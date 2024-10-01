package com.miguez.sancristobal.controllers;


import com.miguez.sancristobal.dtos.Producto;
import com.miguez.sancristobal.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/registros")
public class RegistrosController {

    @Autowired
    private ProductoService ps;


    @GetMapping
    public String llamarRegistro(Model model){
        return "registros";
    }

    @GetMapping("/inventario")
    public String fragmentInventario(Model model){
        Map<String,Producto> inventario=ps.listarProductos();
        model.addAttribute("inventario",inventario);
        model.addAttribute("producto",new Producto());
        return "fragments/inventario :: inventario";
    }

    @GetMapping("/vendidos")
    public  String fragmentVentas(Model model){
        return "fragments/vendidos :: vendidos";
    }
}

