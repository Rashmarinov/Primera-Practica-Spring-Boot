package com.edib.practica1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edib.practica1.model.TablaClientes;

public interface ClientesRepository extends JpaRepository<TablaClientes, Long> {
	}
