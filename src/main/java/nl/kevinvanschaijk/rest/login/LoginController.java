package nl.kevinvanschaijk.rest.login;

import nl.kevinvanschaijk.service.entity.login.Account;
import nl.kevinvanschaijk.service.services.login.LoginServiceImpl;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @Inject
    LoginServiceImpl loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response receiveLoginRequest(Account account) {
        Response response;
        try {
            response = Response.ok().entity(loginService.receiveLoginRequest(account)).build();
        } catch (LoginException e) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return response;
    }
}
