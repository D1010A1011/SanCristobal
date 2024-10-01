package com.miguez.sancristobal.repository;

import com.miguez.sancristobal.dtos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepoProducto extends JpaRepository<Producto,Long> {
    ArrayList<Producto> findProductosByNombreContaining(String nombre);
    Producto findByCodigo(String codigo);
    Producto findByCategoria(String Categoría);
}
