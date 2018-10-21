package nl.kevinvanschaijk.service.services.login;

import nl.kevinvanschaijk.service.entity.login.Account;
import nl.kevinvanschaijk.service.entity.login.AccountToken;

import javax.security.auth.login.LoginException;

public interface LoginService {
    AccountToken receiveLoginRequest(Account account) throws LoginException;

    boolean isValidUser(Account account);
}
