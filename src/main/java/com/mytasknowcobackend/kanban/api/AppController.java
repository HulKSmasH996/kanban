package com.mytasknowcobackend.kanban.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping(path = "/test")
    public String test(Principal principal) {
        return principal.getName();
    }
}
