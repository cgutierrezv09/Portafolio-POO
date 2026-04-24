package cristopher.vega.bl.dao;

import cristopher.vega.bl.entities.Cancion;
import cristopher.vega.dl.Conector;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOCancion {

    private static String statement;
    private static String query;

    public static String insertarCancion(Cancion cancion) throws SQLException, IOException, ClassNotFoundException {

         statement = "INSERT INTO t_canciones (nombre, autor, genero, duracion) VALUES ('"
                + cancion.getNombreCancion() + "', '"
                + cancion.getAutor()         + "', '"
                + cancion.getGenero()         + "', '"
                + cancion.getDuracion()       + "')";

        Conector.getConexion().ejecutarStatement(statement);
        return "La cancion se registro con exito";
    }

    public static void leerCancion() throws SQLException, IOException, ClassNotFoundException {
        query="SELECT *FROM t_canciones";
        ResultSet resultado=Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()){
            System.out.println("No hay canciones registradas");
            return;
        }

        do {
            System.out.println("==== Canciones ====");
            System.out.println("ID: " +resultado.getString("id")
            +"\n Nombre: " +resultado.getString("nombre") + "\n Autor: " +resultado.getString("autor")
            + "\n Genero: " +resultado.getString("genero") + "\n Duracion: " +resultado.getString("duracion"));
        }while (resultado.next());
    }


    public static String eliminarCancion(String idEliminar) throws SQLException, IOException, ClassNotFoundException {

        statement = "DELETE FROM t_canciones WHERE id = '" + idEliminar + "'";
        int filas = Conector.getConexion().ejecutarStatementConConteo(statement);

        if (filas == 0) {
            return "No se encontro ninguna cancion con ese ID";
        }else {
            return "Cancion eliminada con exito";
        }
    }

    public static Cancion buscarCancion(String idCancion) throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_canciones WHERE id = '" + idCancion + "'";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        if (!resultado.next()) {
            return null;
        }

        return new Cancion(
                resultado.getString("nombre"),
                resultado.getString("autor"),
                resultado.getString("genero"),
                resultado.getString("duracion")
        );
    }

    public static void leerCancionesDescargadas() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT * FROM t_canciones";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        boolean hayDescargadas = false;

        if (!resultado.next()) {
            System.out.println("No hay canciones registradas");
            return;
        }

        do {
            String nombreCancion = resultado.getString("nombre");
            String rutaArchivo = "src/cristopher/vega/music/" + nombreCancion + ".wav";

            // Si el archivo existe, la muestra
            if (new File(rutaArchivo).exists()) {
                if (!hayDescargadas) {
                    System.out.println("==== Canciones Descargadas ====");
                    hayDescargadas = true;
                }
                System.out.println("ID: "       + resultado.getInt("id"));
                System.out.println("Nombre: "   + nombreCancion);
                System.out.println("Autor: "    + resultado.getString("autor"));
                System.out.println("Genero: "   + resultado.getString("genero"));
                System.out.println("Duracion: " + resultado.getString("duracion"));
                System.out.println("------------------------------");
            }
        } while (resultado.next());

        if (!hayDescargadas) {
            System.out.println("No hay canciones descargadas en la carpeta music");
        }
    }
}
