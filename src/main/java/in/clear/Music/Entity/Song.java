package in.clear.Music.Entity;

import java.util.UUID;

public class Song {
    private String id;
    private SongInfo songInfo;

    public Song(SongInfo songInfo) {
        this.id = UUID.randomUUID().toString();
        this.songInfo = songInfo;
    }

    public String getId() {
        return id;
    }

    public SongInfo getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(SongInfo songInfo) {
        this.songInfo = songInfo;
    }
}