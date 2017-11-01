package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class InformacionAduanera {
    @JsonProperty("NumeroPedimento")
    private String numeroPedimento;

}
