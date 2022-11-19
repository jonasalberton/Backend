package com.edu.atividade;

import com.edu.solicitacao.enums.StatusSolicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public Atividade salvar(Atividade atividade) {
        atividade.setDataCriacao(LocalDate.now());
        return atividadeRepository.save(atividade);
    }

    public List<Atividade> getAllByUserName() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return atividadeRepository.findByCriadorUsername(username);
    }

    public Atividade getById(Long id) {
        return atividadeRepository.findById(id).get();
    }

    public List<Atividade> getAceitas() {
        return atividadeRepository.findByStatus(StatusSolicitacao.ACEITA);
    }
}
