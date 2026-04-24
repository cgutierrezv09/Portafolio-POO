package cristopher.vega.ti;

import cristopher.vega.bl.entities.*;
import cristopher.vega.bl.logic.GestorAlbum;
import cristopher.vega.bl.logic.GestorCancion;
import cristopher.vega.bl.logic.GestorPlaylist;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controlador {
    public static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
    public static Reproductor reproductor = new Reproductor();

    //Metodos para Registrar cancion y mostrarla en consola
    public static void registrarCancion() throws IOException, SQLException, ClassNotFoundException {

        System.out.println("Ingrese el nombre de la cancion");
        String nombreCancion = entrada.readLine();

        System.out.println("Ingrese el autor");
        String autor = entrada.readLine();

        System.out.println("Ingrese el genero ");
        String genero = entrada.readLine();

        System.out.println("Ingrese la duracion");
        String duracion = entrada.readLine();

        System.out.println(GestorCancion.registrarCancion(nombreCancion,autor,genero,duracion));
    }

    public static void verCancion() throws IOException, SQLException, ClassNotFoundException {
        GestorCancion.verCancion();

    }

    public static void eliminarCancion() throws IOException, SQLException, ClassNotFoundException {
        verCancion();

        System.out.println("Ingrese el ID de la cancion a eliminar");
        String idEliminar=entrada.readLine();

        System.out.println(GestorCancion.eliminarCancion(idEliminar));
    }

    public static void reproducirCancion() throws SQLException, IOException, ClassNotFoundException {

        int opcion;

        do {

            System.out.println("Que deseas reproducir?");
            System.out.println("1. Cancion normal \n 2. Cancion cargada  \n 3. Salir");
            opcion=Integer.parseInt(entrada.readLine());
            switch (opcion){

                case 1:
                    GestorCancion.verCancion();

                    System.out.println("Ingrese el ID de la cancion a reproducir");
                    String idCancion=entrada.readLine();

                    Cancion cancion= GestorCancion.buscarCancion(idCancion);
                    if (cancion==null){
                        System.out.println("No se encontro la cancion");
                        return;
                    }else {
                        reproductor.reproducir(cancion);
                    }
                    break;

                case 2:
                    GestorCancion.verCancionesDescargadas();

                    System.out.println("Ingrese el ID de la cancion");
                    String idCancion2 = entrada.readLine();

                    Cancion cancion2 = GestorCancion.buscarCancion(idCancion2);
                    if (cancion2 == null) {
                        System.out.println("No se encontro la cancion");
                    } else {
                        reproductor.reproducirConAudio(cancion2);
                    }
                    break;


                case 3:
                    reproductor.detener();
                    System.out.println("Saliendo al menu principal ...");
                    break;

                default:
                    System.out.println("Opcion invalida");

            }
        }while (opcion!= 3);


    }

    //Metodos de playlist para registrar una, ver las playlist y agregarle canciones a la playlist
    public static void registrarPlaylist() throws IOException, SQLException, ClassNotFoundException {
        System.out.print("Ingrese el nombre de la playlist: ");
        String nombre = entrada.readLine();

        System.out.println(GestorPlaylist.registrarPlaylist(nombre));

    }
    public static void verPlaylists() throws IOException, SQLException, ClassNotFoundException {
        GestorPlaylist.verPlaylist();

    }
    public static void agregarCancionPlaylist() throws IOException, SQLException, ClassNotFoundException {

        verPlaylists();
        verCancion();

        System.out.println("Igrese el ID de su playlist");
        String idPlaylist=entrada.readLine();

        System.out.println("Ingrese el ID de la cancion");
        String idCancion=entrada.readLine();


        Playlist playlist = GestorPlaylist.buscarPlaylist(idPlaylist);
        if (playlist == null) {
            System.out.println("La playlist no existe");
            return;
        }


        Cancion cancion = GestorCancion.buscarCancion(idCancion);
        if (cancion == null) {
            System.out.println("La cancion no existe");
            return;
        }

        System.out.println(GestorPlaylist.agregarCancionPlaylist(idPlaylist,idCancion));
    }

    public static void eliminarCancionDePlaylist() throws IOException, SQLException, ClassNotFoundException {
        verPlaylists();
        verCancion();

        System.out.println("Ingrese el ID de su playlist");
        String idPlaylist=entrada.readLine();

        System.out.println("Ingrese el ID de la cancion a eliminar");
        String idCancion=entrada.readLine();

        Playlist playlist = GestorPlaylist.buscarPlaylist(idPlaylist);
        if (playlist == null) {
            System.out.println("La playlist no existe");
            return;
        }


        Cancion cancion = GestorCancion.buscarCancion(idCancion);
        if (cancion == null) {
            System.out.println("La cancion no existe");
            return;
        }

        System.out.println(GestorPlaylist.eliminarCancionPlaylist(idPlaylist,idCancion));
    }
    public static void eliminarPlaylist() throws IOException, SQLException, ClassNotFoundException {

        System.out.println("Ingrese el ID de la playlist a eliminar");
        String idPlaylist = entrada.readLine();

        Playlist playlist = GestorPlaylist.buscarPlaylist(idPlaylist);
        if (playlist == null) {
            System.out.println("La playlist no existe");
            return;
        }


        System.out.println(GestorPlaylist.eliminarPlaylist(idPlaylist));
    }
    public static void cargarPlaylist() throws IOException, SQLException, ClassNotFoundException {

        verPlaylists();

        System.out.println("Ingrese el ID de la playlist a cargar");
        String idPlaylist = entrada.readLine();

        Playlist playlist = GestorPlaylist.buscarPlaylist(idPlaylist);
        if (playlist == null) {
            System.out.println("No se encontro la playlist");
            return;
        }


        reproductor.cargarPlaylist(playlist);
        System.out.println("Playlist cargada. Reproduciendo primera cancion...");
        Cancion primera = playlist.getListaCanciones().get(0);

        String ruta = "src/cristopher/vega/music/" + primera.getNombreCancion() + ".wav";

        if (new File(ruta).exists()) {
            reproductor.reproducirConAudio(primera);
        } else {
            reproductor.reproducir(primera);
        }
    }

    //Registra un album
     public static void registrarAlbum() throws IOException, SQLException, ClassNotFoundException {
         System.out.println("Ingrese el nombre del album");
         String nombreAlbum = entrada.readLine();

         System.out.println("Ingrese el autor");
         String autor = entrada.readLine();

         System.out.println("Ingrese el año de lanzamiento");
         Integer añoLanzamiento= Integer.parseInt(entrada.readLine());

         System.out.println(GestorAlbum.registrarAlbum(nombreAlbum,autor,añoLanzamiento));
     }

    public static void verAlbum() throws IOException, SQLException, ClassNotFoundException {
        GestorAlbum.verAlbum();

    }

    public static void agregarCancionAlbum() throws IOException, SQLException, ClassNotFoundException {
        verAlbum();
        verCancion();

        System.out.println("Ingrese el ID de su album");
        String idAlbum=entrada.readLine();

        System.out.println("Ingrese el ID de la cancion");
        String idCancion=entrada.readLine();


        Album album = GestorAlbum.buscarAlbum(idAlbum);
        if (album == null) {
            System.out.println("El album no existe");
            return;
        }

        Cancion cancion = GestorCancion.buscarCancion(idCancion);
        if (cancion == null) {
            System.out.println("La cancion no existe");
            return;
        }

        System.out.println(GestorAlbum.agregarCancionAlbum(idAlbum,idCancion));
    }
    public static void eliminarCancionDeALbum() throws IOException, SQLException, ClassNotFoundException {
        verAlbum();

        System.out.println("Ingrese el ID de su album");
        String idAlbum=entrada.readLine();

        System.out.println("Ingrese el ID de la cancion a eliminar");
        String idCancion=entrada.readLine();

        Album album = GestorAlbum.buscarAlbum(idAlbum);
        if (album == null) {
            System.out.println("El album no existe");
            return;
        }

        Cancion cancion = GestorCancion.buscarCancion(idCancion);
        if (cancion == null) {
            System.out.println("La cancion no existe");
            return;
        }

        System.out.println(GestorAlbum.eliminarCancionAlbum(idAlbum,idCancion));
    }

    public static void eliminarAlbum() throws IOException, SQLException, ClassNotFoundException {

        verAlbum();

        System.out.println("Ingrese el ID de el album a eliminar");
        String idAlbum = entrada.readLine();

        Album album = GestorAlbum.buscarAlbum(idAlbum);
        if (album == null) {
            System.out.println("El album no existe");
            return;
        }

        System.out.println(GestorAlbum.eliminarAlbum(idAlbum));
    }

    public static void cargarAlbum() throws IOException, SQLException, ClassNotFoundException {

        verAlbum();

        System.out.println("Ingrese el ID de el album a cargar");
        String idAlbum = entrada.readLine();

        Album album = GestorAlbum.buscarAlbum(idAlbum);
        if (album == null) {
            System.out.println("No se encontro el album");
            return;
        }


        reproductor.cargarAlbum(album);
        System.out.println("ALbum cargada. Reproduciendo primera cancion...");
        Cancion primera = album.getCanciones().get(0);

        String ruta = "src/cristopher/vega/music/" + primera.getNombreCancion() + ".wav";

        if (new File(ruta).exists()) {
            reproductor.reproducirConAudio(primera);
        } else {
            reproductor.reproducir(primera);
        }
    }

    public static void siguiente(){
        reproductor.siguiente();
    }

    public static void anterior(){
        reproductor.anterior();
    }

    public static void pausar(){
        if (reproductor.isPausa()) {
            reproductor.reanudar();
        } else {
            reproductor.pausar();
        }
    }

    public static void detener(){
        reproductor.detener();
    }


}
