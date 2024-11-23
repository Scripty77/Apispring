package com.example.mi_primer_api_java.controller;

import com.example.mi_primer_api_java.model.dto.ClienteDto;
import com.example.mi_primer_api_java.model.entity.Cliente;
import com.example.mi_primer_api_java.model.payload.MensajeResponse;
import com.example.mi_primer_api_java.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("api/v1/")
public class ClienteController {

    @Autowired
    private ICliente clienteService;

    // Methods of the API

    @PostMapping("cliente")
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
        Cliente clienteSave = null;
        try {
            clienteSave = clienteService.save(clienteDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(ClienteDto.builder()
                            .idCliente(clienteSave.getIdCliente())
                            .nombre(clienteSave.getNombre() )
                            .apellido(clienteSave.getApellido())
                            .correo(clienteSave.getCorreo())
                            .fechaRegistro(clienteSave.getFechaRegistro())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("cliente")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto){
        Cliente clienteUpdate = null;
        try {
            clienteUpdate = clienteService.save(clienteDto);
           return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Cliente creado con éxito")
                    .object(ClienteDto.builder()
                            .idCliente(clienteUpdate.getIdCliente())
                            .nombre(clienteUpdate.getNombre())
                            .apellido(clienteUpdate.getApellido())
                            .fechaRegistro(clienteUpdate.getFechaRegistro())
                            .correo(clienteUpdate.getCorreo())
                            .build())
                    .build()
                    , HttpStatus.CREATED);

        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                        .mensaje(exDt.getMessage())
                        .object(null)
                        .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);

        if (cliente == null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No se encontró el cliente")
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        };

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(ClienteDto.builder()
                                .idCliente(cliente.getIdCliente())
                                .nombre(cliente.getNombre())
                                .apellido(cliente.getApellido())
                                .fechaRegistro(cliente.getFechaRegistro())
                                .correo(cliente.getCorreo())
                                .build())
                        .build()
                , HttpStatus.OK);
    }

}
