package in.clear.Music.Entity;

public class SongInfo {
    private String id;
    private String name;
    private Artist artist;
    private long streams;

    public SongInfo() {
        this.streams = 0; // Initialize streams to 0
    }

    public SongInfo(String id, String name, Artist artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.streams = 0; // Initialize streams to 0
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public long getStreams() {
        return streams;
    }

    public void setStreams(long streams) {
        this.streams = streams;
    }
}