package com.facturacion.sanjorge.SanJorgeFacturacion.service;



import com.facturacion.sanjorge.SanJorgeFacturacion.model.response.RequestTimbrado;
import com.facturacion.sanjorge.SanJorgeFacturacion.model.response.ResultadoTimbrado;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * Created by SISTEMAS03-PC on 10/04/2017.
 */
@Component
@Scope("prototype")
public class TimbradoRunnable implements Runnable {

    private final org.slf4j.Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final org.slf4j.Logger LOG2 = LoggerFactory.getLogger("registroFacturasLog");

    private final Environment environment;


    private File archivo;

    public TimbradoRunnable(Environment environment) {
        this.environment = environment;


    }


    @Override
    public void run() {

        FileOutputStream fos = null;

        boolean moverArchivo = true;

        try {

            String base64 = Base64.encodeBase64String(FileUtils.readFileToByteArray(this.archivo));


            Client client = ClientBuilder.newClient();

            client.property(ClientProperties.CONNECT_TIMEOUT, 520 * 1000);
            client.property(ClientProperties.READ_TIMEOUT, 520 * 1000);

            WebTarget target = client.target("http://www.clickfactura.com.mx").path("api/timbrado/timbraxml");
//            WebTarget target = client.target("http://localhost:83").path("api/timbrado/timbraxml");


            RequestTimbrado req = new RequestTimbrado();
            req.setData(base64);
            req.setPassword(environment.getProperty("password"));
            req.setUsuario(environment.getProperty("usuario"));


            LOG.info("\t--------------------------");
            LOG2.info("\t--------------------------");

            LOG.info("Enviado a timbrar: " + this.archivo.getName());
            LOG2.info("Enviado a timbrar: " + this.archivo.getName());
            List<ResultadoTimbrado> resultadoArray = target.request(MediaType.APPLICATION_JSON)
                                                           .post(Entity.entity(req, MediaType.APPLICATION_JSON), new GenericType<List<ResultadoTimbrado>>() {
                                                           });
//            ResultadoTimbrado resultado = target.request(MediaType.APPLICATION_JSON)
//                                                           .post(Entity.entity(req, MediaType.APPLICATION_JSON), ResultadoTimbrado.class);

            for (ResultadoTimbrado resultado : resultadoArray) {

                try {
                    if (resultado.getStatus()) {
                        //movemos el xml y el pdf


                        LOG2.info("OK Timbrado: " + this.archivo.getName() + ", UUID: " + resultado.getMessage().getUuid() + ", Mensaje: " + resultado.getMessage().getMensaje()+"\n");

                        fos = new FileOutputStream(
                                environment.getProperty("outputTimbradoXmlFolder") + File.separatorChar + FilenameUtils.getBaseName(this.archivo.getName()) + " - " +
                                        resultado.getMessage().getUuid() + ".xml");
                        IOUtils.write(Base64.decodeBase64(resultado.getMessage().getXmlData()), fos);
                        fos.flush();
                        fos.close();

                        fos = new FileOutputStream(
                                environment.getProperty("outputTimbradoPdfFolder") + File.separatorChar + FilenameUtils.getBaseName(this.archivo.getName()) + " - " +
                                        resultado.getMessage().getUuid() + ".pdf");
                        IOUtils.write(Base64.decodeBase64(resultado.getMessage().getPdf()), fos);
                        fos.flush();
                        fos.close();


                    } else {

                        LOG2.error("ERROR no timbrado: " + this.archivo.getName());
                        LOG2.error(resultado.toString()+"\n");

                        fos = new FileOutputStream("." + File.separatorChar + "log" + File.separatorChar + FilenameUtils.getBaseName(this.archivo.getName()) + ".xml");
                        IOUtils.write(Base64.decodeBase64(resultado.getMessage().getXmlData()), fos);
                        fos.flush();
                        fos.close();

                    }


                } catch (Exception e) {
                    LOG2.error("Error al procesar respuesta de timbrado, archivo: " + archivo.getName() + "\n" + resultado.toString());
                    LOG.error(e.getMessage(), e);
                }

            }

        } catch (Exception e) {
            //se deja el archivo para tomarse posteriormente otra vez
            LOG.error("Error en el envio a timbrar archivo, se reintentara en otra ejecución: " + archivo.getName(), e);
            LOG2.error("Error en el envio a timbrar archivo, se reintentara en otra ejecución: " + archivo.getName());
            moverArchivo = false;
        } finally {


            LOG.info("\t--------------------------");
            LOG2.info("\t--------------------------");
            try {
                //movemos el archivo a la carpeta de procesadoFolder



                if (moverArchivo) {
                    Files.move(Paths.get(this.archivo.getPath()),
                            Paths.get(new File(environment.getProperty("procesadoFolder") + File.separatorChar + this.archivo.getName()).getPath()),
                            StandardCopyOption.REPLACE_EXISTING);
                }

            } catch (IOException e1) {
                LOG.error(e1.getMessage());
            }

            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (Exception e) {
                    LOG.error(e.getMessage());
                }
            }

        }


    }


    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }
}
