package com.facturacion.sanjorge.SanJorgeFacturacion.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by SISTEMAS03-PC on 12/04/2017.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoTimbrado {
    private Boolean status;
    @JsonProperty("resp_code")
    private String respCode;
    private Message message;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Resultado{");
        sb.append("status='").append(status).append('\'');
        sb.append(", resp_code='").append(respCode).append('\'');
        sb.append(", message=").append(message);
        sb.append('}');
        return sb.toString();
    }
}
