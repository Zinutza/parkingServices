package org.zina.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zina.model.User;
import org.zina.services.AuthService;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    // Authenticate user
    @ResponseBody
    @RequestMapping(value="auth", method = POST)
    public ResponseEntity authenticateUser(@RequestBody User user) {
        User storedUser = authService.authenticateUser(user);
        if(storedUser != null) {
            return new ResponseEntity<User>(storedUser, OK);
        }
        return new ResponseEntity<String>("NO_USER", UNAUTHORIZED);
    }
}
