package com.facturacion.sanjorge.SanJorgeFacturacion.search;

import lombok.Data;

import java.util.Date;

@Data
public class CriteriaSearch {

    private Integer start;
    private Integer length;
    private String sortDir;
    private Integer sortCol;
    private Integer totalRecords;
    private Integer totalFiltered;

    private Date fechaInicioOperacion;
    private Date fechaFinOperacion;
    private Integer estaus;
    private String serie;
    private String folio;


}
