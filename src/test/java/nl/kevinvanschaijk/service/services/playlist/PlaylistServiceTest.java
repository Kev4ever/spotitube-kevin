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
        AccountToken testToken = new AccountToken();
        ArrayList<Playlist> playlists = new ArrayList<>();

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(true);
        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(testToken);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlists);

        assertTrue(sut.getPlaylists("123") instanceof Playlists);
    }

    @Test
    public void getPlaylistsRequestShouldThrowException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        sut.getPlaylists("123");
    }

    @Test
    public void deletePlaylistRequestShouldReturnModifiedPlaylists() throws AuthenticationException {
        AccountToken testToken = new AccountToken();
        ArrayList<Playlist> playlists = new ArrayList<>();

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(true);
        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(testToken);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlists);


        assertTrue(sut.deletePlaylist("2", "123") instanceof Playlists);
    }

    @Test
    public void deletePlaylistRequestShouldThrowException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        sut.deletePlaylist("1", "123");
    }

    @Test
    public void addPlaylistRequestShouldReturnModifiedPlaylists() throws AuthenticationException {
        Playlist testPlaylist = new Playlist();
        AccountToken testToken = new AccountToken();
        ArrayList<Playlist> playlists = new ArrayList<>();

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(true);
        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(testToken);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlists);


        assertTrue(sut.addPlaylist(testPlaylist, "123") instanceof Playlists);
    }

    @Test
    public void addPlaylistRequestShouldThrowException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Playlist testPlaylist = new Playlist();

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        sut.addPlaylist(testPlaylist, "123");
    }

    @Test
    public void updatePlaylistRequestShouldReturnModifiedPlaylists() throws AuthenticationException {
        Playlist testPlaylist = new Playlist();
        AccountToken testToken = new AccountToken();
        ArrayList<Playlist> playlists = new ArrayList<>();

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(true);
        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(testToken);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlists);

        assertTrue(sut.updatePlaylist(testPlaylist, "1", "123") instanceof Playlists);
    }

    @Test
    public void updatePlaylistRequestShouldThrowException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Playlist testPlaylist = new Playlist();

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        sut.updatePlaylist(testPlaylist, "1,", "123");

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
    public void getPlaylistTracksRequestShouldTrowException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        assertTrue(sut.getPlaylistTracks(1, "123") instanceof Tracklist);
    }

}