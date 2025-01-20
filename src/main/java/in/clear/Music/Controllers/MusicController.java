package in.clear.Music.Controllers;

import in.clear.Music.Entity.Artist;
import in.clear.Music.Entity.Playlist;
import in.clear.Music.Entity.Song;
import in.clear.Music.Entity.SongInfo;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/music")
public class MusicController {

    private List<Song> songs = new ArrayList<>();
    private List<Artist> artists = new ArrayList<>();
    private List<Playlist> playlists = new ArrayList<>();

    @PostMapping("/addSong")
    public void addSong(@RequestBody SongInfo songInfo) {
        Song song = new Song(songInfo);
        songs.add(song);
        Artist artist = songInfo.getArtist();
        if (!artists.contains(artist)) {
            artists.add(artist);
        }
        System.out.println("Song added: " + songInfo.getName());
        artist.getSongs().add(song);
    }

    @PostMapping("/playSong")
    public void playSong(@RequestParam String songId) {
        Song song = songs.stream()
                .filter(s -> s.getId().equals(songId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Song not found"));
        System.out.println("Playing song: " + song.getSongInfo().getName());
        song.getSongInfo().setStreams(song.getSongInfo().getStreams() + 1);
        Artist artist = song.getSongInfo().getArtist();
        artist.setStreams(artist.getStreams() + 1);
    }

    @GetMapping("/top10Songs")
    public List<SongInfo> getTop10Songs() {
        return songs.stream()
                .map(Song::getSongInfo)
                .sorted(Comparator.comparingLong(SongInfo::getStreams).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    @GetMapping("/top3Songs/{artistName}")
    public List<SongInfo> getTop3SongsByArtist(@PathVariable String artistName) {
        return songs.stream()
                .map(Song::getSongInfo)
                .filter(songInfo -> songInfo.getArtist().getName().equalsIgnoreCase(artistName))
                .sorted(Comparator.comparingLong(SongInfo::getStreams).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    @GetMapping("/searchSong/{name}")
    public List<SongInfo> searchSong(@PathVariable String name) {
        return songs.stream()
                .map(Song::getSongInfo)
                .filter(songInfo -> songInfo.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @GetMapping("/searchArtist/{name}")
    public List<SongInfo> searchArtist(@PathVariable String name) {
        System.out.println("Printing the songs of artist");
        return artists.stream()
                .filter(artist -> artist.getName().equalsIgnoreCase(name))
                .flatMap(artist -> artist.getSongs().stream())
                .map(Song::getSongInfo)
                .sorted(Comparator.comparingLong(SongInfo::getStreams).reversed())
                .collect(Collectors.toList());
    }

    @PostMapping("/createPlaylist")
    public void createPlaylist(@RequestParam String name) {
        Playlist playlist = new Playlist(name);
        playlists.add(playlist);
        System.out.println("Playlist created: " + name + " with ID: " + playlist.getId());
    }

    @PostMapping("/addSongToPlaylist")
    public void addSongToPlaylist(@RequestParam String playlistId, @RequestParam String songId) {
        Playlist playlist = playlists.stream()
                .filter(pl -> pl.getId().equals(playlistId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
        Song song = songs.stream()
                .filter(s -> s.getId().equals(songId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Song not found"));
        playlist.addSong(song);
        System.out.println("Song added to playlist: " + song.getSongInfo().getName());
    }

    @GetMapping("/exportPlaylist")
    public void exportPlaylist(@RequestParam String playlistId, @RequestParam String format) throws IOException {
        Playlist playlist = playlists.stream()
                .filter(pl -> pl.getId().equals(playlistId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        String fileName = playlist.getName() + (format.equalsIgnoreCase("csv") ? ".csv" : ".tsv");
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Song song : playlist.getSongs()) {
                writer.write(song.getId() + (format.equalsIgnoreCase("csv") ? "," : "\t") +
                        song.getSongInfo().getName() + (format.equalsIgnoreCase("csv") ? "," : "\t") +
                        song.getSongInfo().getStreams() + "\n");
            }
        }
        System.out.println("Playlist exported: " + fileName);
    }
}