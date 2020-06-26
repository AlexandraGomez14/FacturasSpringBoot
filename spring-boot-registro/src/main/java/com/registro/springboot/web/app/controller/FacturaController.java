package com.registro.springboot.web.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.registro.springboot.web.app.entity.Cliente;
import com.registro.springboot.web.app.entity.Factura;
import com.registro.springboot.web.app.service.IClienteService;

@Controller
//ruta para acceder a este controlador
@RequestMapping("/factura")
//se creara una sesion de factura
@SessionAttributes("factura")
public class FacturaController {

	//inyectamos el cliente service
	@Autowired
	private IClienteService clienteService; 
	
	
	//variable que esta definida en entity
	@GetMapping("/form/{clienteId}")//se le pasa el parametro y el tipo de parametro
	public String createFactura(@PathVariable(value = "clienteId") Long clienteId, 
												Map<String, Object>model) {
		//se crea un objeto tipo cliente
		Cliente cliente = clienteService.findOne(clienteId);
		if (cliente == null) {
			System.out.println("El cliente esta viniendo null");
			return "redirect:/listarAllCliente";
		}else {
			//seteo de factura
			Factura factura = new Factura();
			factura.setCliente(cliente);
			model.put("factura", factura);
			model.put("titulo", "Crear Factura");
			System.out.println("se agrego la factura correctamente");
		}
		return "factura/form";
	}
	
	
	
}
