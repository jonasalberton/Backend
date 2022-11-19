package com.edu.atividade;

import com.edu.solicitacao.enums.StatusSolicitacao;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AtividadeRepository extends PagingAndSortingRepository<Atividade, Long> {

    List<Atividade> findByCriadorUsername(String username);

    List<Atividade> findByStatus(StatusSolicitacao status);
}
