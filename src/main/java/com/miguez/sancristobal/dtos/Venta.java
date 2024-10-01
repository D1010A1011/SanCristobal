package com.miguez.sancristobal.dtos;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="ventas")
public class Venta {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter@Setter
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate fecha;

    @Getter@Setter
    @ManyToOne
    @JoinColumn(name = "cliente_documento")
    private Cliente cliente;

    @Getter@Setter
    @ManyToOne
    @JoinColumn(name = "usuario_nombre")
    private Usuario vendedor;

    @Getter@Setter
    private String total;

    @Getter@Setter
    private String productos;
}
