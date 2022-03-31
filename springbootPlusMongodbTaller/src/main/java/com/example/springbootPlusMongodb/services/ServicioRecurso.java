package com.example.springbootPlusMongodb.services;


import com.example.springbootPlusMongodb.DTOs.RecursoDTO;
import com.example.springbootPlusMongodb.Mappers.RecursoMapper;
import com.example.springbootPlusMongodb.Repository.RepositorioRecurso;

import com.example.springbootPlusMongodb.collections.Recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioRecurso {
    @Autowired
    RepositorioRecurso repositorioRecurso;
    RecursoMapper mapper = new RecursoMapper();
    public List<RecursoDTO> obtenerTodos() {
        List<Recurso> recurso = (List<Recurso>) repositorioRecurso.findAll();
        return mapper.fromCollectionList(recurso);
    }
    public RecursoDTO obtenerPorId(String id) {
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollection(recurso);
    }
    public RecursoDTO crear(RecursoDTO empleadoDTO) {
        Recurso recurso = mapper.fromDTO(empleadoDTO);
        return mapper.fromCollection(repositorioRecurso.save(recurso));
    }
    public RecursoDTO modificar(RecursoDTO recursoDTO) {
        Recurso recurso = mapper.fromDTO(recursoDTO);
        repositorioRecurso.findById(recurso.getId()).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollection(repositorioRecurso.save(recurso));
    }
    public void borrar(String id) {
        repositorioRecurso.deleteById(id);
    }
}
