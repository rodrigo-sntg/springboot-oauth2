package br.com.sandev.rods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sandev.rods.oauth2.models.Authority;
import br.com.sandev.rods.oauth2.models.User;
import br.com.sandev.rods.repositories.UserRepository;

@SpringBootApplication
public class RodsApplication implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RodsApplication.class, args);
	}
	
	@Override
	public void run(String... args) {

		User usuarioAdmin = new User();
		usuarioAdmin.setUsername("rodsntg");
		usuarioAdmin.setPassword("usuario");
		
		userRepository.save(usuarioAdmin);
		
	}
}
