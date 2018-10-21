package nl.kevinvanschaijk.service.services.playlist;

import nl.kevinvanschaijk.persistence.login.AccountTokenDAO;
import nl.kevinvanschaijk.persistence.playlist.PlaylistDAO;
import nl.kevinvanschaijk.persistence.track.TrackDAO;
import nl.kevinvanschaijk.service.entity.login.AccountToken;
import nl.kevinvanschaijk.service.entity.playlist.Playlist;
import nl.kevinvanschaijk.service.entity.playlist.Playlists;
import nl.kevinvanschaijk.service.entity.track.Track;
import nl.kevinvanschaijk.service.entity.track.Tracklist;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.naming.AuthenticationException;

@Default
public class PlaylistServiceImpl implements PlayListService {

    @Inject
    AccountTokenDAO accountTokenDAO;
    @Inject
    PlaylistDAO playlistDAO;
    @Inject
    TrackDAO trackDAO;
    private Playlists playlists = new Playlists();

    public PlaylistServiceImpl() {
    }

    @Override
    public Playlists getPlaylists(String token) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getToken(token);
        if (accountTokenDAO.checkValidToken(accountToken)) {
            return new Playlists(playlistDAO.getPlaylistLength(), playlistDAO.getAllPlaylists(accountToken));
        } else {
            throw new AuthenticationException("Invalid token");
        }
    }

    @Override
    public Playlists deletePlaylist(String id, String token) throws AuthenticationException {
        if (accountTokenDAO.checkValidToken(accountTokenDAO.getToken(token))) {
            playlistDAO.deletePlaylist(Integer.parseInt(id));
            return getPlaylists(token);
        } else {
            throw new AuthenticationException("Invalid token");
        }
    }

    public void setPlaylists(Playlists playlists) {
        this.playlists = playlists;
    }

    @Override
    public Tracklist getPlaylistTracks(int id, String token) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getToken(token);
        if (accountTokenDAO.checkValidToken(accountToken)) {
            return new Tracklist(trackDAO.getPlaylistTracks(id));
        } else {
            throw new AuthenticationException("Invalid token");
        }
    }

    @Override
    public Playlists addPlaylist(Playlist playlist, String token) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getToken(token);
        if (accountTokenDAO.checkValidToken(accountToken)) {
            playlistDAO.persistPlaylist(playlist, accountToken);
            return getPlaylists(token);
        } else {
            throw new AuthenticationException("Invalid token");
        }
    }

    @Override
    public Playlists updatePlaylist(Playlist playlist, String id, String token) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getToken(token);
        if (accountTokenDAO.checkValidToken(accountToken)) {
            playlistDAO.updatePlaylist(playlist, id);
            return getPlaylists(token);
        } else {
            throw new AuthenticationException("Invalid token");
        }
    }

    @Override
    public Tracklist deletePlaylistTrack(int playlistId, int trackId, String token) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getToken(token);
        if (accountTokenDAO.checkValidToken(accountToken)) {
            trackDAO.deletePlaylistTrack(playlistId, trackId);
            return new Tracklist(trackDAO.getPlaylistTracks(playlistId));
        } else {
            throw new AuthenticationException("Invalid token");
        }
    }

    @Override
    public Tracklist addPlaylistTrack(Track track, int playlistId, String token) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getToken(token);
        if (accountTokenDAO.checkValidToken(accountToken)) {
            trackDAO.persistPlaylistTrack(track, playlistId);
            return new Tracklist(trackDAO.getPlaylistTracks(playlistId));
        } else {
            throw new AuthenticationException("Invalid token");
        }
    }

}
