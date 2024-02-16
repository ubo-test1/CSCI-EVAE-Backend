package com.back.csaback.Controllers;

import com.back.csaback.Config.MD5PasswordEncoder;
import com.back.csaback.Models.Authentification;
import com.back.csaback.Repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    private MD5PasswordEncoder pe;

    @Autowired
    private UserRepository ur;

    @GetMapping("test")
    public String test(){
        return "Hello VVorld";
    }
<<<<<<< HEAD

    @PostConstruct
    public void init(){
        if(!rr.findAll().isEmpty()) return;
        pe = new BCryptPasswordEncoder();
        Role r1 = new Role();
        Role r2 = new Role();
        r1.setName(ERole.ROLE_USER);
        r2.setName(ERole.ROLE_ADMIN);
        r1 = rr.save(r1);
        r2 = rr.save(r2);
        if(!ur.findAll().isEmpty()) return;
        Set<Role> sr1 = new HashSet<>();
        sr1.add(r1);
        Set<Role> sr2 = new HashSet<>();
        sr2.add(r2);
        User u = new User();
        u.setEmail("sa.hadiche@gmail.com");
        u.setPassword(pe.encode("test1"));
        u.setUsername("test");
        u.setRoles(sr1);
        User u2 = new User();
        u2.setEmail("admin");
        u2.setUsername("admin");
        u2.setPassword(pe.encode("admin"));
        u2.setRoles(sr2);

        ur.save(u);
        ur.save(u2);
    }
=======
/*
    @PostConstruct
    public void fixPasswords(){
        pe = new MD5PasswordEncoder();
        Authentification a1 = ur.findByLoginConnection("Administrateur").get();
        a1.setMotPasse(pe.encode(a1.getMotPasse()));
        ur.save(a1);
    }*/
>>>>>>> 5e8e0b197ba38f2d42bfe30f5d9bbefce4fb5028
}
