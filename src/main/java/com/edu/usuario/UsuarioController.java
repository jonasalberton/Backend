package com.edu.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(path = "/{username}")
    public UsuarioDto listarPorId(@PathVariable("username") String username) {
       Usuario usuario =  this.usuarioService.findByUsername(username);
       return new ModelMapper().map(usuario, UsuarioDto.class);
    }

    @PostMapping()
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        return this.usuarioService.cadastrar(usuario);
    }

    @PostMapping("/admin/set-criador")
    public Usuario adicionarPermissaoCriador(@RequestBody Usuario usuario) throws Exception {
        return this.usuarioService.adicionarPermissaoCreator(usuario);
    }

    @PostMapping("/set-admin")
    public Usuario adicionarPermissaoAdmin(@RequestBody Usuario usuario) throws Exception {
        return this.usuarioService.adicionarPermissaoAdmin(usuario);
    }
}
