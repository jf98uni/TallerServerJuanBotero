package com.example.springbootPlusMongodb.Repository;

import com.example.springbootPlusMongodb.collections.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioEmpleado extends MongoRepository<Empleado, String> {

}