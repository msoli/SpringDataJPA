package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Concepto {

    @JsonProperty("ClaveProdServ")
    private String claveProdServ;

    @JsonProperty("NoIdentificacion")
    private String noIdentificacion;

    @JsonProperty("ClaveUnidad")
    private String claveUnidad;

    @JsonProperty("Unidad")
    private String unidad;

    @JsonProperty("Descripcion")
    private String descripcion;

    @JsonProperty("Importe")
    private String importe;

    @JsonProperty("Cantidad")
    private String cantidad;

    @JsonProperty("ValorUnitario")
    private String valorUnitario;

    @JsonProperty("Descuento")
    private String descuento;

    @JsonProperty("Impuestos")
    private ImpuestosConceptos impuestos;

    //@JsonProperty("CuentaPredial")
    //private CuentaPredial cuentaPredial;

    @JsonProperty("InformacionAduanera")
    private List<InformacionAduanera> informacionAduanera;

    //@JsonProperty("Partes")
    //private List<Parte> partes;
}
