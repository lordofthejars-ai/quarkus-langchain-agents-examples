package org.acme;


import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;

import java.util.List;

public interface FoodExpert {

    @UserMessage("""
        You are a great evening planner.
        Propose a list of 3 meals matching the given mood.
        The mood is {{mood}}.
        For each meal, just give the name of the meal.
        Provide a list with the 3 items and nothing else.
        """)
    @Agent("A food expert to purpose meals depending on the mood of the person")
    List<String> findMeal(String mood);
}
