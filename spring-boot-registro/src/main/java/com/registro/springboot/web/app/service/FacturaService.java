package com.registro.springboot.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.springboot.web.app.entity.Cliente;
import com.registro.springboot.web.app.entity.Factura;
import com.registro.springboot.web.app.repository.FacturaRepository;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;
	
	public List<Factura> buscarPorCliente(Cliente cliente){
		return facturaRepository.findByCliente(cliente);
	}
	
}
