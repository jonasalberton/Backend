package com.main.entrega;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @PostMapping
    public ResponseEntity<Entrega> inserir(@RequestBody Entrega entrega){
        Entrega entregaReturn = entregaService.inserir(entrega);
        return new ResponseEntity<>(entregaReturn, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public void atualizar(@PathVariable Integer id, @RequestParam StatusEnum status){
        entregaService.atualizarStatus(id, status);
    }
}
