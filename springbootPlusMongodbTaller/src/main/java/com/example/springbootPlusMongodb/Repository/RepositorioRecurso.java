package com.example.springbootPlusMongodb.Repository;


import com.example.springbootPlusMongodb.collections.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RepositorioRecurso extends MongoRepository<Recurso, String> {




    Recurso findByNombre (String s);

    List<Recurso> findByTema (String tema);
    List<Recurso> findByTipo (String tipo);
}