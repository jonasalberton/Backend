package com.main.tabelaFrete;

import org.springframework.stereotype.Service;

import java.util.List;

public interface TabelaFreteService {

    TabelaFrete salvar(TabelaFrete tabelaFrete);
    TabelaFrete buscarPorId(Integer id);
    void deletar(Integer id);

    List<TabelaFrete> buscarPorDescricao(String descricao);

}
