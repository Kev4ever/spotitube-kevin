package nl.kevinvanschaijk.persistence.login;

import nl.kevinvanschaijk.persistence.ConnectionFactory;
import nl.kevinvanschaijk.service.entity.login.AccountToken;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class AccountTokenDAO {
    @Inject
    ConnectionFactory connectionFactory;


    public AccountToken getToken(String token) {
        AccountToken accountToken = null;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT token,Account_username from token WHERE token = ?");
        ) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                String dbToken = resultSet.getString("token");
                String dbUsername = resultSet.getString("Account_username");

                accountToken = new AccountToken(dbUsername, dbToken);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountToken;
    }

    public AccountToken persistToken(String username) {
        AccountToken token = null;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO  token(token,Account_username,expiredate) VALUES (?,?,?)");
        ) {

            String code = UUID.randomUUID().toString();
            LocalDateTime currentDateTime = LocalDateTime.now().plusHours(24);

            statement.setString(1, code);
            statement.setString(2, username);
            statement.setString(3, currentDateTime.toString());
            statement.execute();

            token = new AccountToken(username, code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return token;
    }

    public boolean checkValidToken(AccountToken token) {
        boolean validToken = false;
        if (token != null) {
            try (
                    Connection connection = connectionFactory.getConnection();
                    PreparedStatement statement = connection.prepareStatement(
                            "SELECT expiredate from token where token = ? AND Account_username = ?");
            ) {
                statement.setString(1, token.getToken());
                statement.setString(2, token.getUser());
                ResultSet resultSet = statement.executeQuery();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                while (resultSet.next()) {
                    String expireDate = resultSet.getString("expiredate");
                    validToken = LocalDateTime.parse(expireDate, formatter).isAfter(LocalDateTime.now());

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return validToken;
    }


}
