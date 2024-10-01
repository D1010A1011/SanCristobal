package com.miguez.sancristobal.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguez.sancristobal.dtos.Cliente;
import com.miguez.sancristobal.dtos.Producto;
import com.miguez.sancristobal.dtos.Venta;
import com.miguez.sancristobal.estructuras.Cola;
import com.miguez.sancristobal.services.ProductoService;
import com.miguez.sancristobal.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

@Controller
public class VentasController {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private VentaService ventaService;


    @GetMapping("/vender")
    public String mostrarPuntoVenta(Model model){
        model.addAttribute("productos",productoService.listarProductos());
        return "vender";
    }
    @PostMapping("/vender")
    public String procesarVenta(@RequestParam("carrito") String carritoJson, @RequestParam("client") String cliente,
                                @RequestParam("total") String total, Authentication auth){
        System.out.println("Procesando Venta: Cliente-"+cliente+"");
        ObjectMapper objectMapper= new ObjectMapper();
        StringBuilder productos=new StringBuilder();
        if (cliente.isBlank()||cliente.isEmpty()||cliente==null){
            cliente="No ingresado";
        }

        try {
            Arrays.asList(objectMapper.readValue(carritoJson,Producto[].class))
            Cola carrito= new Cola();
            while (carrito.size()>1){
                productos.append(carrito.poll().getCodigo()+",");
            }
            productos.append(carrito.poll().getCodigo());
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        ventaService.guardarVenta(cliente,auth.getName(),total,productos.toString());
        return "redirect:/vender";
    }



}
