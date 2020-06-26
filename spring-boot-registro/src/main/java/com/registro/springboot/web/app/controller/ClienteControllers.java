package com.registro.springboot.web.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.registro.springboot.web.app.entity.Cliente;
import com.registro.springboot.web.app.entity.Factura;
import com.registro.springboot.web.app.service.FacturaService;
import com.registro.springboot.web.app.service.IClienteService;

@Controller
public class ClienteControllers {

	/**
	 * buscado un componente registrdo en el contenedor que implemente 
	 * busca un beans
	 */
	@Autowired
	private IClienteService clienteDao;
	@Autowired
	private FacturaService facturaService;
	
	//pasamos el id por la url
	@GetMapping(value="/detalleCliente/{id}")
	public String detailsClient(@PathVariable(value="id") Long id,Map<String,Object>model ) {
		Cliente cli = clienteDao.findOne(id);
		if (cli == null) {
			return "redirect:/listar";
		}
		List<Factura> facturasList = facturaService.buscarPorCliente(cli);
		model.put("cliente", cli);
		model.put("facturas", facturasList);
		System.out.println(cli);
		model.put("titulo", "Detalle del cliente: "+cli.getNombre());
		return "detalleCliente";
	}
	
	//ruta
	@RequestMapping(value="/listarAllCliente", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		//retornamos la vista
		return "listarClientes";
	}
	
	/**
	 * primera fase es mostrar el formulario al usuario
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/form")
	public String guardarCliente(Map<String, Object> model) {
		Cliente cli = new Cliente();
		
		model.put("cliente", cli);
		model.put("titulo", "Clientes");

		return "form";
	}
	
	/**
	 * segunda fase seria guardar el cliente
	 * @param cli
	 * @return
	 */
	@RequestMapping(value="/form", method = RequestMethod.POST)
	public String saveDataClient(Cliente cli,@RequestParam("file") MultipartFile foto) {
		if (!foto.isEmpty()) {
			Path directorioRecurso =  Paths.get("src//main//resources//static//upload");
			String rootPath = directorioRecurso.toFile().getAbsolutePath();
			//obtener los bytes de la foto 
			try {
				//obtenemos el byte de la imagen
				byte[] bytes = foto.getBytes();
				//ruta final, obtenemos el nombre original del archivo
				Path rutaCompleta = Paths.get(rootPath+"//"+foto.getOriginalFilename());
				Files.write(rutaCompleta,bytes);
				cli.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		clienteDao.saveCliente(cli);
		return "redirect:/listarAllCliente";
	}
	
	@RequestMapping(value="/form/{id}")
	public String updateDataClient(@PathVariable(value="id")Long id, Map<String, Object>model) {
		Cliente cliente = null;
		if (id > 0) {
			System.out.println(cliente);
			//buscamos en la base de datos por medio del dao
			cliente = clienteDao.findOne(id); 
		}else {
			return "redirect:/listarAllCliente";
		}
		//cliente que se usara en la vista
		model.put("titulo", "Cliente");
		model.put("cliente", cliente);
		return "form";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String deleteDataClient(@PathVariable(value= "id") Long id) {
		if (id > 0) {
			clienteDao.delete(id);
		}else {
			System.out.println("ocurrio un problema");
		}
		return "redirect:/listarAllCliente";
	}
	
}
