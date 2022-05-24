package com.edib.practica1.controller;

import com.edib.practica1.model.TablaClientes;
import com.edib.practica1.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:9083")
@Controller
@RequestMapping(path = "practica")
public class PracticaController {

    @Autowired
    ClientesRepository clienteRepository;


    @GetMapping(path = "tablaClientes")
    public String tablaClientes(Model model){
        model.addAttribute("insert", "insertar cliente");
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes";
    }

    @GetMapping(path = "delete")
    public String delete (Model model, @RequestParam long idCliente){
    	clienteRepository.deleteById(idCliente);
        return  "redirect:/practica/tablaClientes";
    }

    @GetMapping(path = "updateCliente")
    public String updateCliente (Model model, @RequestParam long idCliente){
        model.addAttribute("update", true);
        model.addAttribute("intupdate", 0);
        model.addAttribute("cliente", clienteRepository.findById(idCliente));
        return "altaClientes";
    }

    @GetMapping(path = "insertCliente")
    public String insertCliente (Model model){
        model.addAttribute("update", false);
        model.addAttribute("intupdate", 1);
        model.addAttribute("cliente", new TablaClientes());
        return "altaClientes";
    }

    @PostMapping(path = "saveCliente")
    public String saveCliente( Model model,@ModelAttribute TablaClientes tablaClientes,@RequestParam int update){

        if (tablaClientes.getNombre().length() < 3) {
            if (update == 1) {
                model.addAttribute("update", false);
                model.addAttribute("intupdate", 1);
            } else {
                model.addAttribute("update", true);
                model.addAttribute("intupdate", 0);
            }
            model.addAttribute("cliente", tablaClientes);
            model.addAttribute("isError", true);
            model.addAttribute("error", "El nombre tiene que tener más de 3 letras.");

            return "altaClientes";
        }

        clienteRepository.save(tablaClientes);

        return "redirect:/practica/tablaClientes";
    }
}