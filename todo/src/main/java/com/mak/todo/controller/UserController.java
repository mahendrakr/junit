package com.mak.todo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mak.todo.domain.User;
import com.mak.todo.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping(path = "/users/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		 userService.deleteUser(id);
	}
	
	@GetMapping(path = "/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userService.findUserById(id);
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@PostMapping(path = "/users/save")
	public ResponseEntity<Object> save(@RequestBody User user) {
		User savedUser = userService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
