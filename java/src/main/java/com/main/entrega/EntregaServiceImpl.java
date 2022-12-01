package com.main.entrega;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaServiceImpl implements EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Override
    public Entrega inserir(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    @Override
    public List<Entrega> buscarPorIdEntregador(Integer idEntregador) {
        return entregaRepository.findByIdEntregador(idEntregador);
    }

    @Override
    public void atualizarStatus(Integer id, StatusEnum status) {
        Entrega entrega = entregaRepository.findById(id).get();
        if (entrega.getStatus().equals(StatusEnum.ENTREGUE)){
            throw new EntregaException("Não é possível alterar o status, uma vez que já está entregue.");
        }
        entrega.setStatus(status);
        entregaRepository.save(entrega);
    }
}
