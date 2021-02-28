package org.aspedemo.db.user;

import java.util.List;
import java.util.Map;

import org.aspedemo.exception.AspeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(path="/{id}")
	public User getUser(@PathVariable Integer id) {
		return userService.findUserById(id);
	}
	
	@PostMapping(path="/count")
	public Long count() {
		return userService.countUsers();
	}
	
	@PostMapping(path="/search")
	public List<User> getUsers(@RequestBody Map<String, Object> filter) {
		return userService.findUsersByFilter(filter);
	}
	
	@PostMapping(path="/create")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody User createUser(@RequestBody User user) {
		try {
			Integer id = user.getId();
			if (id != null) {
				throw new AspeException(422, "id not allowed", "please use PUT /user/{id}");
			}
			
			return userService.create(user);
		}
		catch (IllegalArgumentException e) {
			throw new AspeException(422, "Validation Failed");
		}
	}
	
	@PutMapping(path="/update/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	public @ResponseBody User updateUser(@PathVariable int id, @RequestBody User user) {
		try {
			user.setId(id);
			return userService.update(user);
		}
		catch(IllegalArgumentException e) {
			throw new AspeException(422, "Validation Failed");
		}
	}
}
