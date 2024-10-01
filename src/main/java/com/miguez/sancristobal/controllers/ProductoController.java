package com.miguez.sancristobal.controllers;

import com.miguez.sancristobal.dtos.Producto;
import com.miguez.sancristobal.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @GetMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public String mostrarFormularioProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "formularioProductos";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLEADO')")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.agregarProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/buscar/{codigo}")
    public ResponseEntity<Producto> buscarProducto(@PathVariable("codigo") String codigo){
        Producto producto= productoService.obtenerProductoCodigo(codigo);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
