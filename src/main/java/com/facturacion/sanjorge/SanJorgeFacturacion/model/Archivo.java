package com.facturacion.sanjorge.SanJorgeFacturacion.model;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
public class Archivo implements Serializable {

    private static final long serialVersionUID = -6857639787387137353L;

    private final Integer idOperacion;
    private final Integer folio;
    private final String rfcEmisor;
    private final String nameFile;
//    private final String fecha;
    private final String uuid;

    private final String anio;
    private final String mes;
    private final String dia;

    private Boolean existePdf;
    private Boolean existeXml;

    private String correos;

    private String nmCliente;
    private String nmClienteTmp;

    private Integer cancelacion;//aqui inidamos si la factura esta cancelada o no, y evitar reprocesarla

    public Archivo(Integer idOperacion, String rfcEmisor, Date fechaDia,/*, Date fechaHoras,*/ String uuid, String correos, String nmCliente, String nmClienteTmp, Integer folio) {
        this.idOperacion = idOperacion;
        this.rfcEmisor = rfcEmisor;
        this.uuid = uuid;
        this.correos = correos;

        this.nmCliente = nmCliente;
        this.nmClienteTmp = nmClienteTmp;
        this.folio = folio;

        StringBuilder sb = new StringBuilder();

        if (rfcEmisor != null) {
            sb.append(rfcEmisor.trim());
        } else {

        }
        sb.append("-");
        sb.append(folio);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");

        Calendar c = new GregorianCalendar();
        c.setTime(fechaDia);

//        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
//        this.fecha = sdf1.format(fechaDia) + sdf2.format(fechaHoras);
//
//        sb.append(fecha);
        sdf1 = new SimpleDateFormat("yyyy");

        this.anio = sdf1.format(fechaDia);

        sdf1 = new SimpleDateFormat("MM");

//        System.out.println((c.get(Calendar.MONTH) < 10) ? "0" + c.get(Calendar.MONTH) : c.get(Calendar.MONTH) + "");

        this.mes = (c.get(Calendar.MONTH) < 10) ? "0" + (c.get(Calendar.MONTH) + 1) : (c.get(Calendar.MONTH) + 1) + "";

        sdf1 = new SimpleDateFormat("dd");
        this.dia = sdf1.format(fechaDia);

        this.nameFile = sb.toString();

    }


}
