package org.acme.a2a.myserver;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;


@ApplicationScoped
public class CreativeWriterProducer {

    static final ChatModel model = OpenAiChatModel.builder()
            .apiKey("demo")
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .modelName("gpt-4o-mini")
                .build();

    @Produces
    public CreativeWriter creativeWriter() {
        return AiServices.builder(CreativeWriter.class)
                .chatModel(model)
                .build();
    }
}
