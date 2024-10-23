package com.jeff.music;

import com.jeff.music.principal.Principal;
import com.jeff.music.repository.ArtistRepository;
import com.jeff.music.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicApplication implements CommandLineRunner {
	@Autowired
	private AlbumRepository repository;
	@Autowired
	private ArtistRepository artistRepository;
	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, artistRepository);

		principal.showMenu();
	}
}
