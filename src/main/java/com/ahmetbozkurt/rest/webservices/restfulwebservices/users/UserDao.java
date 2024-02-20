package com.ahmetbozkurt.rest.webservices.restfulwebservices.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDao {
	
	private static List<User> users = new ArrayList<User>();
	private static int count = 3;
	
	static {
		users.add(new User(1, "Cagkan", new Date()));
		users.add(new User(2, "Ahmet", new Date()));
		users.add(new User(3, "Ali", new Date()));
	}
	
	//findAll();
	
	public List<User> findAll() {
		return users;
	}
	
	//saveUser();
	
	public User saveUser(User user) {
		
		if (user.getId() == null ) {
			user.setId(++count);
		}
		users.add(user);
		return user;
	}
	
	//findUser();
	
	public User findUser(Integer id) {
		for(User user: users) {
			if (user.getId()==id) {
				return user;
			}
		}
		return null;
		
	}
}
