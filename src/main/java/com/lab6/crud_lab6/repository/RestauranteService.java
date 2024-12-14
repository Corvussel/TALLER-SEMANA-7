package com.lab6.crud_lab6.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.lab6.crud_lab6.model.Restaurante;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository RestauranteRepository;

    public List<Restaurante> ObtenerRestaurantes() {
    return RestauranteRepository.findAll();
}

public void RegistrarRestaurante(Restaurante restaurante) {
    RestauranteRepository.save(restaurante);
}

public Optional<Restaurante> buscarRestaurante(Long id) {
    return RestauranteRepository.findById(id);
}

public void EditarRestaurante(Restaurante restaurante) {
    RestauranteRepository.findById(restaurante.getId()).ifPresent(existingRestaurante -> {
        existingRestaurante.setNombre(restaurante.getNombre());               
        existingRestaurante.setDni(restaurante.getDni());                       
        existingRestaurante.setFechaReserva(restaurante.getFechaReserva());      
        existingRestaurante.setNroPersonas(restaurante.getNroPersonas());       
        existingRestaurante.setSede(restaurante.getSede());   
        RestauranteRepository.save(existingRestaurante);
    });
}

public void EliminarRestaurante(Long id) {
    RestauranteRepository.findById(id).ifPresent(restaurante -> {
        RestauranteRepository.delete(restaurante);
    });
}

}
