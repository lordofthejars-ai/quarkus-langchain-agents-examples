package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/tx")
public class TransferResource {

    @Inject
    SupervisorTransaction supervisorTransaction;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String userRequest = "Transfer 100 EUR from Alex's account to Jonathan' one";
        return supervisorTransaction.makeTransaction(userRequest).result();
    }
}
