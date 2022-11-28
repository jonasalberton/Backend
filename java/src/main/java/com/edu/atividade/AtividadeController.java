package com.edu.atividade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/atividade")
@CrossOrigin
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @PostMapping("/creator")
    public Atividade cadastrar(@RequestBody() Atividade atividade) {

       return atividadeService.salvar(atividade);
    }

    @GetMapping()
    public List<Atividade> buscarPorUsername() {
        return atividadeService.getAllByUserName();
    }

    @GetMapping("/aceitas")
    public List<Atividade> buscarAceitas() {
        return atividadeService.getAceitas();
    }

    @GetMapping("/{id}")
    public Atividade buscarPorId(@PathVariable Long id) {
        return atividadeService.getById(id);
    }
}
