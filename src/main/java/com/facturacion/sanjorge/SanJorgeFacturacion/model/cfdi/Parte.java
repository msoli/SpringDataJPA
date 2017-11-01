package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Parte {

    @JsonProperty("ClaveProdServ")
    private String claveProdServ;

    @JsonProperty("NoIdentificacion")
    private String noIdentificacion;

    @JsonProperty("Cantidad")
    private String cantidad;

    @JsonProperty("Descripcion")
    private String descripcion;

    @JsonProperty("Unidad")
    private String unidad;

    @JsonProperty("ValorUnitario")
    private String valorUnitario;

    @JsonProperty("Importe")
    private String importe;

    @JsonProperty("InformacionAduanera")
    private List<InformacionAduanera> informacionAduanera;
}
