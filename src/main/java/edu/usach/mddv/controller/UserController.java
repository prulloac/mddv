package edu.usach.mddv.controller;

import edu.usach.mddv.model.User;
import edu.usach.mddv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    };
}
