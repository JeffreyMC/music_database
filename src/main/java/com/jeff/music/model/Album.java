package com.jeff.music.model;

import jakarta.persistence.*;

@Entity
@Table(name = "albums")
public class Album{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private Integer totalSongs;
        private Integer release_year;
        @ManyToOne
        private Artist artist;

        public Album(String albumName, int totalSongs, int year, Artist artistName) {
                this.name = albumName;
                this.totalSongs = totalSongs;
                this.release_year = year;
                this.artist = artistName;
        }

        public Album() {}

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Integer getTotalSongs() {
                return totalSongs;
        }

        public void setTotalSongs(Integer totalSongs) {
                this.totalSongs = totalSongs;
        }

        public Integer getRelease_year() {
                return release_year;
        }

        public void setRelease_year(Integer release_year) {
                this.release_year = release_year;
        }

        public Artist getArtist() {
                return artist;
        }

        public void setArtist(Artist artist) {
                this.artist = artist;
        }

        @Override
        public String toString() {
                return "Nombre del album: " + name + "\n" +
                        "Total de canciones: " + totalSongs + "\n" +
                        "Año de lanzamiento: " + release_year + "\n";
        }
}
