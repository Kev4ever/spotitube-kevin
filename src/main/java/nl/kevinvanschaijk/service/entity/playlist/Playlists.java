package nl.kevinvanschaijk.service.entity.playlist;

import java.util.ArrayList;

public class Playlists {
    private int length;
    private ArrayList<Playlist> playlists = new ArrayList<Playlist>();

    public Playlists() {

    }

    public Playlists(int length, ArrayList<Playlist> playlists) {
        this.length = length;
        this.playlists = playlists;
    }

//    public void removePlaylist(int id){
//        playlists.removeIf(obj -> obj.getId() == id);
//    }

//    public Playlist getPlaylist(int id){
//        Playlist searched = null;
//        for(Playlist list : playlists) {
//            if(list.getId() == id){
//                searched = list;
//            }
//        }
//        return searched;
//    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
