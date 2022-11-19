package com.edu.usuario;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario,Long>{

    Usuario findByUsername(String userName);

    Optional<Usuario> findById(Long id);
}