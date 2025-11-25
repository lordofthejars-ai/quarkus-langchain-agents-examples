package org.acme.a2a.myserver;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface CreativeWriter {

    @UserMessage("""
                You are a creative writer.
                Generate a draft of a story long no more than 3 sentence around the given topic.
                Return only the story and nothing else.
                The topic is {{topic}}.
                """)
    String generateStory(@V("topic") String topic);
}