package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CfdiRelacionados {

    @JsonProperty("TipoRelacion")
    private String tipoRelacion;

    @JsonProperty("UUID")
    private List<String> uuid;
}
