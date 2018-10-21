package nl.kevinvanschaijk.service.entity.track;

import java.util.ArrayList;

public class Tracklist {
    ArrayList<Track> tracks;

    public Tracklist() {
    }

    public Tracklist(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
}