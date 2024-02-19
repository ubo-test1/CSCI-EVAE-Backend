package com.back.csaback.Services;

import com.back.csaback.Config.JWT.JwtUtils;
import com.back.csaback.Models.Authentification;
import com.back.csaback.Models.Enseignant;
import com.back.csaback.Repositories.EnseignantRepository;
import com.back.csaback.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Tooltip {

    @Autowired
    private JwtUtils jwt;

    @Autowired
    private UserRepository ur;

    @Autowired
    private EnseignantRepository er;

    public Enseignant getUserFromToken(String token){
        try{
            token = token.substring(7);
            Authentification a = ur.findByLoginOrPseudo(jwt.getUserNameFromJwtToken(token)).get();
            Enseignant e = er.findByEmailUbo(a.getLoginConnection()).get();
            return e;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getRoleFromToken(String token){
        try{
            token = token.substring(7);
            return ur.findByLoginOrPseudo(jwt.getUserNameFromJwtToken(token)).get().getRole();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
