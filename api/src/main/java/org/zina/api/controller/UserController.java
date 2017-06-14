package org.zina.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zina.model.User;
import org.zina.services.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @ResponseBody
    @RequestMapping(value = "user/{id}", method = GET)
    public User read(@PathVariable(value="id") Long id) {
        return service.read(id);
    }
}
