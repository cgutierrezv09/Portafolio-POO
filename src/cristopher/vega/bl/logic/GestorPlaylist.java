package cristopher.vega.bl.logic;

import cristopher.vega.bl.dao.DAOCancion;
import cristopher.vega.bl.dao.DAOPlaylist;
import cristopher.vega.bl.entities.Cancion;
import cristopher.vega.bl.entities.Playlist;

import java.io.IOException;
import java.sql.SQLException;

public class GestorPlaylist {

    public static String registrarPlaylist(String nombre) throws SQLException, IOException, ClassNotFoundException {
        return DAOPlaylist.insertarPlaylist(new Playlist(nombre));
    }
    public static void verPlaylist() throws SQLException, IOException, ClassNotFoundException {
        DAOPlaylist.leerPlaylist();
    }

    public static String agregarCancionPlaylist(String idPlaylist, String idCancion) throws SQLException, IOException, ClassNotFoundException {
        return DAOPlaylist.insertarCancionPlaylist(idPlaylist,idCancion);
    }

    public static String eliminarCancionPlaylist(String idPlaylist, String idCancion) throws SQLException, IOException, ClassNotFoundException {
        return DAOPlaylist.eliminarCancionPlaylist(idPlaylist,idCancion);
    }

    public static String eliminarPlaylist(String idPlaylist)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOPlaylist.eliminarPlaylist(idPlaylist);
    }
    public static Playlist buscarPlaylist(String idPlaylist)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOPlaylist.buscarPlaylist(idPlaylist);
    }
}
