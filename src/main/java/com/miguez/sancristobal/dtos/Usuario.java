package com.miguez.sancristobal.dtos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id@Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter@Setter
    private String nombre;
    @Getter@Setter
    private String email;
    @Getter@Setter
    private String password;
    @Getter@Setter
    private String rol;
}

