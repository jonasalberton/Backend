package com.edu.atividade.capitulo;

import com.edu.atividade.aula.Aula;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Capitulo {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @OneToMany
    private List<Aula> aulas;
}
