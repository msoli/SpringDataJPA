
package com.facturacion.sanjorge.SanJorgeFacturacion.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OperacionFx implements Serializable {


    private String nmPortafolio;
    private Integer nmOperacion;
    private Character tipoOperacion;
    private Date fechaOperacion;
    private Date horaOperacion;
    private String nmDivisa;
    private String nmDivisaOperada;
    private String montoDivisa;
    private Character statusFactura;
    private String nmCliente;
    private String nombre;
    //private String nmClienteTmp;
//    private String clteNombre;

    private String serie;
    private String folio;
    private String email;
    private String error;


}
