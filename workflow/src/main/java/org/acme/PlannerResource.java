package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/plan")
public class PlannerResource {

    @Inject
    EveningPlannerAgent eveningPlannerAgent;

    @GET
    @Path("{mood}")
    public List<EveningPlan> planEvening(@RestPath String mood) {
        return eveningPlannerAgent.plan(mood);
    }
}
