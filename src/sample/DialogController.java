package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.model.Album;
import sample.model.Artist;
import sample.model.Song;
import sample.model.SongArtist;

public class DialogController {
    //Song Dialog
    @FXML
    private TextField trackField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField albumIdField;
    @FXML
    private Label trackField1;
    @FXML
    private Label titleField1;
    @FXML
    private Label albumIdField1;

    //Album Dialog
    @FXML
    private TextField nameField11;
    @FXML
    private TextField artistIdField11;
    @FXML
    private Label nameField111;
    @FXML
    private Label artistIdField111;

    //Artist Dialog
    @FXML
    private TextField nameField2;
    @FXML
    private Label nameField22;

    //ADD ALL DIALOG
    @FXML
    private TextField songTitleField;
    @FXML
    private TextField artistNameField;
    @FXML
    private TextField albumNameField;
    @FXML
    private TextField trackNumberField;

    //Add Album
    @FXML
    private TextField albumNameField5;

    //Add Artist
    @FXML
    private TextField artistNameField5;

    //Add Song
    @FXML
    private TextField songTitleField6;
    @FXML
    private TextField trackNumberField6;

    public Album insertAlbum(){
        String name = albumNameField5.getText().trim();
        Album album = new Album(name);
        return album;
    }

    public Artist insertArtist(){
        String name = artistNameField5.getText().trim();
        Artist artist = new Artist(name);
        return artist;
    }

    public Song insertSong1(){
        String title = songTitleField6.getText().trim();
        int track = Integer.parseInt(trackNumberField6.getText().trim());
        Song song = new Song(track, title);
        return song;
    }

    public SongArtist insertSong(){
        String song = songTitleField.getText().trim();
        String artist = artistNameField.getText().trim();
        String album = albumNameField.getText().trim();
        int track = Integer.parseInt(trackNumberField.getText().trim());
        SongArtist songArtist = new SongArtist(song, artist, album, track);
        return songArtist;
    }

    public void showSong(Song song) {
        trackField1.setText(String.valueOf(song.getTrack()));
        titleField1.setText(song.getName());
        albumIdField1.setText(String.valueOf(song.getAlbumId()));
    }

    public Song updateSong(){
        int track = Integer.parseInt(trackField.getText().trim());
        String title = titleField.getText().trim();
        int albumId = Integer.parseInt(albumIdField.getText().trim());

        Song song = new Song(track, title, albumId);
        return song;
    }

    public void showAlbum(Album album){
        nameField111.setText(album.getName());
        artistIdField111.setText(String.valueOf(album.getArtistId()));
    }

    public Album updateAlbum(){
        String name = nameField11.getText().trim();
        int artistId = Integer.parseInt(artistIdField11.getText().trim());

        Album album = new Album(name, artistId);
        return album;
    }

    public void showArtist(Artist artist){
        nameField22.setText(artist.getName());
    }

    public Artist updateArtist(){
        String name = nameField2.getText().trim();

        Artist artist = new Artist(name);
        return artist;
    }
}
