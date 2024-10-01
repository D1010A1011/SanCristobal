package com.miguez.sancristobal.services;

import com.miguez.sancristobal.dtos.Cliente;
import com.miguez.sancristobal.dtos.Producto;
import com.miguez.sancristobal.dtos.Reserva;
import com.miguez.sancristobal.dtos.Usuario;
import com.miguez.sancristobal.repository.RepoClientes;
import com.miguez.sancristobal.repository.RepoReserva;
import com.miguez.sancristobal.repository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ReservaService {

    @Autowired
    private RepoReserva reservaRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private RepoClientes repoClientes;

    public Reserva realizarReserva(String codigo, int cantidad, String docCliente) {
        Producto producto = productoService.obtenerProductoCodigo(codigo);
        Cliente ciente = repoClientes.findByDocumento(docCliente);

        if (producto != null && ciente != null && producto.getStock() >= cantidad) {
            Reserva reserva = new Reserva();
            reserva.setProducto(producto);
            reserva.setCliente(ciente);
            reserva.setCantidad(cantidad);
            reserva.setFechaReserva(LocalDateTime.now());

            // Actualizar stock del producto
            producto.setStock(producto.getStock() - cantidad);
            productoService.agregarProducto(producto);

            return reservaRepository.save(reserva);
        } else {
            // Manejar error (stock insuficiente, producto no encontrado, etc.)
            return null;
        }
    }

    // Otros métodos como listarReservasPorUsuario, cancelarReserva, etc.
}

