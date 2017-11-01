package com.facturacion.sanjorge.SanJorgeFacturacion.model;

import java.util.Date;

import com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi.Comprobante33;
import lombok.Data;

@Data
public class Contenedor {

    private int idOperacion;
    private Date fechaOperacion;

    //datos adicionales
    private String moneda;
    private String tipoCambio;
    private String instrucciones;

    private Comprobante33 c;

}
