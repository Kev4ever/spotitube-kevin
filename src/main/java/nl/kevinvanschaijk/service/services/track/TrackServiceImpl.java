package nl.kevinvanschaijk.service.services.track;

import nl.kevinvanschaijk.persistence.login.AccountTokenDAO;
import nl.kevinvanschaijk.persistence.track.TrackDAO;
import nl.kevinvanschaijk.service.entity.login.AccountToken;
import nl.kevinvanschaijk.service.entity.track.Tracklist;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

@Default
public class TrackServiceImpl implements TrackService {

    @Inject
    TrackDAO trackDAO;

    @Inject
    AccountTokenDAO accountTokenDAO;

    @Override
    public Tracklist getAllTracks(String forPlayList, String token) throws LoginException {
        AccountToken accountToken = accountTokenDAO.getToken(token);
        if (accountTokenDAO.checkValidToken(accountToken)) {
            return new Tracklist(trackDAO.getAllTracks(forPlayList));
        } else {
            throw new LoginException("Invalid token");
        }
    }
}