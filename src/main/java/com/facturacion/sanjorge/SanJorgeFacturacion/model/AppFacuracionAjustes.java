
package com.facturacion.sanjorge.SanJorgeFacturacion.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "app_facturacion_ajustes")
public class AppFacuracionAjustes {

    @Id
    private Integer id;
    @Column
    private Integer timbradoActivo;
    @Column
    private Date fechaInicioTimbrado;
    @Column
    private Date fechaFinTimbrado;
    @Column
    private String intervaloTimbrado;
    @Column
    private Date fechaRevisionTimbrado;
    @Column
    private Date fechaFinRevisionTimbrado;
    @Column
    private Integer timbrarAtrasadas;
    @Column
    private String error;
    @Column
    private String folioVenta;
    @Column
    private String folioCompra;
    @Column
    private String serieVenta;
    @Column
    private String serieCompra;


}
