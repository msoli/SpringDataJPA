package com.facturacion.sanjorge.SanJorgeFacturacion.repository;


import com.facturacion.sanjorge.SanJorgeFacturacion.model.AppDatosFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppDatosFacturaRepository extends JpaRepository<AppDatosFactura, Integer> {


    AppDatosFactura findOneByNmOperacion(Integer nmOperacion);

    @Query("select count(a.nmOperacion) from AppDatosFactura a")
    Integer countAll();

}
