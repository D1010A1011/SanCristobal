package com.miguez.sancristobal.dtos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter@Setter
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "producto_codigo")
    @Getter@Setter
    private Producto producto;

   @Getter@Setter
   @ManyToOne
   @JoinColumn(name = "cliente_documento")
   private Cliente cliente;

    @Getter@Setter
    private LocalDateTime fechaReserva;

}

