package com.facturacion.sanjorge.SanJorgeFacturacion.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by SISTEMAS03-PC on 12/04/2017.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String codigo;
    private String resultado;
    private String xmlData;
    private String mensaje;
    private String pdf;
    private String uuid;
    private String fechaTimbrado;
    private String rfcProvCertif;
    private String selloCFD;
    private String noCertificadoSAT;
    private String selloSAT;



    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Message{");
        sb.append("codigo='").append(codigo).append('\'');
        sb.append(", resultado='").append(resultado).append('\'');
//        sb.append(", xmlData='").append(xmlData).append('\'');
        sb.append(", mensaje='").append(mensaje).append('\'');
        //sb.append(", pdf='").append(pdf).append('\'');
        sb.append(", uuid='").append(uuid).append('\'');
        sb.append(", fechaTimbrado='").append(fechaTimbrado).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
