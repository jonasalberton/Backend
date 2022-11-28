package com.edu.atividade.certificado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/certificado")
@CrossOrigin
public class CertificadoController {

    @Autowired
    CertificadoService certificadoService;

    @GetMapping("/{id}")
    public Certificado buscarPorId(@PathVariable Long id) {
        return certificadoService.buscarPorId(id);
    }

    @GetMapping()
    public List<Certificado> buscarPorUsername() {
        return certificadoService.buscarTodosPorUsername();
    }

    @PostMapping("/aluno")
    public Certificado gerarCertificadoAulo(@RequestBody() Certificado certificado) {
        return certificadoService.gerarCertificadoAluno(certificado);
    }
}
