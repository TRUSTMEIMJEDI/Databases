package sample.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Datasource {

    public static final String username = "msbd18";
    public static final String password = "haslo2018";
    public static final String CONNECTION_STRING = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artists";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + " FROM " + TABLE_ALBUMS +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
                    " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " = \"";

    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " ";

    public static final String QUERY_ARTIST_FOR_SONG_START =
            "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
                    TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS +
                    " INNER JOIN " + TABLE_ALBUMS + " ON " +
                    TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = \"";

    public static final String QUERY_ARTIST_FOR_SONG_SORT =
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " ";

    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";

    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " +
            TABLE_ARTIST_SONG_VIEW + " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + COLUMN_SONG_ALBUM + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK + ", " + TABLE_SONGS + "." + COLUMN_SONG_TITLE +
            " FROM " + TABLE_SONGS +
            " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS +
            "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
            " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
            " ORDER BY " +
            TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK;

    public static final String QUERY_VIEW_SONG_INFO = "SELECT " + COLUMN_ARTIST_NAME + ", " +
            COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = \"";

    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + COLUMN_ARTIST_NAME + ", " +
            COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = ?";


    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS +
            '(' + COLUMN_ARTIST_ID+ ", " + COLUMN_ARTIST_NAME + ") VALUES(?, ?)";

    public static final String INSERT_ALBUMS = "INSERT INTO " + TABLE_ALBUMS +
            '('+ COLUMN_ALBUM_ID+ ", " + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES(?, ?, ?)";

    public static final String INSERT_SONGS = "INSERT INTO " + TABLE_SONGS +
            '('+ COLUMN_SONG_ID + ", " + COLUMN_SONG_TRACK + ", " + COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM +
            ") VALUES(?, ?, ?, ?)";

    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " +
            TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + " = ?";

    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " +
            TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";

    public static final String QUERY_ALBUMS_BY_ARTIST_ID = "SELECT * FROM " + TABLE_ALBUMS +
            " WHERE " + COLUMN_ALBUM_ARTIST + " = ? ORDER BY " + COLUMN_ALBUM_NAME + " ASC";

    public static final String UPDATE_ARTIST_NAME = "UPDATE " + TABLE_ARTISTS + " SET " +
            COLUMN_ARTIST_NAME + " = ? WHERE " + COLUMN_ARTIST_ID + " = ?";

    public static final String QUERY_SONGS_BY_ALBUM_ID = "SELECT * FROM " + TABLE_SONGS +
            " WHERE " + COLUMN_SONG_ALBUM + " = ? ORDER BY " + COLUMN_SONG_TITLE + " ASC";

    public static final String DELETE_SONG_BY_ID = "DELETE FROM " + TABLE_SONGS +
            " WHERE " + COLUMN_SONG_ID + " = ?";

    public static final String DELETE_ALBUM_BY_ID = "DELETE FROM " + TABLE_ALBUMS +
            " WHERE " + COLUMN_ALBUM_ID + " = ?";

    public static final String DELETE_ARTIST_BY_ID = "DELETE FROM " + TABLE_ARTISTS +
            " WHERE " + COLUMN_ARTIST_ID + " = ?";

    public static final String UPDATE_SONG_BY_ID = "UPDATE " + TABLE_SONGS +
            " SET " + COLUMN_SONG_TRACK + " = ?, " + COLUMN_SONG_TITLE + " = ?, " + COLUMN_SONG_ALBUM + " = ?" +
            " WHERE " + COLUMN_SONG_ID + " = ?";

    public static final String UPDATE_ALBUM_BY_ID = "UPDATE " + TABLE_ALBUMS +
            " SET " + COLUMN_ALBUM_NAME + " = ?, " + COLUMN_ALBUM_ARTIST + " = ?" +
            " WHERE " + COLUMN_ALBUM_ID + " = ?";

    public static final String UPDATE_ARTISTS_BY_ID = "UPDATE " + TABLE_ARTISTS +
            " SET " + COLUMN_ARTIST_NAME + " = ?" +
            " WHERE " + COLUMN_ARTIST_ID + " = ?";

    private Connection conn;

    private PreparedStatement querySongInfoView;

    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;

    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;
    private PreparedStatement queryAlbumsByArtistId;
    private PreparedStatement querySongsForAlbumId;

    private PreparedStatement updateArtistName;
    private PreparedStatement updateSongById;
    private PreparedStatement updateAlbumById;
    private PreparedStatement updateArtistById;

    private PreparedStatement deleteSongById;
    private PreparedStatement deleteAlbumById;
    private PreparedStatement deleteArtistById;

    private static Datasource instance = new Datasource();

    private Datasource() {

    }

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING, username, password);
            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);

            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS);

