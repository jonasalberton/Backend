package com.edu.atividade.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;


    public File salvar(File file) {
        return fileRepository.save(file);
    }

}
