
package cristopher.vega.bl.entities;
import java.util.ArrayList;

public class Biblioteca {
    //Atributo

    private ArrayList<Playlist> listaPlaylist;  //Composicion
    private ArrayList<Cancion> listaCanciones;
    private ArrayList<Album> listaAlbum;
    //Constructor

    public Biblioteca() {
        this.listaPlaylist = new ArrayList<>();
        this.listaCanciones = new ArrayList<>();
        this.listaAlbum=new ArrayList<>();
    }


    //getters

    public ArrayList<Playlist> getListaPlaylist() {
        return listaPlaylist;
    }

    public ArrayList<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public ArrayList<Album> getListaAlbum() {
        return listaAlbum;
    }
    //Setters

    public void setListaPlaylist(ArrayList<Playlist> listaPlaylist) {

        this.listaPlaylist = listaPlaylist;
    }

    public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    public void setListaAlbum(ArrayList<Album> listaAlbum) {
        this.listaAlbum = listaAlbum;
    }





}
