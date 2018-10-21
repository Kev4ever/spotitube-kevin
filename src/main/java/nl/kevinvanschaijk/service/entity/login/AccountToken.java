package nl.kevinvanschaijk.service.entity.login;

public class AccountToken {
    private String user;
    private String token;

    public AccountToken(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public AccountToken() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
