package com.example.springbootPlusMongodb.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Recurso {
    @Id
    private String id;
    private String nombre;
    private String tipo;
    private String tema;
    private Date fecha;
    private Boolean prestado;


}
