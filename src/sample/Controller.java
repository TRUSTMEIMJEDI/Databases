package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import sample.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class Controller {

    @FXML
    private TableView artistTable;

    @FXML
    private ProgressBar progressBar;

    @FXML
    public void listArtists() {
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());

        progressBar.setVisible(true);

        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));

        new Thread(task).start();
    }

    @FXML
    public void listAlbumsForArtist() {
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if(artist == null) {
            System.out.println("NO ARTIST SELECTED");
            return;
        }
        Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {
            @Override
            protected ObservableList<Album> call() throws Exception {
                return FXCollections.observableArrayList(
                        Datasource.getInstance().queryAlbumsForArtistId(artist.getId()));
            }
        };
        artistTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    @FXML
    public void listSongsForAlbum(){
        final Album album = (Album) artistTable.getSelectionModel().getSelectedItem();
        if(album == null) {
            System.out.println("NO ALBUM SELECTED");
            return;
        }
        Task<ObservableList<Song>> task = new Task<ObservableList<Song>>() {
            @Override
            protected ObservableList<Song> call() throws Exception {
                return FXCollections.observableArrayList(
                        Datasource.getInstance().querySongsForAlbumId(album.getId()));
            }
        };
        artistTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    @FXML
    public void deleteSong() {
        final Song song = (Song) artistTable.getSelectionModel().getSelectedItem();
        if(song == null) {
            System.out.println("NO ALBUM SELECTED");
            return;
        }
        int albumId = song.getAlbumId();
        Datasource.getInstance().queryDeleteSongById(song.getId());

        Task<ObservableList<Song>> task = new Task<ObservableList<Song>>() {
            @Override
            protected ObservableList<Song> call() throws Exception {
                return FXCollections.observableArrayList(
                        Datasource.getInstance().querySongsForAlbumId(albumId));
            }
        };
        artistTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    @FXML
    public void deleteAlbum() {
        final Album album = (Album) artistTable.getSelectionModel().getSelectedItem();
        if(album == null) {
            System.out.println("NO ALBUM SELECTED");
            return;
        }
        int artistId = album.getArtistId();
        Datasource.getInstance().queryDeleteAlbumById(album.getId());

        Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {
            @Override
            protected ObservableList<Album> call() throws Exception {
                return FXCollections.observableArrayList(
                        Datasource.getInstance().queryAlbumsForArtistId(artistId));
            }
        };
        artistTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    @FXML
    public void deleteArtist() {
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if(artist == null) {
            System.out.println("NO ARTIST SELECTED");
            return;
        }
        Datasource.getInstance().queryDeleteArtistById(artist.getId());
        listArtists();
    }

    @FXML
    public void updateArtist() {
//        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        final Artist artist = (Artist) artistTable.getItems().get(2);

        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return Datasource.getInstance().updateArtistName(artist.getId(), "AC/DC");
            }
        };

        task.setOnSucceeded(e -> {
            if(task.valueProperty().get()) {
                artist.setName("AC/DC");
                artistTable.refresh();
            }
        });

        new Thread(task).start();
    }

    public void showUpdateArtist() {
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();

        if(artist == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No artist selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the artist you want to edit.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(artistTable.getScene().getWindow());
        dialog.setTitle("Edit selected artist");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("updateArtistDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DialogController dialogController = fxmlLoader.getController();
        dialogController.showArtist(artist);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Artist artist1 = dialogController.updateArtist();
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return Datasource.getInstance().updateArtist(artist.getId(), artist1.getName());
                }
            };

            task.setOnSucceeded(e -> {
                if (task.valueProperty().get()) {
                    artist.setName(artist1.getName());
                    artistTable.refresh();
                }
            });

            new Thread(task).start();
        }
    }

    public void showAddArtist() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(artistTable.getScene().getWindow());
        dialog.setTitle("Add artist");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addArtist.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DialogController dialogController = fxmlLoader.getController();


        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Artist artist = dialogController.insertArtist();
                Datasource.getInstance().insertArtist(artist.getName());
                listArtists();
            } catch (SQLException e){
                System.out.println("[insertArtist in showAddArtist]: Query failed: " +e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void showUpdateSong(){
        final Song song = (Song) artistTable.getSelectionModel().getSelectedItem();

        if(song == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No song selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the song you want to edit.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(artistTable.getScene().getWindow());
        dialog.setTitle("Edit selected song");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("updateSongDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DialogController dialogController = fxmlLoader.getController();
        dialogController.showSong(song);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Song song1 = dialogController.updateSong();
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return Datasource.getInstance().updateSong(song.getId(), song1.getTrack(), song1.getName(), song1.getAlbumId());
                }
            };

            task.setOnSucceeded(e -> {
                if(task.valueProperty().get()) {
                    song.setName(song1.getName());
                    artistTable.refresh();
                }
            });

            new Thread(task).start();
        }
    }

    public void showAddSong() {
        final Album album = (Album) artistTable.getSelectionModel().getSelectedItem();

        if(album == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No album selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the album for which you want to add song.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(artistTable.getScene().getWindow());
        dialog.setTitle("Add song to selected album");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addSong.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DialogController dialogController = fxmlLoader.getController();

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Song song = dialogController.insertSong1();
            Datasource.getInstance().insertSong(song.getName(), album.getId(), song.getTrack());
            listSongsForAlbum();
        }
    }

    public void showUpdateAlbum() {
        final Album album = (Album) artistTable.getSelectionModel().getSelectedItem();

        if(album == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No album selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the album you want to edit.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(artistTable.getScene().getWindow());
        dialog.setTitle("Edit selected album");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("updateAlbumDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DialogController dialogController = fxmlLoader.getController();
        dialogController.showAlbum(album);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Album album1 = dialogController.updateAlbum();
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return Datasource.getInstance().updateAlbum(album.getId(), album1.getName(), album1.getArtistId());
                }
            };

            task.setOnSucceeded(e -> {
                if (task.valueProperty().get()) {
                    album.setName(album1.getName());
                    artistTable.refresh();
                }
            });

            new Thread(task).start();
        }
    }

    public void showAddAlbum() {
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();

        if(artist == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No artist selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the artist for which you want to add album.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(artistTable.getScene().getWindow());
        dialog.setTitle("Add album to selected artist");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addAlbum.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DialogController dialogController = fxmlLoader.getController();

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Album album = dialogController.insertAlbum();
                Datasource.getInstance().insertAlbum(album.getName(), artist.getId());
                listAlbumsForArtist();
            } catch (SQLException e){
                System.out.println("[insertAlbum in showAddAlbum]: Query failed: " +e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void showAddAllDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(artistTable.getScene().getWindow());
        dialog.setTitle("Add song, album, artist");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addAllDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DialogController dialogController = fxmlLoader.getController();

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            SongArtist songArtist = dialogController.insertSong();
            Datasource.getInstance().insertSong(songArtist.getTitle(), songArtist.getArtist(), songArtist.getAlbum(), songArtist.getTrack());
            listArtists();
        }
    }
}

class GetAllArtistsTask extends Task {

    @Override
    public ObservableList<Artist> call()  {
        return FXCollections.observableArrayList
                (Datasource.getInstance().queryArtists(Datasource.ORDER_BY_ASC));
    }
}
