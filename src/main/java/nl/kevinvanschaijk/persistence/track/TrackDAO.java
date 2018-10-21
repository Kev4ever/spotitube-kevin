package nl.kevinvanschaijk.persistence.track;

import nl.kevinvanschaijk.persistence.ConnectionFactory;
import nl.kevinvanschaijk.service.entity.track.Track;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrackDAO {
    @Inject
    ConnectionFactory connectionFactory;

    public ArrayList<Track> getPlaylistTracks(int playlistid) {
        ArrayList<Track> tracks = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT tracks.*, trackinplaylist.offlineAvailable From tracks INNER JOIN trackinplaylist ON trackinplaylist.Track_id = tracks.id WHERE Playlist_id = ?");
        ) {
            statement.setString(1, Integer.toString(playlistid));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String performer = resultSet.getString("performer");
                int duration = resultSet.getInt("duration");
                String album = resultSet.getString("album");
                int playcount = resultSet.getInt("playcount");
                String publicationDate = resultSet.getString("publicationDate");
                String description = resultSet.getString("description");
                boolean offlineAvailable = resultSet.getBoolean("offlineAvailable");
                String url = resultSet.getString("url");

                tracks.add(new Track(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable, url));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    public ArrayList<Track> getAllTracks(String forPlayList) {
        ArrayList<Track> tracks = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT tracks.*,trackinplaylist.offlineAvailable From tracks LEFT JOIN trackinplaylist ON trackinplaylist.Track_id = tracks.id WHERE trackinplaylist.Playlist_id IS NULL OR trackinplaylist.Playlist_id != ?");
        ) {
            statement.setInt(1, Integer.parseInt(forPlayList));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String performer = resultSet.getString("performer");
                int duration = resultSet.getInt("duration");
                String album = resultSet.getString("album");
                int playcount = resultSet.getInt("playcount");
                String publicationDate = resultSet.getString("publicationDate");
                String description = resultSet.getString("description");
                boolean offlineAvailable = resultSet.getBoolean("offlineAvailable");
                String url = resultSet.getString("url");
                tracks.add(new Track(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable, url));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tracks;
    }

    public void deletePlaylistTrack(int playlistId, int trackId) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM trackinplaylist WHERE Playlist_id = ? AND Track_id = ?");
        ) {
            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void persistPlaylistTrack(Track track, int playlistId) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO trackinplaylist (Track_id,Playlist_id,offlineAvailable) VALUES (?,?,?)");
        ) {
            statement.setInt(1, track.getId());
            statement.setInt(2, playlistId);
            statement.setBoolean(3, track.isOfflineAvailable());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
