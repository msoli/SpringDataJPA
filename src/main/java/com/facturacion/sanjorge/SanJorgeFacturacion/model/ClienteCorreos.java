package com.facturacion.sanjorge.SanJorgeFacturacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCorreos {

    private String emailTo;
    private String emailCc;
    private String emailEfact;


}
