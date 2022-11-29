package com.main.custoEntrega;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "custo_entrega")
public class CustoEntrega {

    @Id
    @GeneratedValue(generator="seq_custo_entrega")
    private Integer id;

    @Column(name = "cep_origem_entrega")
    private String cepOrigemEntrega;

    @Column(name = "cep_destino_entrega")
    private String cepDestinoEntrega;

    @Column(name = "id_tabela_freete")
    private Integer idTabelaFreete;

}
