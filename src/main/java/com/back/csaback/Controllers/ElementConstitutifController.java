package com.back.csaback.Controllers;


import com.back.csaback.Models.UniteEnseignement;
import com.back.csaback.Services.ElementConstitutifService;
import com.back.csaback.Services.Tooltip;
import org.springframework.beans.factory.annotation.Autowired;
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
  /*  {
  "id":{
        "codeFormation":"M2DOSI",
                "codeUe":"J2EE"
    }
    }*/
    public ResponseEntity<?> getAllByEnseignant(@RequestBody UniteEnseignement ue){
       try{ if(ue.getId()==null || ue.getId().getCodeFormation() == null || ue.getId().getCodeUe() == null ) throw new IllegalArgumentException("il faut fournir un id valide");
            return ResponseEntity.ok(elementConstitutifService.getAllByEnseigant(ue));
       }catch (IllegalArgumentException e){ return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}
