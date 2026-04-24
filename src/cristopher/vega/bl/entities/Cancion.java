package cristopher.vega.bl.entities;

public class Cancion {

    //atributos

    private String nombreCancion;
    private String autor;
    private String genero;
    private String duracion;

    //Constructor
    public Cancion(String nombreCancion, String autor, String genero,  String duracion) {
        this.nombreCancion = nombreCancion;
        this.autor = autor;
        this.genero = genero;

        this.duracion = duracion;
    }

    //getters


    public String getNombreCancion() {
        return nombreCancion;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }



    public String getDuracion() {
        return duracion;
    }

    //Setters


    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }



    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    //to String



    public String toString() {
        return "Nombre de la cancion: " +nombreCancion+ "\n Autor: " +autor+ "\n Genero: " +genero+
                "\n Duracion: " +duracion;

    }
}
