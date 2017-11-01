package com.facturacion.sanjorge.SanJorgeFacturacion.search;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class DataTableCriterias {


    private int draw;
    private int start;
    private int length;

    private Date fechaInicioOperacion;
    private Date fechaFinOperacion;
    private Integer estaus;
    private String serie;
    private String folio;


    private Map<SearchCriterias, String> search;

    private List<Map<ColumnCriterias, Object>> columns;

    private List<Map<OrderCriterias, String>> order;


    public enum SearchCriterias {
        value,
        regex
    }

    public enum OrderCriterias {
        column,
        dir
    }

    public enum ColumnCriterias {
        data,
        name,
        searchable,
        orderable,
        searchValue,
        searchRegex
    }


    public CriteriaSearch getBusqueda() {

        CriteriaSearch search = new CriteriaSearch();
        search.setLength(length);
        search.setSortCol(Integer.valueOf(order.get(0).get(OrderCriterias.column)));
        search.setSortDir(order.get(0).get(OrderCriterias.dir));
        search.setStart(start);

        search.setFechaInicioOperacion(fechaInicioOperacion);
        search.setFechaFinOperacion(fechaFinOperacion);
        search.setEstaus(estaus);
        search.setSerie(serie);
        search.setFolio(folio);


        return search;
    }


}
