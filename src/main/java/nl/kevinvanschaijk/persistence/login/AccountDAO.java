package nl.kevinvanschaijk.persistence.login;

import nl.kevinvanschaijk.persistence.ConnectionFactory;
import nl.kevinvanschaijk.service.entity.login.Account;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    @Inject
    ConnectionFactory connectionFactory;

    public List<Account> getAllAcounts() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ACCOUNT")
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String password = resultSet.getString("password");
                accounts.add(new Account(user, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return accounts;
    }

    public Account getAccount(String username) {
        Account userAccount = null;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT username,password from account where username = ?");
        ) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("username");
                String password = resultSet.getString("password");
                userAccount = new Account(user, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAccount;
    }


    public void persistAccount(Account account) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO ACCOUNT (user, password) VALUES(?,?)");
        ) {
            statement.setString(1, account.getUser());
            statement.setString(2, account.getPassword());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
