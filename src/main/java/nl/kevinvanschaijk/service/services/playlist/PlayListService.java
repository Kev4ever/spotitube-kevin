package nl.kevinvanschaijk.service.services.playlist;

import nl.kevinvanschaijk.service.entity.playlist.Playlist;
import nl.kevinvanschaijk.service.entity.playlist.Playlists;
import nl.kevinvanschaijk.service.entity.track.Track;
import nl.kevinvanschaijk.service.entity.track.Tracklist;

import javax.naming.AuthenticationException;

public interface PlayListService {

    Playlists getPlaylists(String token) throws AuthenticationException;

    Playlists deletePlaylist(int id, String token) throws AuthenticationException;

    Tracklist getPlaylistTracks(int id, String token) throws AuthenticationException;

    Playlists addPlaylist(Playlist playlist, String token) throws AuthenticationException;

    Playlists updatePlaylist(Playlist playlist, int id, String token) throws AuthenticationException;

    Tracklist deletePlaylistTrack(int playlistId, int trackId, String token) throws AuthenticationException;

    Tracklist addPlaylistTrack(Track track, int playlistId, String token) throws AuthenticationException;
}
