package org.acme;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class ModelProducer {

    public static ChatModel create() {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey("demo")
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .modelName("gpt-4o-mini")
                .build();

        return model;
    }

}