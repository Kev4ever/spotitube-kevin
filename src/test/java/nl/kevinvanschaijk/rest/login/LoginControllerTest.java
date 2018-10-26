package nl.kevinvanschaijk.rest.login;

import nl.kevinvanschaijk.service.entity.login.Account;
import nl.kevinvanschaijk.service.entity.login.AccountToken;
import nl.kevinvanschaijk.service.services.login.LoginServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private LoginServiceImpl loginServiceMock;

    @InjectMocks
    private LoginController sut;

    @Test
    public void loginRequestShouldReturnCreated() throws LoginException {
        Account account = new Account();
        AccountToken token = new AccountToken("", "");
        Mockito.when(loginServiceMock.receiveLoginRequest(Mockito.any())).thenReturn(token);

        Response response = sut.receiveLoginRequest(account);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(token, response.getEntity());
    }

    @Test
    public void loginRequestShouldreturnUnauthorized() throws LoginException {
        Account account = new Account("", "");
        Mockito.when(loginServiceMock.receiveLoginRequest(Mockito.any())).thenThrow(new LoginException());
        Response response = sut.receiveLoginRequest(account);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }
}