package org.acme;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.V;


public interface A2ACreativeWriter {

    @Agent // No description, it is taken from agent card
    String generateStory(@V("topic") String topic);
}
