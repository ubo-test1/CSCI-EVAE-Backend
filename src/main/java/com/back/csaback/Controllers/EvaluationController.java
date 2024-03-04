package com.back.csaback.Controllers;

import com.back.csaback.DTO.EvaDTO;
import com.back.csaback.Models.Etudiant;
import com.back.csaback.Models.Evaluation;
import com.back.csaback.DTO.EvaluationDetails;
import com.back.csaback.Services.EvaluationService;
import com.back.csaback.Services.Tooltip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.tools.Tool;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("eva")
@CrossOrigin(origins = "http://localhost:3000")
public class EvaluationController {

    @Autowired
    private EvaluationService es;

    @Autowired
    private Tooltip ttip;

    @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
    @GetMapping("getAll")
    public ResponseEntity<List<EvaDTO>> getAll(){
        try{
            List<EvaDTO> ret = new ArrayList<>();
            EvaDTO tmp;
            for(Evaluation e : es.getAll()){
                tmp = new EvaDTO(e);
                ret.add(tmp);
            }
            return ResponseEntity.ok(ret);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ADM') or hasRole('ENS')")
    @GetMapping("consulterInfo/{id}")
    public ResponseEntity<EvaluationDetails> consulterInfo(@PathVariable("id") Integer id){
        try{
            Evaluation e = es.findById(id);
            if(e == null )throw new IllegalArgumentException("Evaluation introuvable");
            EvaluationDetails ret = es.ConsulterEvaluation(e);
            if(ret == null) throw new IllegalStateException("Erreur serveur");
            return ResponseEntity.ok(ret);
        }catch(IllegalArgumentException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch(Exception ee){
            ee.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasRole('ETU')")
    @GetMapping("getByPro")
    public ResponseEntity<?> getByPro(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        try {
            Etudiant e = ttip.getEtudFromToken(auth);
            return ResponseEntity.ok(es.findAllByPromo(e));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
