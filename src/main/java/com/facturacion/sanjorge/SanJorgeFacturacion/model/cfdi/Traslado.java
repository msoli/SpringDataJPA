package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Traslado {

    @JsonProperty("Impuesto")
    private String impuesto;

    @JsonProperty("TasaOCuota")
    private String tasaOCuota;

    @JsonProperty("Importe")
    private String importe;

    @JsonProperty("TipoFactor")
    private String tipoFactor;
}
