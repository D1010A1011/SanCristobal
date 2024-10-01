package com.miguez.sancristobal.controllers;

import com.miguez.sancristobal.dtos.Usuario;
import com.miguez.sancristobal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LaunchController {
    @Autowired
    private UserService us;


    @GetMapping("/login")
    public String login(){
        return "Login";
    }

    @Autowired
    private UserService usuarioService;

    @GetMapping("/inicio")
    public String inicio(Model model, Authentication auth){
        model.addAttribute("nombre",usuarioService.obtenerNombre(auth.getName()));
        return "ingreso";
    }

}
