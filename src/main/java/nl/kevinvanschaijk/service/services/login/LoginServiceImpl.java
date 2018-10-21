package nl.kevinvanschaijk.service.services.login;

import nl.kevinvanschaijk.persistence.login.AccountDAO;
import nl.kevinvanschaijk.persistence.login.AccountTokenDAO;
import nl.kevinvanschaijk.service.entity.login.Account;
import nl.kevinvanschaijk.service.entity.login.AccountToken;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

@Default
public class LoginServiceImpl implements LoginService {

    @Inject
    AccountDAO accountDAO;

    @Inject
    AccountTokenDAO accountTokenDAO;

    public LoginServiceImpl() {
    }

    @Override
    public AccountToken receiveLoginRequest(Account account) throws LoginException {
        if (isValidUser(account)) {
            return accountTokenDAO.persistToken(account.getUser());
        } else {
            throw new LoginException("Invalid login");
        }
    }

    @Override
    public boolean isValidUser(Account account) {
        boolean isValid;
        Account accountResult = accountDAO.getAccount(account.getUser());
        try {
            isValid = equals(account.getUser(), accountResult.getUser()) && equals(account.getPassword(), accountResult.getPassword());
        } catch (NullPointerException e) {
            isValid = false;
        }
        return isValid;
    }

    private boolean equals(String word1, String word2) throws NullPointerException {
        return word1.equals(word2);
    }
}
