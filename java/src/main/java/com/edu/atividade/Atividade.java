package com.edu.atividade;

import com.edu.atividade.capitulo.Capitulo;
import com.edu.atividade.file.File;
import com.edu.solicitacao.enums.StatusSolicitacao;
import com.edu.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Atividade {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    private Usuario criador;

    @OneToMany
    private List<Capitulo> capitulos;

    @OneToOne
    private File imagem;

    private LocalDate dataCriacao;

    private StatusSolicitacao status;

    private Long cargaHorariaCriador;

    private Long cargaHorariaAluno;
}
