package gateway.client;


import com.example.LoginRequest;
import com.example.UserRegistrationRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
@RegisterRestClient
public interface LoginClient {

    @POST
    @Path("api/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response login(LoginRequest loginRequest);

    @POST
    @Path("/api/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response register(UserRegistrationRequest request);
}

