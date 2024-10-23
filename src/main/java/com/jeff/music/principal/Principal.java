package com.jeff.music.principal;

import com.jeff.music.model.Album;
import com.jeff.music.model.Artist;
import com.jeff.music.repository.ArtistRepository;
import com.jeff.music.repository.AlbumRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private AlbumRepository repository;
    private ArtistRepository artistRepository;
    private Scanner scanner = new Scanner(System.in);

    public Principal(AlbumRepository repository, ArtistRepository artistRepository) {
        this.repository = repository;
        this.artistRepository = artistRepository;
    }

    public void showMenu() {

        var opcion = -1;

        while(opcion != 4){
            System.out.println("""
                \nBienvenido a la aplicacion de musica
                Elige una de las siguientes opciones: 
                
                1. Ingresar album
                2. Mostrar artistas
                3. Listar albumes por artistas
                4. Salir
                
                """);
            try{
                opcion = scanner.nextInt();

                switch (opcion){
                    case 1 -> setNewAlbum();
                    case 2 -> getArtists();
                    case 3 -> showAlbumsByArtist();
                    default -> System.out.println("Opcion invalida. Intenta de nuevo.");
                }
            }catch (Exception e){
                System.out.println("Opcion invalida. Intente de nuevo. \n" + e);
                scanner.nextLine();
            }
        }
    }

    private void setNewAlbum() {

        try{
            scanner.nextLine();
            System.out.print("Ingrese el nombre del artista: ");
            var artistName = scanner.nextLine();
            System.out.println(artistName);
            System.out.print("Ingrese el nombre del album: ");
            var albumName = scanner.nextLine();
            System.out.print("Ingrese la cantidad de canciones: ");
            var totalSongs = scanner.nextInt();
            System.out.print("Ingrese el anio de lanzamiento: ");
            var year = scanner.nextInt();

            if(!artistName.isEmpty() && !albumName.isEmpty()){
                Album album = new Album();
                //busca que el artista no exista
                Optional<Artist> artistFound = artistRepository.findByNameContainsIgnoreCase(artistName);
                //si el artista no existe, crea un registro en la tabla
                if(!artistFound.isPresent()){
                    Artist newArtist = new Artist(artistName);
                    artistRepository.save(newArtist);
                    //Guarda el album
                    album = new Album(albumName, totalSongs, year, newArtist);
                }else{
                    //Guarda el album
                    album = new Album(albumName, totalSongs, year, artistFound.get());
                }

                repository.save(album);

            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void getArtists(){
        List<Artist> artists = artistRepository.findAll();

        artists.stream()
                .sorted(Comparator.comparing(Artist::getName))
                .forEach(a -> System.out.println(a.getName()));
    }

    public void showAlbumsByArtist(){
        scanner.nextLine();
        System.out.println("Ingrese el nombre del artista: ");
        var artistName = scanner.nextLine();

        Optional<Artist> artistFound = artistRepository.findByNameContainsIgnoreCase(artistName);

        if(artistFound.isPresent()){
            List<Album> albumsFound = repository.findByArtist(artistFound.get());
            System.out.println("Los albumes de " + artistFound.get().getName() + " son: \n");

            albumsFound.stream()
                    .sorted(Comparator.comparing(Album::getRelease_year))
                    .forEach(System.out::println);
        }else{
            System.out.println("No se encontro el artista ingresado.");
        }
    }
}
