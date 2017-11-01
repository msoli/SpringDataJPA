package com.facturacion.sanjorge.SanJorgeFacturacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Created by SISTEMAS03-PC on 27/10/2017.
 */
@Data
@AllArgsConstructor
public class FacturaCancelacion {

    private Integer nmOperacion;
    private String usuario;
    private String password;
    private String uuid;
    private String rfcEmisor;
    private String rfcReceptor;
    private String resultado;
    private Date fechaOperacion;
    private String nmCliente;
    private String nmClienteTmp;
    private String serie;
    private Integer folio;


}

