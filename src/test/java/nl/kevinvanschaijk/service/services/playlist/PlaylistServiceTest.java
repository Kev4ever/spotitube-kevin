package nl.kevinvanschaijk.service.services.playlist;

import nl.kevinvanschaijk.persistence.login.AccountTokenDAO;
import nl.kevinvanschaijk.persistence.playlist.PlaylistDAO;
import nl.kevinvanschaijk.persistence.track.TrackDAO;
import nl.kevinvanschaijk.service.entity.login.AccountToken;
import nl.kevinvanschaijk.service.entity.playlist.Playlist;
import nl.kevinvanschaijk.service.entity.playlist.Playlists;
import nl.kevinvanschaijk.service.entity.track.Track;
import nl.kevinvanschaijk.service.entity.track.Tracklist;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.AuthenticationException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    PlaylistServiceImpl sut;

    @Mock
    private AccountTokenDAO accountTokenDAOMock;
    @Mock
    private PlaylistDAO playlistDAOMock;
    @Mock
    private TrackDAO trackDAO;

    @Test
    public void getPlaylistsRequestShouldReturnPlaylists() throws AuthenticationException {

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(true);
        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(new AccountToken());
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(new ArrayList<Playlist>());

        assertTrue(sut.getPlaylists("123") instanceof Playlists);
    }

    @Test
    public void getPlaylistsRequestShouldThrowAuthenticationException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        sut.getPlaylists("123");
    }

    @Test
    public void deletePlaylistRequestShouldReturnPlaylists() throws AuthenticationException {

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(true);
        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(new AccountToken());
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(new ArrayList<Playlist>());

        assertTrue(sut.deletePlaylist(2, "123") instanceof Playlists);
    }

    @Test
    public void deletePlaylistRequestShouldThrowAuthenticationException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        sut.deletePlaylist(1, "123");
    }

    @Test
    public void addPlaylistRequestShouldReturnPlaylists() throws AuthenticationException {

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(true);
        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(new AccountToken());
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(new ArrayList<Playlist>());

        assertTrue(sut.addPlaylist(new Playlist(), "123") instanceof Playlists);
    }

    @Test
    public void addPlaylistRequestShouldThrowAuthenticationException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        sut.addPlaylist(new Playlist(), "123");
    }

    @Test
    public void updatePlaylistRequestShouldReturnPlaylists() throws AuthenticationException {

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(true);
        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(new AccountToken());
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(new ArrayList<Playlist>());

        assertTrue(sut.updatePlaylist(new Playlist(), 1, "123") instanceof Playlists);
    }

    @Test
    public void updatePlaylistRequestShouldThrowAuthenticationException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        sut.updatePlaylist(new Playlist(), 1, "123");

    }

    @Test
    public void getPlaylistTracksRequestShouldReturnTracklist() throws AuthenticationException {
        int testId = 1;
        String token = "123";
        AccountToken testToken = new AccountToken();
        ArrayList<Track> tracks = new ArrayList<>();

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(true);
        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(testToken);
        Mockito.when(trackDAO.getPlaylistTracks(Mockito.anyInt())).thenReturn(tracks);
        assertTrue(sut.getPlaylistTracks(testId, token) instanceof Tracklist);
    }

    @Test
    public void getPlaylistTracksRequestShouldTrowAuthenticationException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        sut.getPlaylistTracks(1, "123");
    }

    @Test
    public void deletePlaylistTrackRequestShouldReturnTracklist() throws AuthenticationException {

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(true);
        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(new AccountToken());
        Mockito.when(trackDAO.getPlaylistTracks(Mockito.anyInt())).thenReturn(new ArrayList<>());
        assertTrue(sut.deletePlaylistTrack(1, 1, "123") instanceof Tracklist);
    }

    @Test
    public void deletePlaylistTrackRequestShouldTrowAuthenticationException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        sut.deletePlaylistTrack(1, 1, "123");
    }

    @Test
    public void addPlaylistTrackRequestShouldReturnTracklist() throws AuthenticationException {

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(true);
        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(new AccountToken());
        Mockito.when(trackDAO.getPlaylistTracks(Mockito.anyInt())).thenReturn(new ArrayList<>());
        assertTrue(sut.addPlaylistTrack(new Track(), 1, "123") instanceof Tracklist);
    }

    @Test
    public void addPlaylistTrackRequestShouldTrowAuthenticationException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        sut.deletePlaylistTrack(1, 1, "123");
    }


}