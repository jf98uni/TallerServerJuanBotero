package com.example.springbootPlusMongodb.Mappers;

import com.example.springbootPlusMongodb.DTOs.EmpleadoDTO;
import com.example.springbootPlusMongodb.DTOs.RecursoDTO;
import com.example.springbootPlusMongodb.collections.Empleado;
import com.example.springbootPlusMongodb.collections.Recurso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {

    public Recurso fromDTO(RecursoDTO dto) {
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setNombre(dto.getNombre());
        recurso.setFecha(dto.getFecha());
        recurso.setTipo(dto.getTipo());
        recurso.setTema(dto.getTema());
        recurso.setPrestado(dto.getPrestado());
        return recurso;
    }

    public RecursoDTO fromCollection(Recurso collection) {
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(collection.getId());
        recursoDTO.setNombre(collection.getNombre());
        recursoDTO.setFecha(collection.getFecha());
        recursoDTO.setTipo(collection.getTipo());
        recursoDTO.setTema(collection.getTema());
        recursoDTO.setPrestado(collection.getPrestado());
        return recursoDTO;
    }

    public List<RecursoDTO> fromCollectionList(List<Recurso> collection) {
        if (collection == null) {
            return null;

        }
        List<RecursoDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Recurso empleado = (Recurso) listTracks.next();
            list.add(fromCollection(empleado));
        }

        return list;
    }
}
