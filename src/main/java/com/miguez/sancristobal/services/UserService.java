package com.miguez.sancristobal.services;

import com.miguez.sancristobal.dtos.Usuario;
import com.miguez.sancristobal.repository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private RepoUser userepo;

    public Usuario registerUser(Usuario user){
        System.out.println(user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userepo.save(user);
    }
    public String obtenerNombre(String email){
        return userepo.findUserByEmail(email).getNombre();
    }

    public Usuario obtenerUsuario(String email){return userepo.findUserByEmail(email);}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = userepo.findUserByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));

        return new User(usuario.getEmail(), usuario.getPassword(),true,true,true,true, authorities);
    }

}
