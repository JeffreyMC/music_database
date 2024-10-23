package com.jeff.music.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artists")
public class Artist{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        @OneToMany(cascade = CascadeType.ALL,
        fetch = FetchType.EAGER)
        private List<Album> albums;

        public Artist(){}

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Artist(String name){
                this.name = name;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        @Override
        public String toString() {
                return "Artist{" +
                        "name='" + name + '\'' +
                        '}';
        }
}
