package com.main.entrega;

import java.util.List;

interface EntregaService {

    Entrega inserir(Entrega entrega);
    List<Entrega> buscarPorIdEntregador(Integer idEntregador);
    void atualizarStatus(Integer id, StatusEnum status);

}
