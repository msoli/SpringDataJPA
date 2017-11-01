package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Impuestos {

    @JsonProperty("TotalImpuestosTrasladados")
    private String totalImpuestosTrasladados;

    @JsonProperty("TotalImpuestosRetenidos")
    private String totalImpuestosRetenidos;

    @JsonProperty("Traslados")
    private List<Traslado> traslados;

    @JsonProperty("Retenciones")
    private List<Retencion> retenciones;
}
