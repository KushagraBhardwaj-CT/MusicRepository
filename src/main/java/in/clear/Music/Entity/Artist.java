package in.clear.Music.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Artist {
    private String name;
    private long streams;
    private List<Song> songs;

    public Artist() {
        this.streams = 0;
        this.songs = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStreams() {
        return streams;
    }

    public void setStreams(long streams) {
        this.streams = streams;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}