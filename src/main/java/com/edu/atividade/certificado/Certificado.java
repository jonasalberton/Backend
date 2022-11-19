package com.edu.atividade.certificado;

import com.edu.atividade.Atividade;
import com.edu.usuario.Usuario;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Certificado {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne
    private Atividade atividade;

    @NotNull
    @OneToOne
    private Usuario usuario;

    private boolean criador;

    private LocalDate dataConclusao;
}
