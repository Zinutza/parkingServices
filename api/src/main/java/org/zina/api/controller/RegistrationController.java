package org.zina.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zina.model.RegistrationDetails;
import org.zina.model.User;
import org.zina.services.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RegistrationController {

    @Autowired
    private UserService service;

    // Register new user
    @ResponseBody
    @RequestMapping(value = "register", method = POST)
    public User create(@RequestBody RegistrationDetails registrationDetails) {
        return service.create(convertToUser(registrationDetails));
    }

    private User convertToUser(RegistrationDetails registrationDetails) {
        User user = new User();
        user.setEmail(registrationDetails.getEmail());
        user.setPasswordClearText(registrationDetails.getPassword());
        return user;
    }
}
