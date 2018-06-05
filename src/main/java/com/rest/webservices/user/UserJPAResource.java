package com.rest.webservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
public class UserJPAResource {
	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> getAllUser(){
		
		return userRepository.findAll();
		
	}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	@PostMapping("/jpa/users")
	public  ResponseEntity<Object> saveUser(  @RequestBody User user ) {
		User saveduser =userRepository.save(user);
		URI location =ServletUriComponentsBuilder.fromCurrentRequest(
				).path("{/id}").buildAndExpand(saveduser.getId()).toUri();
		
		return  ResponseEntity.created(location).build();
		
		
	}
	
	
	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
/*
		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);*/

		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		Resource<User> resource = new Resource<User>(user.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUser());

		resource.add(linkTo.withRel("all-users"));

		// HATEOAS

		return resource;
	}

	@GetMapping("/jpa/user/{id}/post")
	public List<Post> reterivePost( @PathVariable int id){
		Optional<User> optionaluser = userRepository.findById(id);
		
		return optionaluser.get().getPost();
	}
	
	@PostMapping("/jpa/user/{id}/post")
	public ResponseEntity<Post> savePost(@PathVariable int id ,@RequestBody Post post){
		
		Optional< User> optionalUser = userRepository.findById(id);
		User user = optionalUser.get();
		post.setUser(user);
		postRepository.save(post);
		
		URI location= ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}