package org.acme.a2a.myserver;

import io.a2a.server.PublicAgentCard;
import io.a2a.spec.AgentCapabilities;
import io.a2a.spec.AgentCard;
import io.a2a.spec.AgentInterface;
import io.a2a.spec.AgentSkill;
import io.a2a.spec.TransportProtocol;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class CreativeWriterAgentCardProducer {

    @Produces
    @PublicAgentCard
    public AgentCard agentCard() {
        return new AgentCard.Builder()
                .name("Creative Writer")
                .description("Generate a story based on the given topic")
                .url("http://localhost:8080")
                .version("1.0.0")
                .protocolVersion("1.0.0")
                .capabilities(new AgentCapabilities.Builder()
                        .streaming(true)
                        .pushNotifications(false)
                        .stateTransitionHistory(false)
                        .build())
                .defaultInputModes(Collections.singletonList("text"))
                .defaultOutputModes(Collections.singletonList("text"))
                .skills(Collections.singletonList(new AgentSkill.Builder()
                        .id("creative_writer")
                        .name("Creative Writer")
                        .description("Generate a story based on the given topic")
                        .tags(Collections.singletonList("writing"))
                        .build()))
                .protocolVersion("0.3.0")
                .preferredTransport(TransportProtocol.JSONRPC.asString())
                .additionalInterfaces(List.of(
                        new AgentInterface(TransportProtocol.JSONRPC.asString(), "http://localhost:8080")))
                .build();
    }
}
