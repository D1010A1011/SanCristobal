package com.miguez.sancristobal.repository;

import com.miguez.sancristobal.dtos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoClientes extends JpaRepository<Cliente,Long> {
    Cliente findByDocumento(String documento);
}
