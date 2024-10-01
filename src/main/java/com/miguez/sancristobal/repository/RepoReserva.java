package com.miguez.sancristobal.repository;

import com.miguez.sancristobal.dtos.Cliente;
import com.miguez.sancristobal.dtos.Reserva;
import com.miguez.sancristobal.dtos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepoReserva extends JpaRepository<Reserva, Long> {
    ArrayList<Reserva> findByCliente(Cliente cliente);
}

