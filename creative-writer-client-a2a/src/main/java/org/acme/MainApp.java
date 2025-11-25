package org.acme;


import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.UntypedAgent;
import dev.langchain4j.model.chat.ChatModel;

import java.util.Map;

public class MainApp {
    public static void main(String[] args) {

        ChatModel BASE_MODEL = ModelProducer.create();

        StyleEditor styleEditor = AgenticServices
                .agentBuilder(StyleEditor.class)
                .chatModel(BASE_MODEL)
                .outputKey("story")
                .build();

        StyleScorer styleScorer = AgenticServices
                .agentBuilder(StyleScorer.class)
                .chatModel(BASE_MODEL)
                .outputKey("score")
                .build();

        UntypedAgent styleReviewLoop = AgenticServices
                .loopBuilder()
                .subAgents(styleScorer, styleEditor)
                .maxIterations(5)
                .testExitAtLoopEnd(true)
                .exitCondition( (agenticScope, loopCounter) -> {
                    double score = agenticScope.readState("score", 0.0);
                    return loopCounter <= 3 ? score >= 0.8 : score >= 0.6;
                })
                .build();

        A2ACreativeWriter creativeWriter = AgenticServices
                .a2aBuilder("http://localhost:8080", A2ACreativeWriter.class)
                .outputKey("story")
                .build();

        UntypedAgent novelCreator = AgenticServices
                .sequenceBuilder()
                .subAgents(creativeWriter, styleReviewLoop)
                .outputKey("story")
                .build();

        Map<String, Object> input = Map.of(
                "topic", "dragons and wizards",
                "style", "fantasy",
                "audience", "young adults"
        );

        String story = (String) novelCreator.invoke(input);
        System.out.println(story);
    }
}
