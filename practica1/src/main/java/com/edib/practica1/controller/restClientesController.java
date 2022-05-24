package com.edib.practica1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edib.practica1.model.TablaClientes;
import com.edib.practica1.repository.ClientesRepository;


@RestController
@RequestMapping("/api")
public class restClientesController {
	
	@Autowired
	ClientesRepository clientesRepository;
	 
	@PostMapping("/clientes")
	public ResponseEntity<TablaClientes> createTablaClientes(@RequestBody TablaClientes tablaClientes) {
		try {
			TablaClientes tablasClientesSave = clientesRepository.save(tablaClientes);
			return new ResponseEntity<>(tablasClientesSave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/clientes")
	public ResponseEntity<List<TablaClientes>> getTablaClientes() {

		try {
			List<TablaClientes> TablaClientesSave = clientesRepository.findAll();
			return new ResponseEntity<>(TablaClientesSave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<TablaClientes> getTablaClientesById(@PathVariable("id") long id) {
		
		Optional<TablaClientes> tablaClienteData = clientesRepository.findById(id);

		if (tablaClienteData.isPresent()) {
			return new ResponseEntity<>(tablaClienteData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		
	
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<TablaClientes> updateTablaClientes(@PathVariable("id") long id, @RequestBody TablaClientes tablaClientes) {
		Optional<TablaClientes> tablaClienteData = clientesRepository.findById(id);

		if (tablaClienteData.isPresent()) {
			TablaClientes tablasClientesSave = tablaClienteData.get();
			tablasClientesSave.setNombre(tablaClientes.getNombre());
			tablasClientesSave.setApellidos(tablaClientes.getApellidos());
			tablasClientesSave.setDireccion(tablaClientes.getDireccion());
			tablasClientesSave.setTelefono(tablaClientes.getTelefono());
			tablasClientesSave.setEmail(tablaClientes.getEmail());
			return new ResponseEntity<>(clientesRepository.save(tablasClientesSave), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<HttpStatus> deleteTablaClientes(@PathVariable("id") long id) {
		try {
			clientesRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
