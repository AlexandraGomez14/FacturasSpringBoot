package com.registro.springboot.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.registro.springboot.web.app.entity.Cliente;

@RestController
@RequestMapping("/WService")
public class ClienteRest {

	@Autowired
	private IClienteService service;
	
	@GetMapping
	public List<Cliente> findAll() {
		return service.findAll();
	}
	
	@PostMapping
	@RequestMapping(value="/guardarCliente")
	public String guardarClientes(@RequestBody Cliente cli) {
		String resultado ="";
		service.saveCliente(cli);
		return resultado;
	}

}
