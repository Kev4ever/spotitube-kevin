package nl.kevinvanschaijk.service.services.login;

import nl.kevinvanschaijk.persistence.login.AccountDAO;
import nl.kevinvanschaijk.persistence.login.AccountTokenDAO;
import nl.kevinvanschaijk.service.entity.login.Account;
import nl.kevinvanschaijk.service.entity.login.AccountToken;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private AccountDAO accountDAOMock;

    @Mock
    private AccountTokenDAO accountTokenDAOMock;

    @InjectMocks
    private LoginServiceImpl sut;

    @Test
    public void receiveLoginRequestShouldReturnAccountToken() throws Exception {
        Account account = new Account("kevin", "123");
        AccountToken testToken = new AccountToken("kevin", "abc123");

        Mockito.when(accountDAOMock.getAccount(Mockito.any())).thenReturn(account);
        Mockito.when(accountTokenDAOMock.persistToken(Mockito.any())).thenReturn(testToken);

        assertTrue(sut.isValidUser(account));
        assertTrue(sut.receiveLoginRequest(account) instanceof AccountToken);
    }

    @Test
    public void receiveLoginRequestShouldThrowLoginException() throws LoginException {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Invalid login");

        Mockito.when(accountDAOMock.getAccount(Mockito.any())).thenReturn(new Account("kevin", "123"));
        Account account = new Account("kevin", "1");
        sut.receiveLoginRequest(account);
    }
}