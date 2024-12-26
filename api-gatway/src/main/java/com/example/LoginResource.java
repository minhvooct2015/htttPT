package com.example;

import gateway.client.LoginClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    @RestClient
    LoginClient loginServiceClient;

    @POST
    @Path("/login")
    public Response login(LoginRequest loginRequest) {
        return loginServiceClient.login(loginRequest);
    }

    @POST
    @Path("/register")
    public Response register(UserRegistrationRequest request) {
        return loginServiceClient.register(request);
    }

}

