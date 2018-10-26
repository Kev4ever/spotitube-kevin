package nl.kevinvanschaijk.service.services.track;

import nl.kevinvanschaijk.service.entity.track.Tracklist;

import javax.naming.AuthenticationException;

public interface TrackService {
    Tracklist getAllTracks(String forPlayList, String token) throws AuthenticationException;
}
