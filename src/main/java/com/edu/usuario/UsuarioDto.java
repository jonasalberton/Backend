package com.edu.usuario;

import com.edu.security.Permissao;

import java.util.Collection;

public class UsuarioDto {
    private String username;
    private String nome;
    private Collection<Permissao> permissoes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Collection<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
}
