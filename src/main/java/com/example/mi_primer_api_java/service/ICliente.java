package com.example.mi_primer_api_java.service;

import com.example.mi_primer_api_java.model.dto.ClienteDto;
import com.example.mi_primer_api_java.model.entity.Cliente;

public interface ICliente {

    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);
}
