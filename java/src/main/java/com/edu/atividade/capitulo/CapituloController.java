package com.edu.atividade.capitulo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/capitulo")
@CrossOrigin
public class CapituloController {

    @Autowired
    private CapituloService capituloService;

    @PostMapping("/creator")
    public Capitulo cadastrar(@RequestBody() Capitulo capitulo) {
        return capituloService.salvar(capitulo);
    }
}
