package nl.kevinvanschaijk.service.services.track;

import nl.kevinvanschaijk.persistence.login.AccountTokenDAO;
import nl.kevinvanschaijk.persistence.track.TrackDAO;
import nl.kevinvanschaijk.service.entity.login.AccountToken;
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
public class TrackServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    TrackServiceImpl sut;

    @Mock
    private AccountTokenDAO accountTokenDAOMock;

    @Mock
    private TrackDAO trackDAOMock;

    @Test
    public void getAllTracksRequestShouldReturnTracklist() throws AuthenticationException {

        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(new AccountToken());
        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(true);
        Mockito.when(trackDAOMock.getAllTracks(Mockito.any())).thenReturn(new ArrayList<Track>());

        assertTrue(sut.getAllTracks("1", "123") instanceof Tracklist);
    }

    @Test
    public void getAllTracksRequestShouldThrowAuthenticationException() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid token");

        Mockito.when(accountTokenDAOMock.getToken(Mockito.any())).thenReturn(new AccountToken());
        Mockito.when(accountTokenDAOMock.checkValidToken(Mockito.any())).thenReturn(false);
        Mockito.when(trackDAOMock.getAllTracks(Mockito.any())).thenReturn(new ArrayList<Track>());

        sut.getAllTracks("1", "123");
    }
}