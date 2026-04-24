package cristopher.vega.bl.dao;

import cristopher.vega.bl.entities.Album;
import cristopher.vega.bl.entities.Cancion;
import cristopher.vega.bl.entities.Playlist;
import cristopher.vega.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOAlbum {

    private static String statement;
    private static String query;

    public static String insertarAlbum(Album album) throws SQLException, IOException, ClassNotFoundException {

        statement = "INSERT INTO t_albumes (nombre_album, nombre_artista, anio) VALUES ('"
                + album.getNombreAlbum() + "', '"
                + album.getNombreArtista()  + "', '"
                + album.getAño()  + "')";

        Conector.getConexion().ejecutarStatement(statement);
        return "El album se registro con exito";
    }

    public static void leerAlbum() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_albumes";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        if (!resultado.next()) {
            System.out.println("No hay albumes registrados");
            return;
        }

        do {
            System.out.println("\n==== albumes ====");
            System.out.println("ID: " + resultado.getString("id"));
            System.out.println("\n Nombre: " + resultado.getString("nombre_album")
            +"\n Nombre del artista: " +resultado.getString("nombre_artista")
            +"\n Año de lanzamiento: " +resultado.getString("anio"));

            // Busca las canciones de esta playlist
            String idAlbum = resultado.getString("id");
            String queryCancion = "SELECT * " +
                    "FROM t_canciones c " +
                    "JOIN t_album_canciones ac ON c.id = ac.id_cancion " +
                    "WHERE ac.id_album = '" + idAlbum + "'";

            ResultSet canciones = Conector.getConexion().ejecutarQuery(queryCancion);

            //Imprime las canciones que ya estan agregada a esta playlist
            if (!canciones.next()) {
                System.out.println("  Este album no  no tiene canciones");
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

    public static String insertarCancionAlbum(String idAlbum, String idCancion)
            throws SQLException, IOException, ClassNotFoundException {


        String queryCancion = "SELECT autor FROM t_canciones WHERE id = '" + idCancion + "'";
        ResultSet resCancion = Conector.getConexion().ejecutarQuery(queryCancion);

        if (!resCancion.next()) {
            return "No se encontro la cancion";
        }
        String autorCancion = resCancion.getString("autor");


        String queryAlbum = "SELECT nombre_artista FROM t_albumes WHERE id = '" + idAlbum + "'";
        ResultSet resAlbum = Conector.getConexion().ejecutarQuery(queryAlbum);

        if (!resAlbum.next()) {
            return "No se encontro el album";
        }
        String artistaAlbum = resAlbum.getString("nombre_artista");


        if (!autorCancion.equalsIgnoreCase(artistaAlbum)) {
            return "No se puede agregar la cancion, el autor '" + autorCancion
                    + "' no coincide con el artista del album '" + artistaAlbum + "'";
        }


        String statement = "INSERT INTO t_album_canciones (id_album, id_cancion) VALUES ('"
                + idAlbum + "', '"
                + idCancion + "')";

        Conector.getConexion().ejecutarStatement(statement);
        return "Cancion agregada al album con exito";
    }

    public static String eliminarCancionAlbum(String idAlbum, String idCancion)
            throws SQLException, IOException, ClassNotFoundException {

        statement = "DELETE FROM t_album_canciones WHERE id_album = '"
                + idAlbum + "' AND id_cancion = '" + idCancion + "'";

        Conector.getConexion().ejecutarStatement(statement);
        return "Cancion eliminada de el album con exito";
    }

    public static String eliminarAlbum(String idAlbum)
            throws SQLException, IOException, ClassNotFoundException {

        statement = "DELETE FROM t_albumes WHERE id = '" + idAlbum + "'";


        int filas = Conector.getConexion().ejecutarStatementConConteo(statement);

        if (filas == 0) {
            return "No se encontro ningun album con ese ID";
        }else {
            return "Album eliminada con exito";
        }
    }

    public static Album buscarAlbum(String idAlbum)
            throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_albumes WHERE id = '" + idAlbum + "'";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        if (!resultado.next()) {
            return null;
        }

        Album album = new Album(
                resultado.getString("nombre_album"),
                resultado.getString("nombre_artista"),
                resultado.getInt("anio")
        );


        String queryCancion = "SELECT * FROM t_canciones c " +
                "JOIN t_album_canciones ac ON c.id = ac.id_cancion " +
                "WHERE ac.id_album = '" + idAlbum + "'";

        ResultSet canciones = Conector.getConexion().ejecutarQuery(queryCancion);

        while (canciones.next()) {
            Cancion c = new Cancion(
                    canciones.getString("nombre"),
                    canciones.getString("autor"),
                    canciones.getString("genero"),
                    canciones.getString("duracion")
            );
            album.agregarCancion(c);
        }

        return album;
    }
}
