import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class main {

    public static BufferedReader entrada=new BufferedReader(new InputStreamReader(System.in));


    //Registra una cancion en la biblioteca
    public static void registrarCancion(Biblioteca biblioteca)throws IOException{

        System.out.println("Ingrese el nombre de la cancion");
        String nombreCancion=entrada.readLine();

        System.out.println("Ingrese el autor");
        String autor=entrada.readLine();

        System.out.println("Ingrese el genero ");
        String genero=entrada.readLine();

        System.out.println("Ingrese la duracion");
        String duracion=entrada.readLine();

        Cancion cancion=new Cancion(nombreCancion,autor,genero,duracion);
        biblioteca.agregarCancion(cancion);
        System.out.println("Cancion: " +nombreCancion+ " registrada con exito ");

    }
    //Registra una playlist

    public static void registrarPlaylist(Biblioteca biblioteca)throws IOException{

        System.out.println("Ingrese el nombre de la playlist ");
        String nombre=entrada.readLine();

        Playlist nuevaPlaylist= new Playlist(nombre);

        //Lista de canciones

        ArrayList<Cancion> cancionesDisponibles= new ArrayList<>();

        //Recolectar las canciones ya guardadas

        for (Cancion c: biblioteca.getListaCanciones()){
            cancionesDisponibles.add(c);
        }
        if (cancionesDisponibles.isEmpty()){
            System.out.println("No hay canciones registradas en la biblioteca ");
        }else {
            System.out.println("==== Canciones disponibles ====");
            for (int i=0; i<cancionesDisponibles.size(); i++){
                System.out.println(i + ". " +cancionesDisponibles.get(i).getNombreCancion());
            }

            System.out.println("Cuantas canciones desea agregar a la playlist?");
            int cantidadCanciones=Integer.parseInt(entrada.readLine());

            for (int i=0; i < cantidadCanciones; i++){
                System.out.println("Seleccione el indice de la cancion: ");
                int indiceCancion=Integer.parseInt(entrada.readLine());

                if (indiceCancion < 0 || indiceCancion >= cancionesDisponibles.size()){
                    System.out.println("Indice invalido");
                    continue;
                }
                Cancion cancionElegida=cancionesDisponibles.get(indiceCancion);

                if (nuevaPlaylist.getListaCanciones().contains(cancionElegida)){
                    System.out.println("Esta cancion ya se encuentra en la playlist");
                }else{
                    nuevaPlaylist.agregarCancion(cancionElegida);
                    System.out.println("Cancion agregada: " +cancionElegida.getNombreCancion());
                }
            }

            biblioteca.agregarPlaylist(nuevaPlaylist);
            System.out.println("Playlist " +nombre+ " registrada con exito");

        }


    }

    //Metodo para crear un album

    public static void registrarAlbum(Biblioteca biblioteca) throws IOException{

        System.out.println("Ingrese el nombre del album ");
        String nombreAlbum=entrada.readLine();

        System.out.println("Ingrese el nombre del artista ");
        String autor=entrada.readLine();

        System.out.println("Ingrese el año de lanzamiento");
        int año=Integer.parseInt(entrada.readLine());

        Album album=new Album(nombreAlbum,autor,año);

        System.out.println("Ingrese el numero de canciones que va a contener el album ");
        int cantidadCanciones=Integer.parseInt(entrada.readLine());

        for (int i =0; i < cantidadCanciones; i++){

            System.out.println("Nombre de la cancion " + (i + 1) + ": ");
            String nombreCancion=entrada.readLine();

            System.out.println("Genero: ");
            String genero=entrada.readLine();

            System.out.println("Duracion: ");
            String duracion=entrada.readLine();

            Cancion cancion=new Cancion(nombreCancion,autor,genero,duracion);
            album.agregarCancion(cancion);
        }

        biblioteca.agregarAlbum(album);
        System.out.println("Album registrado con exito ");
    }
    //Metodo para ver la biblioteca

    public static void listaBiblioteca(Biblioteca biblioteca)throws IOException{

        System.out.println("==== Biblioteca ====");
        System.out.println("Cual deseas observar?");
        System.out.println("1. Lista de playlist  \n 2. Lista de canciones  \n 3. Lista de albumes ");
        int opccion=Integer.parseInt(entrada.readLine());

        switch (opccion){

            case 1:
                biblioteca.mostrarPlaylists();
                break;


            case 2:

                biblioteca.mostraCanciones();
                break;

            case 3:

                biblioteca.mostrarAlbum();
                break;


            default:
                System.out.println("Opcion invalida ");
        }

    }

    //Metodo para utilizar el reproductor

    public static void usarReproductor(Biblioteca biblioteca, Reproductor reproductor) throws IOException {

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
                    //Reproduccion de una cancion
                    ArrayList<Cancion> canciones=biblioteca.getListaCanciones();
                    if (canciones.isEmpty()){
                        System.out.println("No hay canciones en la biblioteca ");
                    }else {
                        System.out.println("==== Canciones ==== ");
                        for (int i=0; i< canciones.size(); i++){
                            System.out.println(i + ". " +canciones.get(i).getNombreCancion());
                        }
                        System.out.println("Seleccione el indice de la cancion");
                        int indiceCancion=Integer.parseInt(entrada.readLine());

                        if (indiceCancion >= 0 && indiceCancion< canciones.size()){
                            reproductor.reproducir(canciones.get(indiceCancion));
                        }else {
                            System.out.println("Indice invalido");
                        }

                    }

                    break;

                case 2:
                    ArrayList<Playlist> playlists=biblioteca.getListaPlaylist();
                    if (playlists.isEmpty()){
                        System.out.println("No hay playlist en la biblioteca");
                    }else {
                        System.out.println("==== Playlists ====");
                        for (int i=0; i <playlists.size(); i++){
                            System.out.println(i+ ". " + playlists.get(i).getNombrePlaylist());
                        }
                        System.out.println("Seleccione el indice de la playlist");
                        int indicePlaylist=Integer.parseInt(entrada.readLine());

                        if (indicePlaylist >= 0 && indicePlaylist < playlists.size()){
                            reproductor.cargarPlaylist(playlists.get(indicePlaylist));
                        }else {
                            System.out.println("Indice invalido");
                        }
                    }
                    break;


                case 3:

                    ArrayList<Album> albums=biblioteca.getListaAlbum();

                    if (albums.isEmpty()){
                        System.out.println("No hay ningun album en la biblioteca");
                    }else{
                        System.out.println("==== Albums ====");
                        for (int i=0; i < albums.size(); i++){
                            System.out.println(i + ". " +albums.get(i).getNombreAlbum());
                        }
                        System.out.println("Seleccione el indice del album ");
                        int IndiceAlbum=Integer.parseInt(entrada.readLine());

                        if (IndiceAlbum >= 0 && IndiceAlbum < albums.size()){
                            reproductor.cargarAlbum(albums.get(IndiceAlbum));
                        }else {
                            System.out.println("Indice invalido ");
                        }
                    }
                    break;

                case 4:
                    reproductor.siguiente();
                    break;

                case 5:
                    reproductor.anterior();
                    break;

                case 6:
                    if (reproductor.isPausa()){
                        reproductor.reanudar();
                    }else {
                        reproductor.pausar();
                    }
                    break;


                case 7:
                    reproductor.detener();
                    System.out.println("Saliendo del reproductor");
                    break;


                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 7);
    }

    //metodo de buscar y eliminar
     public static void buscarYeliminar(Biblioteca biblioteca, Reproductor reproductor)throws IOException{

         System.out.println("==== Buscador ==== ");
         System.out.println("Que deseas buscar?");
         System.out.println("1. Cancion \n 2. Playlist \n 3. Album ");
         int opcion=Integer.parseInt(entrada.readLine());

         switch (opcion){

             case 1:
                 System.out.println("Ingrese el nombre de la cancion a buscar");
                 String cancionBuscar=entrada.readLine();

                 Cancion cancionEncontrada=biblioteca.buscarCancion(cancionBuscar);

                 if (cancionEncontrada!=null){
                     System.out.println("Cancion encontrada");

                     System.out.println("Que deseas hacer? ");
                     System.out.println("1. Reproducir  \n 2. Eliminar  \n 3. Agregar a una playlist");
                     int opcionSubmenu=Integer.parseInt(entrada.readLine());

                     switch (opcionSubmenu){

                         case 1:
                             reproductor.reproducir(cancionEncontrada);
                             break;

                         case 2:
                             biblioteca.eliminarCancion(cancionEncontrada.getNombreCancion());
                             System.out.println("Cancion eliminada " +cancionEncontrada.getNombreCancion());
                             break;

                         case 3:
                             // Muestra las playlists disponibles
                             ArrayList<Playlist> playlists = biblioteca.getListaPlaylist();

                             if (playlists.isEmpty()) {
                                 System.out.println(" No hay playlists disponibles");
                             } else {
                                 System.out.println("==== Playlists disponibles ====");
                                 for (int i = 0; i < playlists.size(); i++) {
                                     System.out.println(i + ". " + playlists.get(i).getNombrePlaylist());
                                 }
                                 System.out.println("Seleccione el indice de la playlist: ");
                                 int indicePlaylist = Integer.parseInt(entrada.readLine());

                                 if (indicePlaylist >= 0 && indicePlaylist < playlists.size()) {
                                     playlists.get(indicePlaylist).agregarCancion(cancionEncontrada);
                                     System.out.println("Cancion agregada a la playlist: " + playlists.get(indicePlaylist).getNombrePlaylist());
                                 } else {
                                     System.out.println(" Indice invalido");
                                 }
                             }
                             break;

                         default:
                             System.out.println("Opcion invalida");
                     }
                 }else {
                     System.out.println("Cancion no encontrada");
                 }
                 break;

             case 2:
                 System.out.println("Ingrese la playlist a buscar");
                 String nombrePlaylist=entrada.readLine();

                 Playlist playlistBuscar= biblioteca.buscarPlaylist(nombrePlaylist);

                 if (playlistBuscar!= null){
                     System.out.println("Playlist encontrada");

                     System.out.println("Que deseas hacer?");
                     System.out.println("1. Reproducir playlist \n 2. Eliminar playlist");
                     int opcionSub=Integer.parseInt(entrada.readLine());

                     switch (opcionSub){

                         case 1:
                             reproductor.cargarPlaylist(playlistBuscar);
                             break;

                         case 2:

                             biblioteca.eliminarPlaylist(playlistBuscar.getNombrePlaylist());
                             System.out.println("Playlist eliminada: "+playlistBuscar.getNombrePlaylist());
                             break;

                         default:
                             System.out.println("Opcion invalida");
                     }
                 }else {
                     System.out.println("Playlist no encontrada ");
                 }
                 break;

             case 3:
                 System.out.println("Ingrese el album a buscar");
                 String nombreAlbum=entrada.readLine();

                 Album albumBuscar= biblioteca.buscarAlbum(nombreAlbum);

                 if (albumBuscar!= null){
                     System.out.println("Album encontrado");

                     System.out.println("Que deseas hacer?");
                     System.out.println("1. Reproducir album \n 2. Eliminar album");
                     int opcionSubAlbum=Integer.parseInt(entrada.readLine());

                     switch (opcionSubAlbum){
                         case 1:
                             reproductor.cargarAlbum(albumBuscar);
                             break;

                         case 2:
                             biblioteca.eliminarAlbum(albumBuscar.getNombreAlbum());
                             System.out.println("Album eliminado: " +albumBuscar.getNombreAlbum());
                             break;


                         default:
                             System.out.println("Opcion invalida");
                     }

                 }else {
                     System.out.println("Album no encontrado");
                 }
               break;
         }

     }


    public static void main(String[] args)throws IOException{

        Biblioteca biblioteca=new Biblioteca();
        Reproductor reproductor= new Reproductor();
        int opcion;


        do {
            System.out.println("\n==== Menu Principal ====");
            System.out.println("1. Registrar cancion");
            System.out.println("2. Registrar playlist");
            System.out.println("3. Registrar album");
            System.out.println("4. Ver biblioteca");
            System.out.println("5. Buscar");
            System.out.println("6. Usar reproductor");
            System.out.println("7. Salir");
            opcion = Integer.parseInt(entrada.readLine());

            switch (opcion){
                case 1:
                    registrarCancion(biblioteca);
                    break;

                case 2:
                    registrarPlaylist(biblioteca);
                    break;

                case 3:
                    registrarAlbum(biblioteca);
                    break;

                case 4:
                    listaBiblioteca(biblioteca);
                    break;

                case 5:
                    buscarYeliminar(biblioteca,reproductor);
                    break;

                case 6:
                    usarReproductor(biblioteca,reproductor);
                    break;

                case 7:
                    System.out.println("Saliendo del sistema ...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        }while (opcion!=7);


    }
}
