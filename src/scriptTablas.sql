CREATE DATABASE bd_reproductor;

USE bd_reproductor;

CREATE TABLE t_canciones (
     id          INT AUTO_INCREMENT PRIMARY KEY,
     nombre      VARCHAR(100)    NOT NULL,
     autor       VARCHAR(100)    NOT NULL,
     genero      VARCHAR(50)     NOT NULL,
     duracion    VARCHAR(10)     NOT NULL
    );


CREATE TABLE t_playlists (
     id              INT AUTO_INCREMENT PRIMARY KEY,
     nombre_playlist VARCHAR(100)    NOT NULL
);


CREATE TABLE t_playlist_canciones (
      id_playlist     INT NOT NULL,
      id_cancion      INT NOT NULL,
      PRIMARY KEY (id_playlist, id_cancion),
      FOREIGN KEY (id_playlist)   REFERENCES t_playlists(id)  ON DELETE CASCADE,
      FOREIGN KEY (id_cancion)    REFERENCES t_canciones(id)  ON DELETE CASCADE
);


CREATE TABLE t_albumes (
       id              INT AUTO_INCREMENT PRIMARY KEY,
       nombre_album    VARCHAR(100)    NOT NULL,
       nombre_artista  VARCHAR(100)    NOT NULL,
       anio            INT             NOT NULL

);


CREATE TABLE t_album_canciones (
       id_album        INT NOT NULL,
       id_cancion      INT NOT NULL,
       PRIMARY KEY (id_album, id_cancion),
       FOREIGN KEY (id_album)      REFERENCES t_albumes(id)    ON DELETE CASCADE,
       FOREIGN KEY (id_cancion)    REFERENCES t_canciones(id)  ON DELETE CASCADE
);