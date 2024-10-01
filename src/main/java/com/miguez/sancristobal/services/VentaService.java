package com.miguez.sancristobal.services;


import com.miguez.sancristobal.dtos.Cliente;
import com.miguez.sancristobal.dtos.Producto;
import com.miguez.sancristobal.dtos.Venta;
import com.miguez.sancristobal.dtos.ventaDetallada;
import com.miguez.sancristobal.repository.RepoProducto;
import com.miguez.sancristobal.repository.RepoVentas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService {
    @Autowired
    private ProductoService ps;
    @Autowired
    private RepoVentas repoVentas;
    @Autowired
    private ClientService cs;
    @Autowired
    private UserService us;

    public List<Venta> mostrarVentas(){
        return repoVentas.findAll();
    }
    public ventaDetallada ventaDetallada(Long id){
        Venta venta=repoVentas.findById(id).orElse(null);
        ventaDetallada detallada=new ventaDetallada();

        detallada.setCliente(venta.getCliente().getDocumento());
        detallada.setFecha(venta.getFecha());
        detallada.setVendedor(venta.getVendedor().getId().toString());
        detallada.setTotal(venta.getTotal());

        String[] productos= venta.getProductos().split(",");
        ArrayList<Producto> vendidos=new ArrayList<Producto>();
        for (String codigo:productos){
            vendidos.add(ps.obtenerProductoCodigo(codigo));
        }
        detallada.setProductos(vendidos);
        return detallada;
    }

    public void guardarVenta(String cliente, String vendedor, String total, String productos){
        Venta venta=new Venta();

        Cliente cliente1= cs.buscarCliente(cliente);
        if (cliente1!=null){
            venta.setCliente(cliente1);
        }else {
            cliente1.setDocumento(cliente);
            cs.registrarCliente(cliente1);
            venta.setCliente(cliente1);
        }

        venta.setVendedor(us.obtenerUsuario(vendedor));

        venta.setProductos(productos);

        venta.setTotal(total);
        repoVentas.save(venta);
    }
}
