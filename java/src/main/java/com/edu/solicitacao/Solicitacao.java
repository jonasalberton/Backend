package com.edu.solicitacao;

import com.edu.atividade.Atividade;
import com.edu.solicitacao.enums.StatusSolicitacao;
import com.edu.solicitacao.enums.TipoSolicitacao;
import com.edu.usuario.Usuario;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String descricao;

    @OneToOne(fetch = FetchType.EAGER)
    @NotNull
    private Usuario solicitante;

    @OneToOne(fetch = FetchType.EAGER)
    private Atividade atividade;

    @Enumerated(EnumType.STRING)
    private TipoSolicitacao tipoSolicitacao;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao statusSolicitacao;

    private LocalDate dataSolicitacao;

    private LocalDate dataAtualizacao;
}
