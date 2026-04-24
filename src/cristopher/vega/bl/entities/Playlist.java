package cristopher.vega.bl.entities;

import java.util.ArrayList;

public class Playlist {

    //Atributos
    private String nombrePlaylist;
    private ArrayList <Cancion> listaCanciones;

    //Constructor
    public Playlist(String nombrePlaylist) {
        this.nombrePlaylist = nombrePlaylist;
        this.listaCanciones = new ArrayList<>();
    }

    //Getters
    public String getNombrePlaylist() {
        return nombrePlaylist;
    }

    public ArrayList<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    //Setters
    public void setNombrePlaylist(String nombrePlaylist) {
        this.nombrePlaylist = nombrePlaylist;
    }

    public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    //To String
    public String toString() {
        return "Nombre de la playlist : " + nombrePlaylist + "\n Canciones: " + listaCanciones;
    }

    //Metodo para agregar una cancion a la playlist
    public void agregarCancion(Cancion cancion){
        listaCanciones.add(cancion);
    }




}
