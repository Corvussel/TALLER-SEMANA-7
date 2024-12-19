package com.lab6.crud_lab6.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab6.crud_lab6.model.Empleado;
import com.lab6.crud_lab6.repository.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> obtenerEmpleados() {
        return empleadoRepository.findAll();
    }

    public void registrarEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    public Optional<Empleado> buscarEmpleado(Long id) {
        return empleadoRepository.findById(id);
    }

    public void editarEmpleado(Empleado empleado) {
        empleadoRepository.findById(empleado.getId()).ifPresent(empleadoExistente -> {
            empleadoExistente.setNombre(empleado.getNombre());
            empleadoExistente.setNumeroCelular(empleado.getNumeroCelular());
            empleadoExistente.setContrasena(empleado.getContrasena());
            empleadoExistente.setEmail(empleado.getEmail());
            empleadoRepository.save(empleadoExistente);
        });
    }

    public void eliminarEmpleado(Long id) {
        empleadoRepository.findById(id).ifPresent(empleado -> {
            empleadoRepository.delete(empleado);
        });
    }
}
