package com.edu.security;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Permissao {

    @Id
    @Enumerated(EnumType.STRING)
    private Authority permissao;

    public Permissao(Authority permissao) {
        this.permissao = permissao;
    }

    public Permissao() {
    }

    public Authority getPermissao() {
        return permissao;
    }

    public void setPermissao(Authority permissao) {
        this.permissao = permissao;
    }

    public enum Authority {
        ROLE_USER,
        ROLE_ADMIN,
        ROLE_CREATOR
    }
}


