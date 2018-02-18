package edu.usach.mddvaccesscontrol.controller;

import edu.usach.mddvaccesscontrol.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserController {
	private List<User> userList = new ArrayList<>();

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public Optional<User> getUser(@RequestParam String email) {
		return userList.stream().filter(x -> x.getEmail().equals(email)).findAny();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void addUser(@RequestBody User user) {
		userList.add(user);
	}
}
