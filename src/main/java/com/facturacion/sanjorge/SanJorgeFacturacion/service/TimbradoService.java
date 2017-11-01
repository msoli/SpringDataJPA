package com.facturacion.sanjorge.SanJorgeFacturacion.service;

import com.facturacion.sanjorge.SanJorgeFacturacion.model.response.RequestTimbrado;
import com.facturacion.sanjorge.SanJorgeFacturacion.model.response.ResultadoTimbrado;
import com.facturacion.sanjorge.SanJorgeFacturacion.repository.OperacionFxDAO;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
public class TimbradoService {

    private final org.slf4j.Logger LOG = LoggerFactory.getLogger(TimbradoService.class);

    @Autowired
    private Environment environment;

    public void timbraFactura() {

        Client client = ClientBuilder.newClient();

        client.property(ClientProperties.CONNECT_TIMEOUT, 520 * 1000);
        client.property(ClientProperties.READ_TIMEOUT, 520 * 1000);

        WebTarget target = client.target("http://www.clickfactura.com.mx").path("");


        RequestTimbrado req = new RequestTimbrado();

        req.setPassword(environment.getProperty("password"));
        req.setUsuario(environment.getProperty("usuario"));
        req.setComprobante(null);

        ResultadoTimbrado resultadoArray = target.request(MediaType.APPLICATION_JSON)
                                                 .post(Entity.entity(req, MediaType.APPLICATION_JSON), ResultadoTimbrado.class);

    }
}
