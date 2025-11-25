package org.acme;


import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;

import java.util.List;

public interface MovieExpert {

    @UserMessage("""
        You are a great evening planner.
        Propose a list of 3 movies matching the given mood.
        The mood is {mood}.
        Provide a list with the 3 items and nothing else.
        """)
    @Agent("A movie expert to purpose movies depending on the mood of the person")
    List<String> findMovie(String mood);
}