package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Song {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty track;
    private SimpleStringProperty name;
    private SimpleIntegerProperty albumId;

    public Song() {
        this.id = new SimpleIntegerProperty();
        this.track = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.albumId = new SimpleIntegerProperty();
    }

    public Song(int track, String name) {
        this.track = new SimpleIntegerProperty(track);
        this.name = new SimpleStringProperty(name);
    }

    public Song(int track, String name, int albumId) {
        this.track = new SimpleIntegerProperty(track);
        this.name = new SimpleStringProperty(name);
        this.albumId = new SimpleIntegerProperty(albumId);
    }

    public int getId() { return id.get(); }

    public void setId(int id) { this.id.set(id); }

    public int getTrack() { return track.get(); }

    public void setTrack(int track) { this.track.set(track); }

    public String getName() { return name.get(); }

    public void setName(String name) { this.name.set(name); }

    public int getAlbumId() { return albumId.get(); }

    public void setAlbumId(int albumId) { this.albumId.set(albumId); }
}
