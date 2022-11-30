package com.main.tabelaFrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/tabelas")
public class TabelaFreteController {

    @Autowired
    private TabelaFreteService tabelaFreteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TabelaFrete> buscarPorId(@PathVariable Integer id){
        TabelaFrete tabelaFreteReturn = tabelaFreteService.buscarPorId(id);
        return new ResponseEntity<>(tabelaFreteReturn, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<TabelaFrete> inserir(@RequestBody TabelaFrete tabelaFrete){
         TabelaFrete tabelaFreteReturn = tabelaFreteService.salvar(tabelaFrete);
        return new ResponseEntity<>(tabelaFreteReturn, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<TabelaFrete> atualizar(@RequestBody TabelaFrete tabelaFrete){
        TabelaFrete tabelaFreteReturn = tabelaFreteService.salvar(tabelaFrete);
        return new ResponseEntity<>(tabelaFreteReturn, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TabelaFrete> deletar(@PathVariable Integer id){
        tabelaFreteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TabelaFrete>> buscarPorDescricao(@RequestParam String descricao){
        List<TabelaFrete> tabelaFreteReturn = tabelaFreteService.buscarPorDescricao(descricao);
        return new ResponseEntity<>(tabelaFreteReturn, HttpStatus.OK);
    }

}