//            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
//            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS, Statement.RETURN_GENERATED_KEYS);

            insertIntoSongs = conn.prepareStatement(INSERT_SONGS);
            queryArtist = conn.prepareStatement(QUERY_ARTIST);
            queryAlbum = conn.prepareStatement(QUERY_ALBUM);
            queryAlbumsByArtistId = conn.prepareStatement(QUERY_ALBUMS_BY_ARTIST_ID);
            querySongsForAlbumId = conn.prepareStatement(QUERY_SONGS_BY_ALBUM_ID);
            updateArtistName = conn.prepareStatement(UPDATE_ARTIST_NAME);
            updateSongById = conn.prepareStatement(UPDATE_SONG_BY_ID);
            updateAlbumById = conn.prepareStatement(UPDATE_ALBUM_BY_ID);
            updateArtistById = conn.prepareStatement(UPDATE_ARTISTS_BY_ID);
            deleteSongById = conn.prepareStatement(DELETE_SONG_BY_ID);
            deleteAlbumById = conn.prepareStatement(DELETE_ALBUM_BY_ID);
            deleteArtistById = conn.prepareStatement(DELETE_ARTIST_BY_ID);


            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {

            if(querySongInfoView != null) {
                querySongInfoView.close();
            }

            if(insertIntoArtists != null) {
                insertIntoArtists.close();
            }

            if(insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }

            if(insertIntoSongs !=  null) {
                insertIntoSongs.close();
            }

            if(queryArtist != null) {
                queryArtist.close();
            }

            if(queryAlbum != null) {
                queryAlbum.close();
            }

            if(queryAlbumsByArtistId != null) {
                queryAlbumsByArtistId.close();
            }

            if(querySongsForAlbumId != null) {
                querySongsForAlbumId.close();
            }

            if(updateArtistName != null) {
                updateArtistName.close();
            }

            if(updateSongById != null) {
                updateSongById.close();
            }

            if(updateAlbumById != null) {
                updateAlbumById.close();
            }

            if(updateArtistById != null) {
                updateArtistById.close();
            }

            if(deleteSongById != null) {
                deleteSongById.close();
            }

            if(deleteAlbumById != null) {
                deleteAlbumById.close();
            }

            if(deleteArtistById != null) {
                deleteArtistById.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("[close]: Couldn't close connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Artist> queryArtists(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append(" DESC");
            } else {
                sb.append(" ASC");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                try {
                    Thread.sleep(20);
                } catch(InterruptedException e) {
                    System.out.println("Interuppted: " + e.getMessage());
                }
                Artist artist = new Artist();
                artist.setId(results.getInt(INDEX_ARTIST_ID));
                artist.setName(results.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;

        } catch (SQLException e) {
            System.out.println("[queryArtists]: Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Album> queryAlbumsForArtistId(int id) {
        try {
            queryAlbumsByArtistId.setInt(1, id);
            ResultSet results = queryAlbumsByArtistId.executeQuery();

            List<Album> albums = new ArrayList<>();
            while(results.next()) {
                Album album = new Album();
                album.setId(results.getInt(1));
                album.setName(results.getString(2));
                album.setArtistId(id);
                albums.add(album);
            }

            return albums;
        } catch(SQLException e) {
            System.out.println("[queryAlbumsForArtistId]: Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Song> querySongsForAlbumId(int id) {
        try {
            querySongsForAlbumId.setInt(1, id);
            ResultSet results = querySongsForAlbumId.executeQuery();

            List<Song> songs = new ArrayList<>();
            while(results.next()) {
                Song song = new Song();
                song.setId(results.getInt(1));
                song.setTrack(results.getInt(2));
                song.setName(results.getString(3));
                song.setAlbumId(id);
                songs.add(song);
            }

            return songs;
        } catch(SQLException e) {
            System.out.println("[querySongsForAlbumId]: Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void queryDeleteSongById(int id) {
        try {
            deleteSongById.setInt(1, id);
            deleteSongById.executeQuery();
        } catch (SQLException e) {
            System.out.println("[queryDeleteSongById]: Query failed: " +e.getMessage());
            e.printStackTrace();
        }
    }

    public void queryDeleteAlbumById(int id) {
        try {
            deleteAlbumById.setInt(1, id);
            deleteAlbumById.executeQuery();
        } catch (SQLException e) {
            System.out.println("[queryDeleteAlbumById]: Query failed: " +e.getMessage());
            e.printStackTrace();
        }
    }

    public void queryDeleteArtistById(int id) {
        try {
            deleteArtistById.setInt(1, id);
            deleteArtistById.executeQuery();
        } catch (SQLException e) {
            System.out.println("[queryDeleteArtistById]: Query failed: " +e.getMessage());
            e.printStackTrace();
        }
    }

    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {

        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        sb.append(artistName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        System.out.println("SQL statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<String> albums = new ArrayList<>();
            while (results.next()) {
                albums.add(results.getString(1));
            }

            return albums;

        } catch (SQLException e) {
            System.out.println("[queryAlbumsForArtist]: Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void querySongsMetadata() {
        String sql = "SELECT * FROM " + TABLE_SONGS;

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)) {

            ResultSetMetaData meta = results.getMetaData();
            int numColumns = meta.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                System.out.format("Column %d in the songs table is names %s\n",
                        i, meta.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("[querySongsMetadata]: Query failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getCount(String table) {
        String sql = "SELECT COUNT(*) AS count FROM " + table;
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)) {

            results.next();
            int count = results.getInt("count");

            System.out.format("Count = %d\n", count);
            return count;
        } catch (SQLException e) {
            System.out.println("[getCount]: Query failed: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public boolean createViewForSongArtists() {

        try (Statement statement = conn.createStatement()) {

            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;

        } catch (SQLException e) {
            System.out.println("[createViewForSongArtists]: Create View failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateArtistName(int id, String newName) {
        try {
            updateArtistName.setString(1, newName);
            updateArtistName.setInt(2, id);
            int affectedRecords = updateArtistName.executeUpdate();

            return affectedRecords == 1;

        } catch(SQLException e) {
            System.out.println("[updateArtistName]: Update failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateArtist(int id, String newName) {
        try {
            updateArtistById.setString(1, newName);
            updateArtistById.setInt(2, id);
            int affectedRecords = updateArtistById.executeUpdate();

            return affectedRecords == 1;

        } catch(SQLException e) {
            System.out.println("[updateArtist]: Update failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAlbum(int id, String newName, int newArtistId) {
        try {
            updateAlbumById.setString(1, newName);
            updateAlbumById.setInt(2, newArtistId);
            updateAlbumById.setInt(3, id);
            int affectedRecords = updateAlbumById.executeUpdate();

            return affectedRecords == 1;

        } catch(SQLException e) {
            System.out.println("[updateAlbum]: Update failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSong(int id, int track ,String newName, int newAlbumId) {
        try {
            updateSongById.setInt(1, track);
            updateSongById.setString(2, newName);
            updateSongById.setInt(3, newAlbumId);
            updateSongById.setInt(4, id);
            int affectedRecords = updateSongById.executeUpdate();

            return affectedRecords == 1;

        } catch(SQLException e) {
            System.out.println("[updateSongById]: Update failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public int insertArtist(String name) throws SQLException {
        queryArtist.setString(1, name);
        ResultSet results = queryArtist.executeQuery();
        if(results.next()) {
            return results.getInt(1);
        } else {
            // Insert the artist
            int id = getCount(TABLE_ARTISTS) + 1;
            insertIntoArtists.setInt(1, id);
            insertIntoArtists.setString(2, name);
            int affectedRows = insertIntoArtists.executeUpdate();

            if(affectedRows != 1) {
                throw new SQLException("[insertArtist]: Couldn't insert artist!");
            }

            return id;

//            ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
//            if(generatedKeys.next()) {
//                System.out.println(generatedKeys.getInt(1));
//                return generatedKeys.getInt(1);
//            } else {
//                throw new SQLException("[insertArtist]: Couldn't get id for artist");
//            }
        }
    }

    public int insertAlbum(String name, int artistId) throws SQLException {

        queryAlbum.setString(1, name);
        ResultSet results = queryAlbum.executeQuery();
        if(results.next()) {
            return results.getInt(1);
        } else {
            // Insert the album
            int id = getCount(TABLE_ALBUMS) + 1;
            insertIntoAlbums.setInt(1, id);
            insertIntoAlbums.setString(2, name);
            insertIntoAlbums.setInt(3, artistId);
            int affectedRows = insertIntoAlbums.executeUpdate();

            if(affectedRows != 1) {
                throw new SQLException("[insertAlbum]: Couldn't insert album!");
            }

            return id;

//            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
//            if(generatedKeys.next()) {
//                return generatedKeys.getInt(1);
//            } else {
//                throw new SQLException("[insertAlbum]: Couldn't get id for album");
//            }
        }
    }

    public void insertSong(String title, String artist, String album, int track) {

        try {
            conn.setAutoCommit(false);

            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);
            int id = getCount(TABLE_SONGS) + 1;
            insertIntoSongs.setInt(1, id);
            insertIntoSongs.setInt(2, track);
            insertIntoSongs.setString(3, title);
            insertIntoSongs.setInt(4, albumId);
            int affectedRows = insertIntoSongs.executeUpdate();
            if(affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("The song insert failed");
            }

        } catch(Exception e) {
            System.out.println("[insertSong]: Insert song exception: " + e.getMessage());
            e.printStackTrace();
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch(SQLException e2) {
                System.out.println("[insertSong]: Oh boy! Things are really bad! " + e2.getMessage());
                e2.printStackTrace();
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.setAutoCommit(true);
            } catch(SQLException e) {
                System.out.println("[insertSong]: Couldn't reset auto-commit! " + e.getMessage());
                e.printStackTrace();
            }

        }
    }

    public void insertSong(String title, int albumId, int track) {

        try {
            conn.setAutoCommit(false);

            int id = getCount(TABLE_SONGS) + 1;
            insertIntoSongs.setInt(1, id);
            insertIntoSongs.setInt(2, track);
            insertIntoSongs.setString(3, title);
            insertIntoSongs.setInt(4, albumId);
            int affectedRows = insertIntoSongs.executeUpdate();
            if(affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("The song insert failed");
            }

        } catch(Exception e) {
            System.out.println("[insertSong]: Insert song exception: " + e.getMessage());
            e.printStackTrace();
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch(SQLException e2) {
                System.out.println("[insertSong]: Oh boy! Things are really bad! " + e2.getMessage());
                e2.printStackTrace();
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.setAutoCommit(true);
            } catch(SQLException e) {
                System.out.println("[insertSong]: Couldn't reset auto-commit! " + e.getMessage());
                e.printStackTrace();
            }

        }
    }
}















