package nl.kevinvanschaijk.rest.track;

import nl.kevinvanschaijk.service.entity.track.Tracklist;
import nl.kevinvanschaijk.service.services.track.TrackServiceImpl;
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
public class TrackControllerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private TrackServiceImpl trackServiceMock;

    @InjectMocks
    private TrackController sut;

    @Test
    public void getAllTracksRequestShouldReturnOk() throws AuthenticationException {
        Tracklist tracklist = new Tracklist();

        Mockito.when(trackServiceMock.getAllTracks(Mockito.any(), Mockito.any())).thenReturn(tracklist);
        Response response = sut.getAllTracks("1", "123");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracklist, response.getEntity());
    }

    @Test
    public void getAllTracksRequestShouldReturnForbidden() throws AuthenticationException {
        Mockito.when(trackServiceMock.getAllTracks(Mockito.any(), Mockito.any())).thenThrow(new AuthenticationException());
        Response response = sut.getAllTracks("1", "123");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void getAllTracksRequestShouldReturnBadRequest() throws AuthenticationException {
        Response response = sut.getAllTracks("", "123");

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

}