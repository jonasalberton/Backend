package com.main.tabelaFrete;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tabela_frete")
public class TabelaFrete {

    @Id
    @GeneratedValue(generator="seq_tabela_frete")
    private Integer id;

    @Column(name = "descricao_curta")
    private String descricaoCurta;

    @Column(name = "km_percorrido")
    private Double kmPercorrido;

    @Column(name = "taxa_administracao")
    private Double taxaAdministracao;

}
