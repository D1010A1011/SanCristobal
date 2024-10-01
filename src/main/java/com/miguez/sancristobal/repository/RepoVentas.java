package com.miguez.sancristobal.repository;

import com.miguez.sancristobal.dtos.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;

public interface RepoVentas extends JpaRepository<Venta,Long> {
    Venta findByFecha(LocalDate fecha);
}
