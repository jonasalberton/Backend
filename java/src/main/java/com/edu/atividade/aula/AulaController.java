package com.edu.atividade.aula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/aula")
@CrossOrigin
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @PostMapping("/creator")
    public Aula cadastrar(@RequestBody() Aula aula) {
        return aulaService.salvar(aula);
    }

    @GetMapping("/{id}")
    public Aula getByid(@PathVariable Long id) {
        return aulaService.getByid(id);
    }
}
