package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Emisor {

    @JsonProperty("Rfc")
    private String rfc;

    @JsonProperty("Nombre")
    private String nombre;

    @JsonProperty("RegimenFiscal")
    private String regimenFiscal;
}
