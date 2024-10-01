package com.miguez.sancristobal.dtos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Getter@Setter
    private String documento;

    @Getter@Setter
    private String nombre;

    @Getter@Setter
    private String telefono;

    @Getter@Setter
    private String correo;

    @Getter@Setter
    private String dirección;

}
