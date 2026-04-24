
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

    //Metodo para agregar una playlist

    public void agregarPlaylist(Playlist playlist){

        listaPlaylist.add(playlist);
    }

    //Metodo para agregar album

    public void agregarAlbum(Album album){
        listaAlbum.add(album);
    }

    //Metodo para agregar una cancion a la biblioteca

    public  void agregarCancion(Cancion cancion){

        listaCanciones.add(cancion);
    }

    //Metodo para buscar la cancion de la biblioteca

    public Cancion buscarCancion(String nombre){

        for (int i =0; i < listaCanciones.size(); i++){
            if (listaCanciones.get(i).getNombreCancion().equals(nombre)){

                return listaCanciones.get(i);
            }
        }
        return null;
    }

    //metodo para eliminar una cancion

    public void eliminarCancion(String nombre){
        Cancion encontrado=null;

        for (Cancion c: listaCanciones){
            if (c.getNombreCancion().equals(nombre)){
                encontrado=c;
                break;
            }
        }

        if (encontrado!=null){
            listaCanciones.remove(encontrado);
            System.out.println("cristopher.vega.bl.entities.Cancion eliminada: " +nombre);
        }else {
            System.out.println("No se encontro la cancion");
        }
    }

    //Metodo para buscar la playlist

    public Playlist buscarPlaylist(String nombre){
        for (int i=0; i<listaPlaylist.size(); i++){
            if (listaPlaylist.get(i).getNombrePlaylist().equals(nombre)){
                return listaPlaylist.get(i);
            }
        }
        return null;
    }

    //Metodo para eliminar una playlist

    public void eliminarPlaylist(String nombre){
        Playlist encontrado=null;

        for (Playlist p: listaPlaylist){
            if (p.getNombrePlaylist().equals(nombre)){
                encontrado=p;
                break;
            }
        }

        if (encontrado!=null){
            listaPlaylist.remove(encontrado);
            System.out.println("cristopher.vega.bl.entities.Playlist eliminada : " +nombre);
        }else {
            System.out.println("No se encontro la playlist");
        }
    }

    //Metodo para buscar el album

    public Album buscarAlbum(String nombre){
        for (int i=0; i< listaAlbum.size(); i++){
            if (listaAlbum.get(i).getNombreAlbum().equals(nombre)){
                return listaAlbum.get(i);
            }
        }
        return null;
    }

    //Metodo para eliminar album
    public void eliminarAlbum(String nombre){
        Album encontrado=null;

        for (Album a: listaAlbum){
            if (a.getNombreAlbum().equals(nombre)){
                encontrado=a;
                break;
            }
        }

        if (encontrado!=null){
            listaAlbum.remove(encontrado);
            System.out.println("cristopher.vega.bl.bl.Album eliminado: " +nombre);
        }else {
            System.out.println("No se encontro el album");
        }
    }
    // Mostrar todas las playlists
    public void mostrarPlaylists() {
        if (listaPlaylist.isEmpty()){
            System.out.println("No hay playlist registradas");
        }else {
            for (Playlist p : listaPlaylist) {
                System.out.println("==== Lista de playlists= === \n" +p.getNombrePlaylist());
            }
        }


    }

    //Metodo para mostrar todas las canciones

    public void mostraCanciones(){
        if (listaCanciones.isEmpty()){
            System.out.println("No hay canciones registradas");
        }else {
            for (Cancion c : listaCanciones) {
                System.out.println("==== Lista de canciones ==== \n" + c.getNombreCancion());
            }
        }
    }

    //Metodo para mostrar todos los album

    public void mostrarAlbum() {
        if (listaAlbum.isEmpty()) {
            System.out.println("No hay albumes registrados");
        } else{
            for (Album a : listaAlbum) {
                System.out.println("==== Lista de albumes ==== \n" + a.getNombreAlbum());
            }
        }

    }



}
