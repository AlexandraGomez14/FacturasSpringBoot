package com.registro.springboot.web.app.service;

import java.util.List;
import com.registro.springboot.web.app.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();

	public boolean saveCliente(Cliente cliente);
	
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);

}
