package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImpuestosConceptos {

    @JsonProperty("Traslados")
    private TrasladosConcepto traslados;

    @JsonProperty("Retenciones")
    private RetencionesConcepto retenciones;
}
