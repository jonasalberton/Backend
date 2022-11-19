package com.edu.atividade.certificado;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CertificadoRepository extends PagingAndSortingRepository<Certificado, Long> {

    List<Certificado> findByUsuarioUsername(String username);
}
