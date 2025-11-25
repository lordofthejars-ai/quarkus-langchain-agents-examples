package org.acme;


import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.scope.ResultWithAgenticScope;
import dev.langchain4j.agentic.supervisor.SupervisorAgent;
import dev.langchain4j.agentic.supervisor.SupervisorResponseStrategy;
import dev.langchain4j.model.chat.ChatModel;

public class MainApp {
    public static void main(String[] args) {

        ChatModel BASE_MODEL = ModelProducer.create();

        BankTool bankTool = new BankTool();
        bankTool.createAccount("Alex", 1000.0);
        bankTool.createAccount("Jonathan", 1000.0);

        WithdrawAgent withdrawAgent = AgenticServices
                .agentBuilder(WithdrawAgent.class)
                .chatModel(BASE_MODEL)
                .tools(bankTool)
                .build();
        CreditAgent creditAgent = AgenticServices
                .agentBuilder(CreditAgent.class)
                .chatModel(BASE_MODEL)
                .tools(bankTool)
                .build();

        ExchangeOperator exchangeAgent = new ExchangeOperator();

        SupervisorAgent bankSupervisor = AgenticServices
                .supervisorBuilder()
                .chatModel(BASE_MODEL)
                .subAgents(withdrawAgent, creditAgent, exchangeAgent)
                .responseStrategy(SupervisorResponseStrategy.SUMMARY)
                .build();

        ResultWithAgenticScope<String> result = bankSupervisor.invokeWithAgenticScope("Transfer 100 EUR from Alex's account to Jonathan' one");

        System.out.println(result);


    }
}
