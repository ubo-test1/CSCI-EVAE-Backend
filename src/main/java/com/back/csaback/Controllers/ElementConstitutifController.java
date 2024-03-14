package com.back.csaback.Controllers;


import com.back.csaback.Models.ElementConstitutifId;
import com.back.csaback.Models.Enseignant;
import com.back.csaback.Models.UniteEnseignement;
import com.back.csaback.Models.UniteEnseignementId;
import com.back.csaback.Services.ElementConstitutifService;
import com.back.csaback.Services.Tooltip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/ElementConstitutif"})
@CrossOrigin(origins = "http://localhost:3000")
public class ElementConstitutifController {
    @Autowired
    ElementConstitutifService elementConstitutifService;
    @Autowired
    Tooltip ttip;
    @PreAuthorize("hasRole('ENS')")
    @GetMapping("/getAllByUE")
    public ResponseEntity<?> getAllByUEAndEnseignant(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,@RequestParam("codeUe") String codeUe, @RequestParam("codeFormation") String codeFormation){
       try{
           if(codeFormation == null || codeUe == null ) throw new IllegalArgumentException("il faut fournir un des infos valide !");
           UniteEnseignement uniteEnseignement=new UniteEnseignement();
           uniteEnseignement.setId(new UniteEnseignementId(codeFormation,codeUe));
           Enseignant e = ttip.getUserFromToken(auth);
           return ResponseEntity.ok(elementConstitutifService.getAllByUeAndEnseigant(uniteEnseignement,e));
       }catch (IllegalArgumentException e){ return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}
