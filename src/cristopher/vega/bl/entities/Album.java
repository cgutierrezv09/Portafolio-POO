package cristopher.vega.bl.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Album {
    //Atributos
    private String nombreAlbum;
    private String nombreArtista;
    private int año;
    private ArrayList<Cancion> canciones;


    //Constructor

    public Album(String nombreAlbum, String nombreArtista, int año) {
        this.nombreAlbum = nombreAlbum;
        this.nombreArtista = nombreArtista;
        this.año = año;
        this.canciones = new ArrayList<>();
    }

    //Getters

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public int getAño() {
        return año;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    //Setters

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }

    public void agregarCancion(Cancion cancion){
        canciones.add(cancion);
    }


}
