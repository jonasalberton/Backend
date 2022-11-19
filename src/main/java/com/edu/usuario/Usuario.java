package com.edu.usuario;

import com.edu.security.Permissao;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements UserDetails {

    @Id
    @Column(unique = true)
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nome;

    @ManyToMany(fetch=FetchType.EAGER)
    private Collection<Permissao> permissoes;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList();

        for (Permissao auth : permissoes) {
                authorities.add(new SimpleGrantedAuthority(auth.getPermissao().toString()));
            }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void adicionarPermissao(Permissao permissao) {
        this.permissoes.add(permissao);
        getPermissoes();
    }
}
