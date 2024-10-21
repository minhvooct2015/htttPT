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

@Path("/login")
public class UserResource {

    @Inject

    LoginService loginService;

    @POST
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
        UserEntity registeredUser = loginService.register(request);
        return Response.ok(registeredUser).build(); // Return registered user object
    }
}

@Getter
@Setter
class LoginRequest {
    public String taiKhoan;
    public String matKhau;

}
