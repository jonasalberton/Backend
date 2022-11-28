package com.edu.solicitacao;

import com.edu.solicitacao.enums.StatusSolicitacao;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SolicitacaoRepository extends PagingAndSortingRepository<Solicitacao, Long> {

    List<Solicitacao> findByStatusSolicitacao(StatusSolicitacao statusSolicitacao);
}
