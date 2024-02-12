package com.back.csaback.Controllers;

import com.back.csaback.Models.Evaluation;
import com.back.csaback.Services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("eva")
public class EvaluationController {

    @Autowired
    private EvaluationService es;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getAll")
    public List<Evaluation> getAll(){
        return es.getAll();
    }

}
