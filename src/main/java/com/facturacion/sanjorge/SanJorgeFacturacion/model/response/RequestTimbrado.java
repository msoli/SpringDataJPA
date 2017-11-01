package com.facturacion.sanjorge.SanJorgeFacturacion.model.response;

import com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi.Comprobante33;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by SISTEMAS03-PC on 21/09/2017.
 */
@Data
public class RequestTimbrado {
    private String usuario;
    private String password;
    private String data;

    @JsonProperty("Comprobante")
    private Comprobante33 comprobante;


}
