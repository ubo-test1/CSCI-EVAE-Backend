package com.back.csaback.Controllers;

import com.back.csaback.DTO.EvaDTO;
import com.back.csaback.Models.Evaluation;
import com.back.csaback.DTO.EvaluationDetails;
import com.back.csaback.Services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("eva")
@CrossOrigin
public class EvaluationController {

    @Autowired
    private EvaluationService es;
    @PreAuthorize("hasRole('ADM')")
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

    @PreAuthorize("hasRole('ADM')")
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
}
