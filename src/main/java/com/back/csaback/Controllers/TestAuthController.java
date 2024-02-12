package com.back.csaback.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testAuth")
public class TestAuthController {
    @GetMapping("testUser")
    @PreAuthorize("hasRole('USER')")
    public String testUser(){
        return "User auth working";
    }

    @GetMapping("testAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String testAdmin(){
        return "Admin auth working";
    }

    @GetMapping("testAuth")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String testAuth(){
        return "A   uth working";
    }
}
