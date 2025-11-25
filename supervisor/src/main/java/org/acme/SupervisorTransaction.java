package org.acme;

import dev.langchain4j.agentic.declarative.SubAgent;
import dev.langchain4j.agentic.declarative.SupervisorAgent;
import dev.langchain4j.agentic.scope.ResultWithAgenticScope;
import dev.langchain4j.agentic.supervisor.SupervisorResponseStrategy;

public interface SupervisorTransaction {

    @SupervisorAgent(outputName = "exchange", responseStrategy = SupervisorResponseStrategy.SUMMARY, subAgents = {
            @SubAgent(type = CreditAgent.class, outputName = "exchange"),
            @SubAgent(type = WithdrawAgent.class, outputName = "exchange"),
            @SubAgent(type = ExchangeOperator.class, outputName = "exchange")
    })
    ResultWithAgenticScope<String> makeTransaction(String request);

}