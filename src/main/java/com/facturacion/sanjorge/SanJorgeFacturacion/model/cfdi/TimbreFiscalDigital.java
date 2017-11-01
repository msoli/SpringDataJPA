package com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class TimbreFiscalDigital {

    @JsonProperty("Version")
    private String version;

    @JsonProperty("UUID")
    private String uuid;

    @JsonProperty("FechaTimbrado")
    private String fechaTimbrado;

    @JsonProperty("RfcProvCertif")
    private String rfcProvCertif;

    @JsonProperty("SelloCFD")
    private String selloCFD;

    @JsonProperty("NoCertificadoSAT")
    private String noCertificadoSAT;

    @JsonProperty("SelloSAT")
    private String selloSAT;
}
