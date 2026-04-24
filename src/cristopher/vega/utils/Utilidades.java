package cristopher.vega.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilidades {

    public static String[] getPropierties() throws IOException {
        String [] propiedades= new String[5];
        Properties lectura= new Properties();
        String ruta="src/cristopher/vega/utils/bd.properties";
        try{
            lectura.load(new FileInputStream(ruta));
            propiedades[0]=lectura.getProperty("driver");
            propiedades[1]=lectura.getProperty("server");
            propiedades[2]=lectura.getProperty("database");
            propiedades[3]=lectura.getProperty("user");
            propiedades[4]=lectura.getProperty("password");
            return propiedades;

        } catch (IOException e) {
            System.out.println("Sucedio un error al cargar las credenciales " );
            throw e;
        }
    }
}
