package com.facturacion.sanjorge.SanJorgeFacturacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_datos_factura")
public class AppDatosFactura {

    @Id
    private Integer nmOperacion;
    @Column
    private String serie;
    @Column
    private Integer folio;
    @Column
    private String uuid;
    @Column
    private String error;
    @Column
    private String rfcReceptor;


    public AppDatosFactura(Integer nmOperacion, String serie, Integer folio, String uuid, String rfcReceptor) {
        this.nmOperacion = nmOperacion;
        this.serie = serie;
        this.folio = folio;
        this.uuid = uuid;
        this.rfcReceptor = rfcReceptor;
    }


}
