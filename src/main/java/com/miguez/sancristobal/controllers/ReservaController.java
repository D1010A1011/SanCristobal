package com.miguez.sancristobal.controllers;

import com.miguez.sancristobal.dtos.Producto;
import com.miguez.sancristobal.dtos.Reserva;
import com.miguez.sancristobal.dtos.Usuario;
import com.miguez.sancristobal.repository.RepoUser;
import com.miguez.sancristobal.services.ProductoService;
import com.miguez.sancristobal.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ProductoService productoService;
    @Autowired
    private RepoUser repoUser;

    @GetMapping("/reservar/{codigo}")
    public String mostrarFormularioReserva(@PathVariable String codigo, Model model) {
        Producto producto = productoService.obtenerProductoCodigo(codigo);
        model.addAttribute("producto", producto);
        model.addAttribute("reserva", new Reserva());
        return "reservas/formulario";
    }

    @PostMapping("/realizar")
    public String realizarReserva(@ModelAttribute Reserva reserva, Principal principal) {
        reservaService.realizarReserva(
                reserva.getProducto().getCodigo(),
                reserva.getCantidad(),
                principal.getName()
        );
        return "redirect:/reservas/mis-reservas";
    }

    /** @GetMapping("/mis-reservas")
    public String listarReservasDelUsuario(Model model, Principal principal) {
    Usuario usuario = repoUser.findUserByEmail(principal.getName());
    model.addAttribute("reservas", reservaService.listarReservasPorUsuario());
    return "reservas/lista";
    }*/
}
