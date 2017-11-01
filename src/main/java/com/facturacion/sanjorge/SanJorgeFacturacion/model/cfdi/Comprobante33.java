package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Comprobante33 {

    @JsonProperty("Serie")
    private String serie;

    @JsonProperty("Folio")
    private String folio;

    @JsonProperty("Fecha")
    private String fecha;

    @JsonProperty("SubTotal")
    private String subTotal;

    @JsonProperty("Moneda")
    private String moneda;

    @JsonProperty("Total")
    private String total;

    @JsonProperty("TipoDeComprobante")
    private String tipoDeComprobante;

    @JsonProperty("FormaPago")
    private String formaPago;

    @JsonProperty("MetodoPago")
    private String metodoPago;

    @JsonProperty("CondicionesDePago")
    private String condicionesDePago;

    @JsonProperty("Descuento")
    private String descuento;

    @JsonProperty("TipoCambio")
    private String tipoCambio;

    @JsonProperty("LugarExpedicion")
    private String lugarExpedicion;

    @JsonProperty("Confirmacion")
    private String confirmacion;

    @JsonProperty("Version")
    private String version;

    //@JsonProperty("Sello")
    //private String sello;

    //@JsonProperty("Certificado")
    //private String certificado;

    //@JsonProperty("NoCertificado")
    //private String noCertificado;

    @JsonProperty("CfdiRelacionados")
    private CfdiRelacionados cfdiRelacionados;

    @JsonProperty("Emisor")
    private Emisor emisor;

    @JsonProperty("Receptor")
    private Receptor receptor;

    @JsonProperty("Conceptos")
    private Conceptos conceptos;

    @JsonProperty("Impuestos")
    private Impuestos impuestos;

    @JsonProperty("TimbreFiscalDigital")
    private TimbreFiscalDigital timbreFiscalDigital;

}


