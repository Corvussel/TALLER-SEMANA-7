package com.lab6.crud_lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.lab6.crud_lab6.model.Restaurante;
import com.lab6.crud_lab6.repository.RestauranteService;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
private RestauranteService restauranteService;

@GetMapping("/listaRestaurantes")
public String listarRestaurantes(Model model) {
    List<Restaurante> restaurantes = restauranteService.ObtenerRestaurantes();
    model.addAttribute("restaurantes", restaurantes);
    model.addAttribute("restaurante", new Restaurante());
    return "listaRestaurantes";
}

@PostMapping("/RegistrarRestaurante")
public String RegistrarRestaurante(@ModelAttribute Restaurante restaurante) {
    restauranteService.RegistrarRestaurante(restaurante);
    return "redirect:/listaRestaurantes";
}

@GetMapping("/EditarRestaurante/{id}")
public String EditarRestaurante(@PathVariable("id") Long id, Model model) {
    Optional<Restaurante> restaurante = restauranteService.buscarRestaurante(id);
    if (restaurante.isPresent()) {
        model.addAttribute("restaurante", restaurante.get());
        return "EditarRestaurante";
    } else {
        return "redirect:/listaRestaurantes";
    }
}

@PostMapping("/GuardarEdicion")
public String GuardarEdicion(@ModelAttribute Restaurante restaurante) {
    restauranteService.EditarRestaurante(restaurante);
    return "redirect:/listaRestaurantes";
}

@GetMapping("/EliminarRestaurante/{id}")
public String EliminarRestaurante(@PathVariable("id") Long id) {
    restauranteService.EliminarRestaurante(id);
    return "redirect:/listaRestaurantes";
}

}
