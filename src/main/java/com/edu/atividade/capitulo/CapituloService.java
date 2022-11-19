package com.edu.atividade.capitulo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapituloService {

    @Autowired
    private CapituloRepository capituloRepository;

    public Capitulo salvar(Capitulo capitulo) {
        return this.capituloRepository.save(capitulo);
    }
}
