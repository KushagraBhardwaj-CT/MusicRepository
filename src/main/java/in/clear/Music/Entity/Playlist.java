package in.clear.Music.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Playlist {
    private String id;
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }
}