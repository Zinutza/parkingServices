package org.zina.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class HealthController {

    // Backend healthcheck // Ignore this one
    @ResponseBody
    @RequestMapping(value = "health", method = GET)
    public String checkHealth() {
        return "I am healthy";
    }
}
