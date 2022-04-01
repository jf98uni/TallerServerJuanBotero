package com.example.springbootPlusMongodb.DTOs;

import java.util.Date;

public class RecursoDTO {
    private String id;
    private String nombre;
    private String tipo;
    private String tema;
    private String fecha;
    private Boolean prestado;
    public RecursoDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo.toLowerCase();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.toLowerCase();
    }

    public String getTema() {
        return tema.toLowerCase();
    }

    public void setTema(String tema) {
        this.tema = tema.toLowerCase();
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getPrestado() {
        return prestado;
    }

    public void setPrestado(Boolean prestado) {
        this.prestado = prestado;
    }
}
