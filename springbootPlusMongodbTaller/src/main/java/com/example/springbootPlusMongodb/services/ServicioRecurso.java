package com.example.springbootPlusMongodb.services;


import com.example.springbootPlusMongodb.DTOs.RecursoDTO;
import com.example.springbootPlusMongodb.Mappers.RecursoMapper;
import com.example.springbootPlusMongodb.Repository.RepositorioRecurso;

import com.example.springbootPlusMongodb.collections.Recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    public RecursoDTO crear(RecursoDTO recursoDTO) {
        Recurso recurso = mapper.fromDTO(recursoDTO);
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

    public String disponibilidad(String name){

        Recurso recurso = repositorioRecurso.findByNombre(name);

        String id = recurso.getId();

        Recurso recurso1 = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        if (recurso1.getPrestado()){
            return "No esta diponible, fue prestado en la fecha: " +recurso1.getFecha();
        }
        return "Esta diponible";
    }

    public String prestar (String nombre) {
        Recurso recurso = repositorioRecurso.findByNombre(nombre);

        if (recurso.getPrestado()){
            return disponibilidad(recurso.getNombre());
        }

        recurso.setPrestado(true);
        recurso.setFecha((String.valueOf(LocalDateTime.now())));
        mapper.fromCollection(repositorioRecurso.save(recurso));

        return "Recurso reservado";
    }

    public List<RecursoDTO> filtrarAreaTipo (String areaTipo){

        List<String> listTipos = new ArrayList<>();

        List<RecursoDTO> todo = obtenerTodos();

        for (RecursoDTO recurso : todo){
            listTipos.add(recurso.getTipo());
        }


        if (listTipos.contains(areaTipo.toLowerCase())){
            List<Recurso> recursos = repositorioRecurso.findByTipo(areaTipo.toLowerCase());
            return mapper.fromCollectionList(recursos);
        }


        List<Recurso> recursos = repositorioRecurso.findByTema(areaTipo.toLowerCase());
        return mapper.fromCollectionList(recursos);

    }

    public String devolver (String nombre) {
        Recurso recurso = repositorioRecurso.findByNombre(nombre);

        if (recurso.getPrestado() == false){
            return "Este recurso no se puede devolver ya que no esta prestado";
        }

        recurso.setPrestado(false);
        recurso.setFecha(null);
        mapper.fromCollection(repositorioRecurso.save(recurso));

        return "Recurso regresado, Gracias!!";
    }


}
