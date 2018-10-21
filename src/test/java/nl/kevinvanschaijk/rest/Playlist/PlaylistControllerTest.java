package nl.kevinvanschaijk.rest.Playlist;

import nl.kevinvanschaijk.service.entity.playlist.Playlist;
import nl.kevinvanschaijk.service.entity.playlist.Playlists;
import nl.kevinvanschaijk.service.services.playlist.PlaylistServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private PlaylistServiceImpl playlistServiceMock;

    @InjectMocks
    private PlaylistController sut;

    @Test
    public void getPlaylistsRequestShouldReturnOk() throws AuthenticationException {
        String testToken = "123";
        Playlists playlists = new Playlists();

        Mockito.when(playlistServiceMock.getPlaylists(Mockito.any())).thenReturn(playlists);
        Response playlistResponse = sut.getAllPlaylists(testToken);

        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(playlists, playlistResponse.getEntity());
    }

    @Test
    public void getPlaylistRequestShouldReturnForbidden() throws AuthenticationException {
        String token = "";

        Mockito.when(playlistServiceMock.getPlaylists(Mockito.any())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.getAllPlaylists(token);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void deletePlaylistRequestShouldReturnOk() throws AuthenticationException {
        Playlists playlists = new Playlists();
        String id = "1";
        String testToken = "123";

        Mockito.when(playlistServiceMock.deletePlaylist(Mockito.any(), Mockito.any())).thenReturn(playlists);
        Response playlistResponse = sut.deletePlaylist(id, testToken);

        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(playlists, playlistResponse.getEntity());
    }

    @Test
    public void deletePlaylistsRequestShouldReturnForbidden() throws AuthenticationException {
        String id = "1";
        String testToken = "123";

        Mockito.when(playlistServiceMock.deletePlaylist(Mockito.any(), Mockito.any())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.deletePlaylist(id, testToken);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void addPlaylistRequestShouldReturnOk() throws AuthenticationException {
        Playlists playlists = new Playlists();
        Playlist testplaylist = new Playlist();
        String testToken = "123";

        Mockito.when(playlistServiceMock.addPlaylist(Mockito.any(), Mockito.any())).thenReturn(playlists);
        Response playlistResponse = sut.addPlaylist(testplaylist, testToken);

        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(playlists, playlistResponse.getEntity());
    }

    @Test
    public void addPlaylistRequestShouldReturnForbidden() throws AuthenticationException {
        Playlist testplaylist = new Playlist();
        String testToken = "123";

        Mockito.when(playlistServiceMock.addPlaylist(Mockito.any(), Mockito.any())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.addPlaylist(testplaylist, testToken);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), playlistResponse.getStatus());
    }

    @Test
    public void updatePlaylistRequestShouldReturnOk() throws AuthenticationException {
        Playlists playlists = new Playlists();
        Playlist testplaylist = new Playlist();
        String id = "1";
        String testToken = "123";

        Mockito.when(playlistServiceMock.updatePlaylist(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(playlists);
        Response playlistResponse = sut.updatePlaylist(testplaylist, id, testToken);

        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
        assertEquals(playlists, playlistResponse.getEntity());
    }

    @Test
    public void updatePlaylistRequestShouldReturnForbidden() throws AuthenticationException {
        Playlist testplaylist = new Playlist();
        String id = "1";
        String testToken = "123";

        Mockito.when(playlistServiceMock.updatePlaylist(Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(new AuthenticationException());
        Response playlistResponse = sut.updatePlaylist(testplaylist, id, testToken);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), playlistResponse.getStatus());
    }
}