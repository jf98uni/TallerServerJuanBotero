package com.example.springbootPlusMongodb.Repository;

import com.example.springbootPlusMongodb.collections.Empleado;
import com.example.springbootPlusMongodb.collections.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRecurso extends MongoRepository<Recurso, String> {

}