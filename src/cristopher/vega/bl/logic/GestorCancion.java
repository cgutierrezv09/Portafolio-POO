package cristopher.vega.bl.logic;

import cristopher.vega.bl.dao.DAOCancion;
import cristopher.vega.bl.entities.Cancion;

import java.io.IOException;
import java.sql.SQLException;

public class GestorCancion {

    public static String registrarCancion(String nombre,String autor,String genero,String duracion) throws SQLException, IOException, ClassNotFoundException {
        return DAOCancion.insertarCancion(new Cancion(nombre,autor,genero,duracion));
    }

    public static void verCancion() throws SQLException, IOException, ClassNotFoundException {
        DAOCancion.leerCancion();
    }

    public static String eliminarCancion(String idEliminar) throws SQLException, IOException, ClassNotFoundException {
        return DAOCancion.eliminarCancion(idEliminar);
    }
    public static Cancion buscarCancion(String idCancion)
            throws SQLException, IOException, ClassNotFoundException {
        return DAOCancion.buscarCancion(idCancion);
    }

    public static void verCancionesDescargadas() throws SQLException, IOException, ClassNotFoundException {
        DAOCancion.leerCancionesDescargadas();
    }
}
