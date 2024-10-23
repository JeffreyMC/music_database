package com.jeff.music.repository;

import com.jeff.music.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findByNameContainsIgnoreCase(String name);
}
