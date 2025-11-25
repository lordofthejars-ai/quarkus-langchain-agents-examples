package org.acme;

import dev.langchain4j.agentic.Agent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;


public class ExchangeOperator {
    public static Map<String, Double> exchangeRatesToUSD = new HashMap<>();

    static {
        exchangeRatesToUSD.put("USD", 1.0);
        exchangeRatesToUSD.put("EUR", 1.15);
        exchangeRatesToUSD.put("CHF", 1.25);
        exchangeRatesToUSD.put("CAN", 0.8);
    }

    @Agent(
            description =
                    "A money exchanger that converts a given amount of money from the original to the target currency",
            outputName = "exchange")
    public Double exchange(
            String originalCurrency,
            Double amount,
            String targetCurrency) {
        Double exchangeRate1 = exchangeRatesToUSD.get(originalCurrency);
        if (exchangeRate1 == null) {
            throw new RuntimeException("No exchange rate found for currency " + originalCurrency);
        }
        Double exchangeRate2 = exchangeRatesToUSD.get(targetCurrency);
        if (exchangeRate2 == null) {
            throw new RuntimeException("No exchange rate found for currency " + targetCurrency);
        }
        return (amount * exchangeRate1) / exchangeRate2;
    }
}
