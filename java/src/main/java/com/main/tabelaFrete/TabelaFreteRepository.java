package com.main.tabelaFrete;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TabelaFreteRepository extends PagingAndSortingRepository<TabelaFrete,Integer>{

    List<TabelaFrete> findByDescricaoCurtaContainingIgnoreCase(String descricao);

}