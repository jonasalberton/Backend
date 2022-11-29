package com.main.entrega;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Data
@Table(name = "entrega")
public class Entrega implements Serializable {

    @Id
    @GeneratedValue(generator="seq_entrega")
    public Integer id;

    @Column(name = "descricao_carga")
    private String descricaoCarga;

    @Column(name = "cep_origem_entrega")
    private String cepOrigemEntrega;

    @Column(name = "cep_destino_entrega")
    private String cepDestinoEntrega;

    @Column(name = "id_entregador")
    private Integer idEntregador;

    @Column(name = "id_tabela_frete")
    private Integer idTabelaFrete;

}
