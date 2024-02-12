package com.back.csaback.Controllers;

import com.back.csaback.Models.ERole;
import com.back.csaback.Models.Role;
import com.back.csaback.Models.User;
import com.back.csaback.Repositories.RoleRepository;
import com.back.csaback.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("test")
public class TestController {
    private BCryptPasswordEncoder pe;

    @Autowired
    private UserRepository ur;

    @Autowired
    private RoleRepository rr;

    @GetMapping("test")
    public String test(){
        return "Hello VVorld";
    }

    @GetMapping("init")
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
        u2.setPassword(pe.encode("admin"));
        u2.setRoles(sr2);

        ur.save(u);
        ur.save(u2);
    }
}
