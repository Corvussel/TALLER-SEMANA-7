package com.lab6.crud_lab6.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.lab6.crud_lab6.model.Proveedor;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository ProveedorRepository;

    public List<Proveedor> ObtenerProvedores() {
        return ProveedorRepository.findAll();
    }

    public void RegistrarProveedor(Proveedor proveedor) {
        ProveedorRepository.save(proveedor);
    }

    public Optional<Proveedor> BuscarProveedor(Long id) {
        return ProveedorRepository.findById(id);
    }

    public void EditarProveedor(Proveedor proveedor) {
        ProveedorRepository.findById(proveedor.getId()).ifPresent(ProveedorExistente -> {
            ProveedorExistente.setNombre(proveedor.getNombre());
            ProveedorExistente.setDireccion(proveedor.getDireccion());
            ProveedorExistente.setTelefono(proveedor.getTelefono());
            ProveedorExistente.setEmail(proveedor.getEmail()); 
            ProveedorRepository.save(ProveedorExistente);
        });
    }

    public void EliminarProveedor(Long id) {
        ProveedorRepository.findById(id).ifPresent(proveedor -> {
            ProveedorRepository.delete(proveedor);
        });
    }

}
