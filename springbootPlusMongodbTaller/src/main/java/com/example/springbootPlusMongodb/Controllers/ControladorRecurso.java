package com.example.springbootPlusMongodb.Controllers;


import com.example.springbootPlusMongodb.DTOs.RecursoDTO;

import com.example.springbootPlusMongodb.services.ServicioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/recursos")
public class ControladorRecurso {
    @Autowired
    ServicioRecurso servicioRecurso;

    @GetMapping("/{id}")
    public ResponseEntity<RecursoDTO> findbyId(@PathVariable("id") String id) {
        return new ResponseEntity(servicioRecurso.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<RecursoDTO>> findAll() {
        return new ResponseEntity(servicioRecurso.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/consultar/{nombre}")
    public ResponseEntity<String> consult(@PathVariable("nombre") String nombre){
        return new ResponseEntity(servicioRecurso.disponibilidad(nombre),HttpStatus.ACCEPTED);
    }

    @GetMapping("/filtrar/{tipoArea}")
    public ResponseEntity<String> filter(@PathVariable("tipoArea") String tipoArea){
        return new ResponseEntity(servicioRecurso.filtrarAreaTipo(tipoArea),HttpStatus.ACCEPTED);
    }



    @PostMapping("/crear")
    public ResponseEntity<RecursoDTO> create(@RequestBody RecursoDTO recursoDTO) {
        return new ResponseEntity(servicioRecurso.crear(recursoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<RecursoDTO> update(@RequestBody RecursoDTO recursoDTO) {
        if (recursoDTO.getId() != null) {
            return new ResponseEntity(servicioRecurso.modificar(recursoDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reservar/{nombre}")
    public ResponseEntity<String> reservar(@PathVariable("nombre") String nombreO) throws ParseException {

        return new ResponseEntity(servicioRecurso.prestar(nombreO),HttpStatus.ACCEPTED);

    }

    @PutMapping("/regresar/{nombre}")
    public ResponseEntity<String> returnResource(@PathVariable("nombre") String nombre) throws ParseException {

        return new ResponseEntity(servicioRecurso.devolver(nombre),HttpStatus.ACCEPTED);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            servicioRecurso.borrar(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }



}
