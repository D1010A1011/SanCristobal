package com.miguez.sancristobal.controllers;

import com.miguez.sancristobal.dtos.Cliente;
import com.miguez.sancristobal.dtos.Usuario;
import com.miguez.sancristobal.services.ClientService;
import com.miguez.sancristobal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {
    @Autowired
    private UserService us;
    @Autowired
    private ClientService cs;


    @GetMapping("/registroUsuario")
    @PreAuthorize("hasRole('ADMIN')")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registroUsuarios";
    }

    @PostMapping("/registroUsuario")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuario.setRol("ADMIN");
        us.registerUser(usuario);
        return "redirect:/";
    }

    @GetMapping("/registroClientes")
    public String formularioClientes(Model model){
        model.addAttribute("cliente",new Cliente());
        return "registroClientes";
    }

    @PostMapping("/registroClientes")
    public String procesarCliente(@ModelAttribute Cliente cliente){
        return "redirect:/inicio";
    }
}
