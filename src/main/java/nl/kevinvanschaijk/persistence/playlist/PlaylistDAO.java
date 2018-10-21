package nl.kevinvanschaijk.persistence.playlist;

import nl.kevinvanschaijk.persistence.ConnectionFactory;
import nl.kevinvanschaijk.service.entity.login.AccountToken;
import nl.kevinvanschaijk.service.entity.playlist.Playlist;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistDAO {
    @Inject
    ConnectionFactory connectionFactory;

    public ArrayList<Playlist> getAllPlaylists(AccountToken token) {
        ArrayList<Playlist> playlists = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id,name,Account_username from playlist")
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                boolean owner = token.getUser().equals(resultSet.getString("Account_username"));
                playlists.add(new Playlist(Integer.parseInt(id), name, owner));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return playlists;
    }

    public void deletePlaylist(int playlistId) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM playlist WHERE id = ?");
        ) {
            statement.setInt(1, playlistId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void persistPlaylist(Playlist playlist, AccountToken accountToken) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO playlist(name,Account_username) VALUES (?,?)");
        ) {
            statement.setString(1, playlist.getName());
            statement.setString(2, accountToken.getUser());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePlaylist(Playlist playlist, String id) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE playlist SET name= ? WHERE id = ?");
        ) {
            statement.setString(1, playlist.getName());
            statement.setString(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPlaylistLength() {
        int totalDuration = 0;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "    SELECT SUM(duration) AS 'length' FROM tracks LEFT join trackinplaylist ON trackinplaylist.Track_id = tracks.id");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                totalDuration = resultSet.getInt("length");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalDuration;
    }
}


