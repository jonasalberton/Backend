package com.edu.atividade.aula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/frete")
@CrossOrigin
public class FreteController {

    @Autowired
    private AulaService aulaService;

    @PostMapping("/creator")
    public Aula cadastrar(@RequestBody() Aula aula) {
        return aulaService.salvar(aula);
    }

    @GetMapping("")
    public String getByid(@PathVariable Long id) {
        return "ALORRA";
    }
}
