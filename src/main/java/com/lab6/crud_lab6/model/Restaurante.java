package com.lab6.crud_lab6.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data 
public class Restaurante {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    private String nombre;

    private String dni;

    private Date fechaReserva;

    private int nroPersonas;

    private int sede; 
   
}
