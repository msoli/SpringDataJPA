package com.facturacion.sanjorge.SanJorgeFacturacion;

import com.facturacion.sanjorge.SanJorgeFacturacion.model.QTbFacturaElectronica;
import com.facturacion.sanjorge.SanJorgeFacturacion.model.TbFacturaElectronica;
import com.facturacion.sanjorge.SanJorgeFacturacion.repository.TbFacturaElectronicaRepository;
import com.facturacion.sanjorge.SanJorgeFacturacion.search.TbFacturaElectronicaSpecs;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SanJorgeFacturacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SanJorgeFacturacionApplication.class, args);
    }


    @Autowired
    private TbFacturaElectronicaRepository tbFacturaElectronicaRepository;


    @Bean
    public CommandLineRunner run() throws Exception {


        return args -> {


//            QTbFacturaElectronica tbFactura = QTbFacturaElectronica.tbFacturaElectronica;
//
//            Predicate predicate = tbFactura.tipoProducto.contains("EFECTIVO");


            Pageable pageable = new PageRequest(0, 1);

//            tbFacturaElectronicaRepository.findAll(predicate, pageable).forEach(item -> {
//                System.out.println(item.getIdOperacion());
//            });

            TbFacturaElectronica electronica = new TbFacturaElectronica();
            electronica.setIdOperacion(12097);

            TbFacturaElectronicaSpecs electronicaSpecs = new TbFacturaElectronicaSpecs(electronica);

            Page<TbFacturaElectronica> page = tbFacturaElectronicaRepository.findAll(electronicaSpecs,pageable );

            page.getContent().stream().forEach(item -> {
                System.out.println(item.getIdOperacion());
            });

            /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date f1 = sdf.parse("2017-10-26");
            Date f2 = sdf.parse("2017-10-27");

            Pageable pageable = new PageRequest(0, 1);

            tbFacturaElectronicaRepository.findAllForTimbrado(pageable, f1, f2).stream().forEach(item -> {
                System.out.println(item.getIdOperacion());
            });

            pageable = new PageRequest(1, 1);

            tbFacturaElectronicaRepository.findAllForTimbrado(pageable, f1, f2).stream().forEach(item -> {
                System.out.println(item.getIdOperacion());
            });*/


        };

    }
}
