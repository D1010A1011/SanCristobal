package com.miguez.sancristobal.services;

import com.miguez.sancristobal.dtos.Producto;
import com.miguez.sancristobal.repository.RepoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductoService {


    @Autowired
    private RepoProducto productoRepository;

    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto obtenerProductoCodigo(String codigo) {
        Map<String,Producto> inventario=listarProductos();
        return inventario.getOrDefault(codigo,null);
    }

    public Map<String,Producto> listarProductos() {
        Map<String,Producto> inventario=new HashMap();
        List<Producto> productos=productoRepository.findAll();
        for (Producto producto:productos){
            inventario.put(producto.getCodigo(),producto);
        }
        return inventario;
    }

    public void actualizarStock(String codigo, int cantidad) {
        Producto producto = obtenerProductoCodigo(codigo);
        if (producto != null) {
            producto.setStock(cantidad);
            productoRepository.save(producto);
        }
    }

}
