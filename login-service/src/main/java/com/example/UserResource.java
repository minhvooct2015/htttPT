package com.example;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.Setter;

@Path("/api")
public class UserResource {

    @Inject

    LoginService loginService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        UserEntity user = loginService.login(loginRequest.getTaiKhoan(), loginRequest.getMatKhau());
        return Response.ok().entity(user).build();
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserRegistrationRequest request) {
        try {
            // Register the user
            UserEntity registeredUser = loginService.register(request);

            // Return the registered user in the response body with status CREATED (201)
            return Response.status(Response.Status.CREATED)
                    .entity(registeredUser) // Send the registered user as the response body
                    .build();
        } catch (Exception e) {
            // Handle any exceptions, log the error, and return an appropriate response
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error registering user: " + e.getMessage())
                    .build();
        }
    }

}

@Getter
@Setter
class LoginRequest {
    public String taiKhoan;
    public String matKhau;

}
