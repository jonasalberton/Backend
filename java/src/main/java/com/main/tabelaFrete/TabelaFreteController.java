package com.main.tabelaFrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/tabelas")
public class TabelaFreteController {

    @Autowired
    private TabelaFreteRepository tabelaFreteRepository;

    @GetMapping("/{id}")
    public ResponseEntity<TabelaFrete> buscarPorId(@PathVariable Integer id){
        TabelaFrete tabelaFreteReturn = tabelaFreteRepository.findById(id).get();
        return new ResponseEntity<>(tabelaFreteReturn, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<TabelaFrete> inserir(@RequestBody TabelaFrete tabelaFrete){
         TabelaFrete tabelaFreteReturn = tabelaFreteRepository.save(tabelaFrete);
        return new ResponseEntity<>(tabelaFreteReturn, HttpStatus.CREATED);
    }

}
