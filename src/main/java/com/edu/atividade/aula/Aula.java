package com.edu.atividade.aula;

import com.edu.atividade.file.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Aula {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @OneToOne
    private File file;
}
