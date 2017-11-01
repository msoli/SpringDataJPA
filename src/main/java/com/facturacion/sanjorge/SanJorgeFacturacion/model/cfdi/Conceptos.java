package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class Conceptos {

    @JsonProperty("Concepto")
    private List<Concepto> concepto;
}
