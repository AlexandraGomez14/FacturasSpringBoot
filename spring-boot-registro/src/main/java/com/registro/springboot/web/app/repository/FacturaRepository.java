package com.registro.springboot.web.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.springboot.web.app.entity.Cliente;
import com.registro.springboot.web.app.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, String> {

	List<Factura> findByCliente(Cliente cliente);
	
}
