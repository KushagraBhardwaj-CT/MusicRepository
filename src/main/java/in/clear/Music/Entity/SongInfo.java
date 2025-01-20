package in.clear.Music.Entity;

public class SongInfo {
    private String id;
    private String name;
    private Artist artist;
    private long streams;

    public SongInfo(String id, String name, Artist artist, long streams) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.streams = streams;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Artist getArtist() {
        return artist;
    }

    public long getStreams() {
        return streams;
    }

    public void setStreams(long streams) {
        this.streams = streams;
    }
}