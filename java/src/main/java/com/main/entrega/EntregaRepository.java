package com.main.entrega;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EntregaRepository extends PagingAndSortingRepository<Entrega,Integer>{

    List<Entrega> findByIdEntregador(Integer idEntregador);

}