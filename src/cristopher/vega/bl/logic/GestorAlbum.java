package cristopher.vega.bl.logic;

import cristopher.vega.bl.dao.DAOAlbum;

import cristopher.vega.bl.dao.DAOPlaylist;
import cristopher.vega.bl.entities.Album;
import cristopher.vega.bl.entities.Playlist;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestorAlbum {


    public static String registrarAlbum(String nombreALbum, String autor, int añoLanzamiento) throws SQLException, IOException, ClassNotFoundException {
        return DAOAlbum.insertarAlbum(new Album(nombreALbum,autor,añoLanzamiento));
    }

    public static void verAlbum() throws SQLException, IOException, ClassNotFoundException {
        DAOAlbum.leerAlbum();
    }

    public static String agregarCancionAlbum(String idAlbum, String idCancion) throws SQLException, IOException, ClassNotFoundException {
        return DAOAlbum.insertarCancionAlbum(idAlbum,idCancion);
    }

    public static String eliminarCancionAlbum(String idALbum, String idCancion) throws SQLException, IOException, ClassNotFoundException {
        return DAOAlbum.eliminarCancionAlbum(idALbum,idCancion);
    }
    public static String eliminarAlbum(String idAlbum)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOAlbum.eliminarAlbum(idAlbum);
    }
    public static Album buscarAlbum(String idAlbum)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOAlbum.buscarAlbum(idAlbum);
    }
}
