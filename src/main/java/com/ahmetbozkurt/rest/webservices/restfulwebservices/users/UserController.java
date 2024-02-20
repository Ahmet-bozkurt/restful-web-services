package com.ahmetbozkurt.rest.webservices.restfulwebservices.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;

import javax.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;
	
	//retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUser(){
		return userDao.findAll();
	}
	
	//retrieveUser{id}
	@GetMapping("/users/{id}")
	public EntityModel getUser(@PathVariable Integer id) {

		User user = userDao.findUser(id);

		if (user == null){
			throw new UserNotFoundException("id" + id);
		}

		EntityModel<User> model = EntityModel.of(user);

		WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUser());

		model.add(linkToUsers.withRel("all-users"));

		// /users
		return model;
	}
	
	@PostMapping("/users")
	public void createUser(@Valid @RequestBody User user) {
		User savedUser = userDao.saveUser(user);
	}
	
}
