package com.registro.springboot.web.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.registro.springboot.web.app.entity.Cliente;
import com.registro.springboot.web.app.repository.IClienteRepository;


@Service
public class ClienteServiceImplements implements IClienteService {
		/**
		 * se instancia la clase que extiende de jpa repositori
		 */
		@Autowired
		private IClienteRepository clienteDao;
		
		@Override
		@Transactional(readOnly = true)
		public List<Cliente> findAll() {
			
			return (List<Cliente>) clienteDao.findAll();
		}
	
		@Override
		@Transactional
		public boolean saveCliente(Cliente cliente) {
			clienteDao.save(cliente);
			return false;
		}
	
		@Override
		@Transactional(readOnly = true)
		public Cliente findOne(Long id) {
			// TODO Auto-generated method stub
			return clienteDao.findById(id).orElse(null);
		}
	
		@Override
		@Transactional
		public void delete(Long id) {
			clienteDao.deleteById(id);
		}

}
