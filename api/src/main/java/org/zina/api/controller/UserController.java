package org.zina.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zina.model.User;
import org.zina.services.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @ResponseBody
    @RequestMapping(value = "user/{id}", method = GET)
    public User read(@PathVariable(value="id") Long id) {
        return service.read(id);
    }

    @ResponseBody
    @RequestMapping(value = "user", method = POST)
    public User create(@RequestBody User user) {
        return service.create(user);
    }
}
