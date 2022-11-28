package com.edu.atividade.file;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "file")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    private String fileName;

    private String fileType;

    private Long size;

    private String src;
}
