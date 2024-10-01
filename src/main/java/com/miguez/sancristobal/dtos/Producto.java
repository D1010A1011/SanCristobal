package com.miguez.sancristobal.dtos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productos")
public class Producto {
    @Id@Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter@Setter
    private String nombre;
    @Getter@Setter
    private double precio;
    @Getter@Setter
    private int stock;
    @Getter@Setter
    private String codigo;
    @Getter@Setter
    private String categoria;

}
