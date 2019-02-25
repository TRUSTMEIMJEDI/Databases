package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SongArtist {
    private SimpleStringProperty title;
    private SimpleStringProperty artist;
    private SimpleStringProperty album;
    private SimpleIntegerProperty track;

    public SongArtist(){
        this.title = new SimpleStringProperty();
        this.artist = new SimpleStringProperty();
        this.album = new SimpleStringProperty();
        this.track = new SimpleIntegerProperty();
    }

    public SongArtist(String title, String artist, String album, int track){
        this.title = new SimpleStringProperty(title);
        this.artist = new SimpleStringProperty(artist);
        this.album = new SimpleStringProperty(album);
        this.track = new SimpleIntegerProperty(track);
    }

    public String getTitle() { return title.get(); }

    public void setTitle(String title) { this.title.set(title); }

    public String getArtist() { return artist.get(); }

    public void setArtist(String artist) { this.artist.set(artist); }

    public String getAlbum() { return album.get(); }

    public void setAlbum(String album) { this.album.set(album); }

    public int getTrack() { return track.get(); }

    public void setTrack(int track) { this.track.set(track); }
}
