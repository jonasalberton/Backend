package com.edu.security;

import com.edu.usuario.Usuario;
import com.edu.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario accountCredentials =  Optional.ofNullable(usuarioRepository.findByUsername(username))
                                                 .orElseThrow( () -> new UsernameNotFoundException("Usuario não encontrado"));

        return new User(accountCredentials.getUsername(), accountCredentials.getPassword(), accountCredentials.getAuthorities());
    }
}
