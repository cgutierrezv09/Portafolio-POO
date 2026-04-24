package cristopher.vega.bl.dao;

import cristopher.vega.bl.entities.Cancion;
import cristopher.vega.bl.entities.Playlist;
import cristopher.vega.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOPlaylist {

    private static String statement;
    private static String query;

    //registra una playlist
    public static String insertarPlaylist(Playlist playlist) throws SQLException, IOException, ClassNotFoundException {

        statement = "INSERT INTO t_playlists (nombre_playlist) VALUES ('"
                + playlist.getNombrePlaylist() + "')";

        Conector.getConexion().ejecutarStatement(statement);
        return "La playlist se registro con exito";
    }

    //lee las playlist y las canciones agregadas a esta
    public static void leerPlaylist() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_playlists";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        if (!resultado.next()) {
            System.out.println("No hay playlists registradas");
            return;
        }

        do {
            System.out.println("\n==== Playlist ====");
            System.out.println("ID: " + resultado.getString("id"));
            System.out.println("Nombre: " + resultado.getString("nombre_playlist"));

            // Busca las canciones de esta playlist
            String idPlaylist = resultado.getString("id");
            String queryCancion = "SELECT * " +
                    "FROM t_canciones c " +
                    "JOIN t_playlist_canciones pc ON c.id = pc.id_cancion " +
                    "WHERE pc.id_playlist = '" + idPlaylist + "'";

            ResultSet canciones = Conector.getConexion().ejecutarQuery(queryCancion);

            //Imprime las canciones que ya estan agregada a esta playlist
            if (!canciones.next()) {
                System.out.println("  Esta playlist no tiene canciones");
            } else {
                System.out.println("  Canciones:");
                do {
                    System.out.println("  - " + canciones.getString("nombre")
                            + " | " + canciones.getString("autor")
                            + " | " + canciones.getString("genero")
                            + " | " + canciones.getString("duracion"));
                } while (canciones.next());
            }

        } while (resultado.next());
    }

    //Inserta una cancion a la playlist
    public static String insertarCancionPlaylist(String idPlaylist, String idCancion)
            throws SQLException, IOException, ClassNotFoundException {

        String statement = "INSERT INTO t_playlist_canciones (id_playlist, id_cancion) VALUES ('"
                + idPlaylist + "', '"
                + idCancion  + "')";

        Conector.getConexion().ejecutarStatement(statement);
        return "Cancion agregada a la playlist con exito";
    }

    public static String eliminarCancionPlaylist(String idPlaylist, String idCancion)
            throws SQLException, IOException, ClassNotFoundException {

        statement = "DELETE FROM t_playlist_canciones WHERE id_playlist = '"
                + idPlaylist + "' AND id_cancion = '" + idCancion + "'";

        Conector.getConexion().ejecutarStatement(statement);
        return "Cancion eliminada de la playlist con exito";
    }

    public static String eliminarPlaylist(String idPlaylist)
            throws SQLException, IOException, ClassNotFoundException {

        statement = "DELETE FROM t_playlists WHERE id = '" + idPlaylist + "'";


        int filas = Conector.getConexion().ejecutarStatementConConteo(statement);

        if (filas == 0) {
            return "No se encontro ninguna playlist con ese ID";
        }else {
            return "Playlist eliminada con exito";
        }
    }

    public static Playlist buscarPlaylist(String idPlaylist)
            throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_playlists WHERE id = '" + idPlaylist + "'";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        if (!resultado.next()) {
            return null;
        }

        Playlist playlist = new Playlist(resultado.getString("nombre_playlist"));


        String queryCancion = "SELECT * FROM t_canciones c " +
                "JOIN t_playlist_canciones pc ON c.id = pc.id_cancion " +
                "WHERE pc.id_playlist = '" + idPlaylist + "'";

        ResultSet canciones = Conector.getConexion().ejecutarQuery(queryCancion);

        while (canciones.next()) {
            Cancion c = new Cancion(
                    canciones.getString("nombre"),
                    canciones.getString("autor"),
                    canciones.getString("genero"),
                    canciones.getString("duracion")
            );
            playlist.agregarCancion(c);
        }

        return playlist;
    }



}
