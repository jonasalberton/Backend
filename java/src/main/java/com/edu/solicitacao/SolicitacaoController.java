package com.edu.solicitacao;

import com.edu.atividade.Atividade;
import com.edu.solicitacao.enums.StatusSolicitacao;
import com.edu.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/solicitacao")
@CrossOrigin
public class SolicitacaoController {

    @Autowired
    SolicitacaoService solicitacaoService;

    @GetMapping("/admin/{status}")
    public Iterable<Solicitacao> buscarPorSatus(@PathVariable StatusSolicitacao status) {
        return solicitacaoService.buscarPorStatus(status);
    }

    @PostMapping("/criador")
    public Solicitacao solicitarRoleCreator(@RequestBody Usuario usuario) throws Exception {
        return solicitacaoService.criarSolicitacaoRoleCreator(usuario);
    }

    @PostMapping("/admin/permitir-criador")
    public Solicitacao permitirCriador(@RequestBody Solicitacao solicitacao) throws Exception {
        return solicitacaoService.permitirCriador(solicitacao);
    }

    @PostMapping("/solicitar-publicacao")
    public Solicitacao solicitarPublicacaoAtividade(@RequestBody Atividade atividade) throws Exception {
        return solicitacaoService.criarSolicitacaoPublicacaoAtividade(atividade);
    }

    @PostMapping("/admin/permitir-publicacao")
    public Solicitacao permitirPublicacaoAtividade(@RequestBody Solicitacao solicitacao) throws Exception {
        return solicitacaoService.permitirPublicacaoAtividade(solicitacao);
    }

    @PostMapping("/admin/negar-publicacao")
    public Solicitacao negarPublicacao(@RequestBody Solicitacao solicitacao) throws Exception {
        return solicitacaoService.negarPublicacao(solicitacao);
    }
}
