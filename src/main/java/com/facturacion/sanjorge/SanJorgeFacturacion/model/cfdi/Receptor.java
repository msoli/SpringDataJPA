package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Receptor {

    @JsonProperty("Rfc")
    private String rfc;

    @JsonProperty("Nombre")
    private String nombre;

    @JsonProperty("ResidenciaFiscal")
    private String residenciaFiscal;

    @JsonProperty("NumRegIdTrib")
    private String numRegIdTrib;

    @JsonProperty("UsoCFDI")
    private String usoCFDI;


}
