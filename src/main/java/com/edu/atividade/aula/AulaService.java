package com.edu.atividade.aula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    public Aula salvar(Aula aula) {
        return this.aulaRepository.save(aula);
    }

    public Aula getByid(Long id) {
        return aulaRepository.findById(id).get();
    }
}
