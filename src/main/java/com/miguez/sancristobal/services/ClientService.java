package com.miguez.sancristobal.services;

import com.miguez.sancristobal.dtos.Cliente;
import com.miguez.sancristobal.repository.RepoClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private RepoClientes repoClientes;

    public Cliente registrarCliente(Cliente cliente){
        return repoClientes.save(cliente);
    }
    public Cliente buscarCliente(String documento){
        return repoClientes.findByDocumento(documento);
    }

}
