package com.main.usuario;

import com.main.security.Permissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findByUsername(String userName) {
        return this.usuarioRepository.findByUsername(userName);
    }

    public Usuario atualizar(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    public Usuario cadastrar(Usuario usuario) {
        usuario.setPassword(this.bcryptPassword(usuario.getPassword()));
        List<Permissao> auth = new ArrayList();
        auth.add(new Permissao(Permissao.Authority.ROLE_ADMIN));
        usuario.setPermissoes(auth);
        return this.usuarioRepository.save(usuario);
    }

    private String bcryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public Usuario adicionarPermissaoCreator(Usuario usuario) throws Exception {
        Usuario user = findByUsername(usuario.getUsername());
        if(Objects.isNull(user)) throw new Exception("Erro ao encontrar o usuario");

        user.adicionarPermissao(new Permissao(Permissao.Authority.ROLE_CREATOR));
        return this.usuarioRepository.save(user);
    }

    public Usuario adicionarPermissaoAdmin(Usuario usuario) throws Exception {
        Usuario user = findByUsername(usuario.getUsername());
        if(Objects.isNull(user)) throw new Exception("Erro ao encontrar o usuario");

        user.adicionarPermissao(new Permissao(Permissao.Authority.ROLE_ADMIN));
        return this.usuarioRepository.save(user);
    }
}