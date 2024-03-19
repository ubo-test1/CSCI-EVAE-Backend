package com.back.csaback.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testAuth")
@CrossOrigin
public class TestAuthController {
    @GetMapping("testUser")
    @PreAuthorize("hasRole('ENS')")
    public String testUser(){
        return "User auth working";
    }

    @GetMapping("testAdmin")
    @PreAuthorize("hasRole('ADM')")
    public String testAdmin(){
        return "Admin auth working";
    }

    @GetMapping("testAuth")
    @PreAuthorize("hasRole('ENS') or hasRole('ADM')")
    public String testAuth(){
        return "A   uth working";
    }
}
