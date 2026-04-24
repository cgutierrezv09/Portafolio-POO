package cristopher.vega.ui;


import cristopher.vega.ti.Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Menu {

    public static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

    public static void menuPrincipal() throws IOException, SQLException, ClassNotFoundException {
        int opcion;

        do {
            System.out.println("\n==== Menu Principal ====");
            System.out.println("1. Registrar cancion");
            System.out.println("2. Registrar playlist");
            System.out.println("3. Registrar album");
            System.out.println("4. Gestionar Canciones");
            System.out.println("5. Gestionar playlist");
            System.out.println("6. Gestionar album");
            System.out.println("7. Usar reproductor");
            System.out.println("8. Salir");
            opcion = Integer.parseInt(entrada.readLine());

            switch (opcion){

               case 1:
                   Controlador.registrarCancion();
                 break;

                case 2:
                    Controlador.registrarPlaylist();
                 break;

                case 3:
                    Controlador.registrarAlbum();
                 break;

                case 4:
                    menuCanciones();
                 break;

                case 5:
                    menuPlaylist();
                 break;

                case 6: menuAlbum();
                break;

                case 7:
                    menuReprodutor();
                    break;

                case 8: System.out.println("Saliendo del sistema ...");
                break;

                default: System.out.println("Opcion invalida");
            }

        }while (opcion!=8);
    }

    private static void menuPlaylist() throws IOException, SQLException, ClassNotFoundException {
        int opcion;
        do {
            System.out.println("\n==== Gestionar Playlist ====");
            System.out.println("1. Ver playlists");
            System.out.println("2. Agregar cancion a playlist");
            System.out.println("3. Eliminar cancion de playlist");
            System.out.println("4. Eliminar playlist");
            System.out.println("5. Volver");
            opcion = Integer.parseInt(entrada.readLine());

            switch (opcion) {
                case 1:
                    Controlador.verPlaylists();
                break;

                case 2:
                    Controlador.agregarCancionPlaylist();
                break;

                case 3:
                    Controlador.eliminarCancionDePlaylist();
                break;

                case 4:
                    Controlador.eliminarPlaylist();
                    break;

                case 5:
                    System.out.println("Volviendo al menu principal ....");
                    break;
                default: System.out.println("Opcion invalida");
            }
        } while (opcion != 5);
    }

    public static void menuCanciones() throws IOException, SQLException, ClassNotFoundException {

        int opcion;
        do {
            System.out.println("==== Canciones ==== ");
            System.out.println("Que deseas hacer?");
            System.out.println("1. Ver canciones \n 2. Eliminar canciones \n 3. Salir ");
            opcion = Integer.parseInt(entrada.readLine());

            switch (opcion){
           case 1:
                Controlador.verCancion();
                break;

            case 2:
                Controlador.eliminarCancion();
                break;

            case 3:
                System.out.println("Volviendo al menu principal ...");
                break;

                default:
                    System.out.println("Opcion invalida ");
            }
        }while (opcion!=3);

    }

    private static void menuAlbum() throws IOException, SQLException, ClassNotFoundException {
        int opcion;
        do {
            System.out.println("\n==== Gestionar Albumes ====");
            System.out.println("1. Ver Albumes");
            System.out.println("2. Agregar cancion a un ALbum");
            System.out.println("3. Eliminar cancion de el albuum");
            System.out.println("4. Eliminar album");
            System.out.println("5. Volver");
            opcion = Integer.parseInt(entrada.readLine());

            switch (opcion) {
                case 1:
                    Controlador.verAlbum();
                    break;

                case 2:
                    Controlador.agregarCancionAlbum();
                    break;

                case 3:
                    Controlador.eliminarCancionDeALbum();
                    break;

                case 4:
                    Controlador.eliminarAlbum();
                    break;

                case 5:
                    System.out.println("Volviendo al menu principal ....");
                    break;
                default: System.out.println("Opcion invalida");
            }
        } while (opcion != 5);
    }

    public static void menuReprodutor() throws IOException, SQLException, ClassNotFoundException {
        int opcion;

        do {
            System.out.println("==== Reproductor ====");
            System.out.println("1. Reproducir cancion");
            System.out.println("2. Cargar playlist");
            System.out.println("3. Cargar album");
            System.out.println("4. Siguiente cancion");
            System.out.println("5. Cancion anterior");
            System.out.println("6. Pausar / Reanudar");
            System.out.println("7. Detener y salir");
            opcion = Integer.parseInt(entrada.readLine());

            switch (opcion){
               case 1:
                    Controlador.reproducirCancion();
                    break;

                case 2:
                    Controlador.cargarPlaylist();
                    break;

                case 3:
                    Controlador.cargarAlbum();
                    break;

                case 4:
                    Controlador.siguiente();
                    break;

                case 5:
                    Controlador.anterior();
                    break;

                case 6:
                    Controlador.pausar();
                    break;

                case 7:
                    Controlador.detener();
                    break;

                default:
                    System.out.println("Opcion invalida");
            }
        }while (opcion!=7);

    }





}
