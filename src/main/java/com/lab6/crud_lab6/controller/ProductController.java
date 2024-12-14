package com.lab6.crud_lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.lab6.crud_lab6.model.Proveedor;
import com.lab6.crud_lab6.repository.ProveedorService;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/listaProveedores")
    public String showProveedores(Model model) {
        List<Proveedor> proveedores = proveedorService.ObtenerProvedores();
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("proveedor", new Proveedor());
        return "proveedores";
    }

    
    @PostMapping("/RegistrarProveedor")
    public String RegistrarRestaurante(@ModelAttribute Proveedor proveedor, Model model) {
        proveedorService.RegistrarProveedor(proveedor);
        model.addAttribute("proveedor", proveedor);
        return "redirect:/listaProveedores";
    }

    @GetMapping("/EditarProveedor/{id}")
    public String EditarRestaurante(@PathVariable("id") Long id, Model model) {
        Optional<Proveedor> proveedor = proveedorService.BuscarProveedor(id);
        if (proveedor.isPresent()) {
            model.addAttribute("proveedor", proveedor.get());
            return "formulario-proveedor";
        } else {
            return "redirect:/listaProveedores";
        }
    }

    @PostMapping("/GuardarEdicion")
    public String GuardarEdicion(@ModelAttribute Proveedor proveedor) {
        proveedorService.EditarProveedor(proveedor);
        return "redirect:/listaProveedores";
    }

    @GetMapping("/EliminarProveedor/{id}")
    public String EliminarRestaurante(@PathVariable("id") Long id) {
        proveedorService.EliminarProveedor(id);
        return "redirect:/listaProveedores";
    }

}
