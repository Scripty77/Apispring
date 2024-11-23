package com.example.mi_primer_api_java.model.dao;

import com.example.mi_primer_api_java.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {
}
