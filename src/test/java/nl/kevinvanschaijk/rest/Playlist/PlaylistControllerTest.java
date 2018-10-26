package nl.kevinvanschaijk.rest.playlist;

import nl.kevinvanschaijk.service.entity.playlist.Playlist;
import nl.kevinvanschaijk.service.entity.playlist.Playlists;
import nl.kevinvanschaijk.service.entity.track.Track;
import nl.kevinvanschaijk.service.entity.track.Tracklist;
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
    public void getAllPlaylistsRequestShouldReturnOk() throws AuthenticationException {

        Playlists playlists = new Playlists();

        Mockito.when(playlistServiceMock.getPlaylists(Mockito.any())).thenReturn(playlists);
        Response response = sut.getAllPlaylists("123");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void getAllPlaylistsRequestShouldReturnForbidden() throws AuthenticationException {

        Mockito.when(playlistServiceMock.getPlaylists(Mockito.any())).thenThrow(new AuthenticationException());
        Response response = sut.getAllPlaylists("123");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void getAllPlaylistsRequestShouldReturnBadRequest() throws AuthenticationException {

        Response response = sut.getAllPlaylists("");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void getAllTracksInPlaylistRequestShouldReturnOk() throws AuthenticationException {
        Tracklist tracklist = new Tracklist();

        Mockito.when(playlistServiceMock.getPlaylistTracks(Mockito.anyInt(), Mockito.any())).thenReturn(tracklist);
        Response response = sut.getAllTracksInPlaylist(1, "123");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracklist, response.getEntity());
    }

    @Test
    public void getAllTracksInPlaylistRequestShouldReturnForbidden() throws AuthenticationException {
        Tracklist tracklist = new Tracklist();

        Mockito.when(playlistServiceMock.getPlaylistTracks(Mockito.anyInt(), Mockito.any())).thenThrow(new AuthenticationException());
        Response response = sut.getAllTracksInPlaylist(1, "123");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());

    }

    @Test
    public void getAllTracksInPlaylistRequestShouldReturnBadRequest() throws AuthenticationException {
        Response response = sut.getAllTracksInPlaylist(1, "");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void deletePlaylistRequestShouldReturnOk() throws AuthenticationException {
        Playlists playlists = new Playlists();

        Mockito.when(playlistServiceMock.deletePlaylist(Mockito.anyInt(), Mockito.any())).thenReturn(playlists);
        Response response = sut.deletePlaylist(1, "123");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void deletePlaylistsRequestShouldReturnForbidden() throws AuthenticationException {

        Mockito.when(playlistServiceMock.deletePlaylist(Mockito.anyInt(), Mockito.any())).thenThrow(new AuthenticationException());
        Response response = sut.deletePlaylist(1, "123");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void deletePlaylistsRequestShouldReturnBadRequest() throws AuthenticationException {

        Response response = sut.deletePlaylist(1, "");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void addPlaylistRequestShouldReturnCreated() throws AuthenticationException {
        Playlists playlists = new Playlists();

        Mockito.when(playlistServiceMock.addPlaylist(Mockito.any(), Mockito.any())).thenReturn(playlists);
        Response response = sut.addPlaylist(new Playlist(), "123");

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void addPlaylistRequestShouldReturnForbidden() throws AuthenticationException {

        Mockito.when(playlistServiceMock.addPlaylist(Mockito.any(), Mockito.any())).thenThrow(new AuthenticationException());
        Response response = sut.addPlaylist(new Playlist(), "123");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void addPlaylistRequestShouldReturnBadRequest() throws AuthenticationException {
        Response response = sut.addPlaylist(new Playlist(), "");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void updatePlaylistRequestShouldReturnOk() throws AuthenticationException {
        Playlists playlists = new Playlists();

        Mockito.when(playlistServiceMock.updatePlaylist(Mockito.any(), Mockito.anyInt(), Mockito.any())).thenReturn(playlists);
        Response response = sut.updatePlaylist(new Playlist(), 1, "123");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void updatePlaylistRequestShouldReturnForbidden() throws AuthenticationException {

        Mockito.when(playlistServiceMock.updatePlaylist(Mockito.any(), Mockito.anyInt(), Mockito.any())).thenThrow(new AuthenticationException());
        Response response = sut.updatePlaylist(new Playlist(), 1, "123");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void updatePlaylistRequestShouldReturnBadRequest() throws AuthenticationException {
        Response response = sut.updatePlaylist(new Playlist(), 1, "");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void deletePlaylistTrackRequestShouldReturnOk() throws AuthenticationException {
        Tracklist tracklist = new Tracklist();

        Mockito.when(playlistServiceMock.deletePlaylistTrack(Mockito.anyInt(), Mockito.anyInt(), Mockito.any())).thenReturn(tracklist);
        Response response = sut.deletePlaylistTrack(1, 1, "123");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracklist, response.getEntity());
    }

    @Test
    public void deletePlaylistTrackRequestShouldReturnForbidden() throws AuthenticationException {

        Mockito.when(playlistServiceMock.deletePlaylistTrack(Mockito.anyInt(), Mockito.anyInt(), Mockito.any())).thenThrow(new AuthenticationException());
        Response response = sut.deletePlaylistTrack(1, 1, "123");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void deletePlaylistTrackRequestShouldReturnBadRequest() throws AuthenticationException {

        Response response = sut.deletePlaylistTrack(1, 1, "");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void addPlaylistTrackRequestShouldReturnCreated() throws AuthenticationException {
        Tracklist tracklist = new Tracklist();

        Mockito.when(playlistServiceMock.addPlaylistTrack(Mockito.any(), Mockito.anyInt(), Mockito.any())).thenReturn(tracklist);
        Response response = sut.addPlaylistTrack(new Track(), 1, "123");

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(tracklist, response.getEntity());
    }

    @Test
    public void addPlaylistTrackRequestShouldReturnForbidden() throws AuthenticationException {

        Mockito.when(playlistServiceMock.addPlaylistTrack(Mockito.any(), Mockito.anyInt(), Mockito.any())).thenThrow(new AuthenticationException());
        Response response = sut.addPlaylistTrack(new Track(), 1, "123");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void addPlaylistTrackRequestShouldReturnBadRequest() throws AuthenticationException {

        Response response = sut.addPlaylistTrack(new Track(), 1, "");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}