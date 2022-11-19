package com.edu.atividade.certificado;

import com.edu.atividade.Atividade;
import com.edu.atividade.AtividadeService;
import com.edu.usuario.Usuario;
import com.edu.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class CertificadoService {

    @Autowired
    CertificadoRepository certificadoRepository;

    @Autowired
    AtividadeService atividadeService;

    @Autowired
    UsuarioService usuarioService;

    public Iterable<Certificado> buscarTodos() {
       return certificadoRepository.findAll();
    }

    public Certificado buscarPorId(Long id) {
        return certificadoRepository.findById(id).get();
    }

    public List<Certificado> buscarTodosPorUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return certificadoRepository.findByUsuarioUsername(username);
    }

    public Certificado gerar(Certificado certificado) {
        return certificadoRepository.save(certificado);
    }

    public Certificado gerarCertificadoAluno(Certificado certificado) {
        Usuario usuario = usuarioService.findByUsername(certificado.getUsuario().getUsername());

        Atividade atividade = atividadeService.getById(certificado.getAtividade().getId());

        certificado = Certificado.builder()
                .atividade(atividade)
                .usuario(usuario)
                .dataConclusao(LocalDate.now())
                .criador(false)
                .build();

        return certificadoRepository.save(certificado);
    }

}
