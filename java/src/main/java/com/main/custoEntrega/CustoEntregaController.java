package com.main.custoEntrega;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/custos")
public class CustoEntregaController {

    @Autowired
    private CustoEntregaRepository entregaRepository;

//    @PostMapping
//    public Entrega inserir(@RequestBody Entrega entrega){
//        return entregaRepository.(entrega);
//    }

}
