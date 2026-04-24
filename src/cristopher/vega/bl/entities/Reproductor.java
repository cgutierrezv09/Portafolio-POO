package cristopher.vega.bl.entities;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;

public class Reproductor {

    //Atributos
    private Cancion cancionActual; //composicion
    private Playlist playlistActual;  //Composicion
    private Album albumActual; //Composicion
    private boolean pausa;
    private boolean repeticion;
    private int indice;
    private Clip clip;
    private static final String RUTA_BASE = "src/cristopher/vega/music/";

    //Constructor

    public Reproductor() {
        this.cancionActual = null;
        this.playlistActual = null;
        this.albumActual = null;
        this.indice = 0;
        this.pausa = false;
        this.repeticion = false;
    }


    //Getters

    public Cancion getCancionActual() {
        return cancionActual;
    }

    public Playlist getPlaylistActual() {
        return playlistActual;
    }

    public int getIndice() {
        return indice;
    }

    public boolean isPausa() {
        return pausa;
    }

    public boolean isRepeticion() {
        return repeticion;
    }

    //Setters


    public void setCancionActual(Cancion cancionActual) {
        this.cancionActual = cancionActual;
    }

    public void setPlaylistActual(Playlist playlistActual) {
        this.playlistActual = playlistActual;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    public void setRepeticion(boolean repeticion) {
        this.repeticion = repeticion;
    }


    //To String


    public String toString() {
        return "Cancion: " +cancionActual+  "\n Playlist: " +playlistActual ;
    }

    //Metodo para reproducir una cancion

    public void reproducirConAudio(Cancion cancion) {

        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }

        cancionActual = cancion;
        pausa = false;
        System.out.println("▶ Reproduciendo: " + cancionActual.getNombreCancion());

        try {
            File archivo = new File(RUTA_BASE + cancion.getNombreCancion().trim() + ".wav");
            AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error al reproducir: " + e.getMessage());
        }
    }

    public void reproducir(Cancion cancion) {

        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            clip = null;
        }

        cancionActual = cancion;
        pausa = false;
        System.out.println("▶ Reproduciendo: " + cancionActual.getNombreCancion()
                + " - " + cancionActual.getAutor());
    }

    //Metodo para pausar una cancion

    public void pausar() {
        if (cancionActual != null) {
            pausa = true;
            if (clip != null && clip.isRunning()) {
                clip.stop(); // si está reproduciendo audio, lo pausa
            }
            System.out.println("⏸ Cancion pausada: " + cancionActual.getNombreCancion());
        }
    }

    //Metodo para reanudar la cancion

    public void reanudar() {
        if (pausa) {
            pausa = false;
            if (clip != null) {
                clip.start(); // si hay audio, lo reanuda
            }
            System.out.println("▶ Cancion reanudada: " + cancionActual.getNombreCancion());
        }
    }

    //Metodo para detener la cancion

    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
        cancionActual = null;
        playlistActual = null;
        albumActual = null;
        pausa = false;
        indice = 0;
        System.out.println("⏹ Reproduccion detenida");
    }

    //carga una playlist al reproductor

    public void cargarPlaylist(Playlist playlist){
        playlistActual=playlist;
        indice=0;

        System.out.println("Se esta reproduciendo la playlist: " +playlist.getNombrePlaylist());
    }


    //Cargar un album al reproductor

    public void cargarAlbum(Album album){
        albumActual=album;
        indice=0;

        System.out.println("Se esta reproduciendo el album: " +album.getNombreAlbum());
    }

    //Metodo para avanzar de cancion
    public void siguiente(){
        ArrayList<Cancion> lista = null;

        if (playlistActual != null){
            lista = playlistActual.getListaCanciones();
        } else if (albumActual != null){
            lista = albumActual.getCanciones();
        }

        if (lista != null && indice < lista.size() - 1){
            indice++;
            cancionActual = lista.get(indice);
            System.out.println("⏭ Siguiente: " + cancionActual.getNombreCancion());

            String ruta = RUTA_BASE + cancionActual.getNombreCancion() + ".wav";
            if (new File(ruta).exists()) {
                reproducirConAudio(cancionActual);
            } else {
                reproducir(cancionActual);
            }
        } else if (lista != null){
            System.out.println("Estas en la ultima cancion");
        } else {
            System.out.println("No hay playlist o album cargado");
        }
    }

    //Metodo para la cancion anterior
    public void anterior(){
        ArrayList<Cancion> lista = null;

        if (playlistActual != null){
            lista = playlistActual.getListaCanciones();
        } else if (albumActual != null){
            lista = albumActual.getCanciones();
        }

        if (lista != null && indice > 0){
            indice--;
            cancionActual = lista.get(indice);
            System.out.println("⏮ Anterior: " + cancionActual.getNombreCancion());

            String ruta = RUTA_BASE + cancionActual.getNombreCancion() + ".wav";
            if (new File(ruta).exists()) {
                reproducirConAudio(cancionActual);
            } else {
                reproducir(cancionActual);
            }
        } else if (lista != null){
            System.out.println("Estas en la primera cancion");
        } else {
            System.out.println("No hay playlist o album cargado");
        }
    }

}
