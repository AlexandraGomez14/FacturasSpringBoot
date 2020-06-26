package com.registro.springboot.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.registro.springboot.web.app.entity.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long > {

}
