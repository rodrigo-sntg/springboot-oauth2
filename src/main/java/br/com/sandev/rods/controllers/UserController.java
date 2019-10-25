package br.com.sandev.rods.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sandev.rods.oauth2.models.User;
import br.com.sandev.rods.repositories.UserRepository;

@CrossOrigin
@Controller
@RequestMapping({ "/api/secure/user" })
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAll() {
		List<User> users = userRepository.findAll();
		
		User user = userRepository.findByNameOR("");
		
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> get(@PathVariable(value = "username") String username) {
		User user = userRepository.findByNameOR(username);
		
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping(value="/", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok().body(userRepository.save(user));
	}
	
	@PutMapping("/{username}")
	public ResponseEntity<User> update(
			@PathVariable(value = "username") String username, @Valid @RequestBody User details)
					throws ResourceNotFoundException {
		
		User best = userRepository.findByUsername(username);
		
		final User updated = userRepository.save(best);
		
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable(value = "usarname") String username) throws Exception {
		User sup = userRepository.findByUsername(username);
		userRepository.delete(sup);
		Map<String, Boolean> response = new HashMap<>();
		
		response.put("deleted", Boolean.TRUE);
	
		return ResponseEntity.ok().body(response);
	}

}