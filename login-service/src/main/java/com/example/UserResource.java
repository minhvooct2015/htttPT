package com.example;


import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@Path("/api")
public class UserResource {

    @Inject
    LoginService loginService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        String taiKhoan = loginRequest.getTaiKhoan();
        UserEntity user = loginService.login(taiKhoan, loginRequest.getMatKhau());

        // Here you would generate a JWT token and return it
        // Example: return generateToken(user);
        String token = Jwt.issuer("http://localhost:9000")  // Match `mp.jwt.verify.issuer`
                .upn(taiKhoan)
                .groups(new HashSet<>(Set.of(user.getVaiTro().toString()))) // Add user roles
                .claim("email", user.getEmail())
                .claim("userNumber", user.getMaNguoiDung())
                .claim("tenUser", user.getHoTen())
                .expiresAt(Instant.now().plus(3, ChronoUnit.HOURS)) // Token expires in 1 hour
// Custom claim
                .sign();
        return Response.ok(new AuthResponse(token)).build();
    }

    public static class AuthResponse {
        public String token;

        public AuthResponse(String token) {
            this.token = token;
        }
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserRegistrationRequest request) {
        try {
            // Register the user
            UserEntity user = loginService.register(request);

            String token = Jwt.issuer("http://localhost:9000")  // Match `mp.jwt.verify.issuer`
                    .upn(user.getTaiKhoan())
                    .groups(new HashSet<>(Set.of(user.getVaiTro().toString()))) // Add user roles
                    .claim("email", user.getEmail())
                    .claim("userNumber", user.getMaNguoiDung())
                    .claim("tenUser", user.getHoTen())
                    .expiresAt(Instant.now().plus(3, ChronoUnit.HOURS)) // Token expires in 1 hour
// Custom claim
                    .sign();
            // Return the registered user in the response body with status CREATED (201)
            return Response.status(Response.Status.CREATED)
                    .entity(new AuthResponse(token)) // Send the registered user as the response body
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
