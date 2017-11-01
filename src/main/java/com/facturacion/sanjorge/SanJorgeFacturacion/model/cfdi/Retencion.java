package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Retencion {

    @JsonProperty("Impuesto")
    private String impuesto;

    @JsonProperty("Importe")
    private String importe;
}
