package com.example.mi_primer_api_java.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class MensajeResponse implements Serializable {

    private String mensaje;
    private Object object;

}


