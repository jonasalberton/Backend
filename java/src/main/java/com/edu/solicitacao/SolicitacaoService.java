package com.edu.solicitacao;

import com.edu.atividade.Atividade;
import com.edu.atividade.AtividadeService;
import com.edu.atividade.certificado.Certificado;
import com.edu.atividade.certificado.CertificadoService;
import com.edu.solicitacao.enums.StatusSolicitacao;
import com.edu.solicitacao.enums.TipoSolicitacao;
import com.edu.usuario.Usuario;
import com.edu.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class SolicitacaoService {

    @Autowired
    SolicitacaoRepository solicitacaoRepository;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    AtividadeService atividadeService;
    @Autowired
    CertificadoService certificadoService;

    public Solicitacao criarSolicitacaoRoleCreator(Usuario usuario) throws Exception {
        Usuario user = usuarioService.findByUsername(usuario.getUsername());

        if (Objects.isNull(user)) throw new Exception("erro");

        Solicitacao solicitacao = Solicitacao.builder()
                .solicitante(user)
                .tipoSolicitacao(TipoSolicitacao.CRIADOR)
                .descricao("Solicitação de permissão criador")
                .statusSolicitacao(StatusSolicitacao.PENDENTE)
                .dataSolicitacao(LocalDate.now())
                .build();

        return solicitacaoRepository.save(solicitacao);
    }

    public Solicitacao permitirCriador(Solicitacao solicitacao) throws Exception {
        solicitacao = solicitacaoRepository.findById(solicitacao.getId()).get();

        if (Objects.isNull(solicitacao)) throw new Exception("Solicitação não encontrada");

        solicitacao.setStatusSolicitacao(StatusSolicitacao.ACEITA);
        solicitacao = solicitacaoRepository.save(solicitacao);
        usuarioService.adicionarPermissaoCreator(solicitacao.getSolicitante());

        return solicitacao;
    }

    public Iterable<Solicitacao> buscarPorStatus(StatusSolicitacao statusSolicitacao) {
        return solicitacaoRepository.findByStatusSolicitacao(statusSolicitacao);
    }

    public Solicitacao criarSolicitacaoPublicacaoAtividade(Atividade atividade) throws Exception {
        atividade = atividadeService.salvar(atividade);

        if (Objects.isNull(atividade)) throw new Exception("erro");

        Solicitacao solicitacao = Solicitacao.builder()
                .solicitante(atividade.getCriador())
                .tipoSolicitacao(TipoSolicitacao.PUBLICACAO)
                .atividade(atividade)
                .descricao("Solicitação para publicar atividade")
                .statusSolicitacao(StatusSolicitacao.PENDENTE)
                .dataSolicitacao(LocalDate.now())
                .build();

        return solicitacaoRepository.save(solicitacao);
    }

    public Solicitacao permitirPublicacaoAtividade(Solicitacao solicitacao) throws Exception {

        Atividade atividade = atividadeService.getById(solicitacao.getAtividade().getId());
        atividade.setCargaHorariaAluno(solicitacao.getAtividade().getCargaHorariaAluno());
        atividade.setCargaHorariaCriador(solicitacao.getAtividade().getCargaHorariaCriador());
        atividade.setStatus(StatusSolicitacao.ACEITA);
        atividadeService.salvar(atividade);

        solicitacao = solicitacaoRepository.findById(solicitacao.getId()).get();

        if (Objects.isNull(solicitacao)) throw new Exception("Solicitação não encontrada");

        solicitacao.setStatusSolicitacao(StatusSolicitacao.ACEITA);
        solicitacao = solicitacaoRepository.save(solicitacao);

        gerarCertificadoCriador(solicitacao);
        return solicitacao;
    }

    public Solicitacao negarPublicacao(Solicitacao solicitacao) throws Exception {
        Atividade atividade = atividadeService.getById(solicitacao.getAtividade().getId());
        atividade.setStatus(StatusSolicitacao.NEGADA);
        atividadeService.salvar(atividade);

        solicitacao = solicitacaoRepository.findById(solicitacao.getId()).get();

        if (Objects.isNull(solicitacao)) throw new Exception("Solicitação não encontrada");

        solicitacao.setStatusSolicitacao(StatusSolicitacao.NEGADA);
        solicitacao = solicitacaoRepository.save(solicitacao);

        return solicitacao;
    }

    private void gerarCertificadoCriador(Solicitacao solicitacao) {
        Certificado certificado = Certificado.builder()
                .usuario(solicitacao.getSolicitante())
                .atividade(solicitacao.getAtividade())
                .criador(true)
                .build();

        certificadoService.gerar(certificado);
    }
}
