package com.facturacion.sanjorge.SanJorgeFacturacion.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_factura_electronica")
public class TbFacturaElectronica implements Serializable {
    private Integer idOperacion;
    private Integer idSucursal;
    private Integer idCliente;
    private String tipoTransaccion;
    private String tipoProducto;
    private Timestamp fechaOperacion;
    private Timestamp horaOperacion;
    private String divisaOperada;
    private String divisa;
    private BigDecimal montoDivOperada;
    private BigDecimal montoDivisa;
    private BigDecimal tipoCambio;
    private String nombreCliente;
    private String rfcCte;
    private String direccionCte;
    private Double numExtCte;
    private String coloniaCte;
    private String cpCte;
    private String poblacionCte;
    private String estadoCte;
    private String paisCte;
    private Integer formaPago;
    private String moneda;
    private String tipoComprobante;
    private String metodoPago;
    private String lugarExpedicion;
    private String confirmacion;
    private String residenciaFiscal;
    private String numRegIdTrib;
    private String usoCfdi;
    private String cveProdServ;
    private String cveUnidad;
    private BigDecimal descuento;
    private BigDecimal tasaCuota;
    private BigDecimal base;
    private String tipoFactor;
    private Integer impuesto;
    private BigDecimal importe;
    private Integer tipoImpuesto;
    private String numeroPedimento;
    private String uuid;
    private Integer tipoRelacion;
    private Integer folioFactura;
    private String error;

    @Id
    @Column(name = "id_operacion", nullable = false)
    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    @Basic
    @Column(name = "id_sucursal", nullable = false)
    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Basic
    @Column(name = "id_cliente", nullable = false)
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Basic
    @Column(name = "tipo_transaccion", nullable = false, length = 1)
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    @Basic
    @Column(name = "tipo_producto", nullable = false, length = 10)
    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @Basic
    @Column(name = "fecha_operacion", nullable = false)
    public Timestamp getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Timestamp fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    @Basic
    @Column(name = "hora_operacion", nullable = false)
    public Timestamp getHoraOperacion() {
        return horaOperacion;
    }

    public void setHoraOperacion(Timestamp horaOperacion) {
        this.horaOperacion = horaOperacion;
    }

    @Basic
    @Column(name = "divisa_operada", nullable = false, length = 10)
    public String getDivisaOperada() {
        return divisaOperada;
    }

    public void setDivisaOperada(String divisaOperada) {
        this.divisaOperada = divisaOperada;
    }

    @Basic
    @Column(name = "divisa_", nullable = false, length = 10)
    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    @Basic
    @Column(name = "monto_div_operada", nullable = false, precision = 2)
    public BigDecimal getMontoDivOperada() {
        return montoDivOperada;
    }

    public void setMontoDivOperada(BigDecimal montoDivOperada) {
        this.montoDivOperada = montoDivOperada;
    }

    @Basic
    @Column(name = "monto_divisa", nullable = false, precision = 2)
    public BigDecimal getMontoDivisa() {
        return montoDivisa;
    }

    public void setMontoDivisa(BigDecimal montoDivisa) {
        this.montoDivisa = montoDivisa;
    }

    @Basic
    @Column(name = "tipo_cambio", nullable = false, precision = 2)
    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    @Basic
    @Column(name = "nombre_cliente", nullable = false, length = 100)
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Basic
    @Column(name = "rfc_cte", nullable = false, length = 15)
    public String getRfcCte() {
        return rfcCte;
    }

    public void setRfcCte(String rfcCte) {
        this.rfcCte = rfcCte;
    }

    @Basic
    @Column(name = "direccion_cte", nullable = true, length = 50)
    public String getDireccionCte() {
        return direccionCte;
    }

    public void setDireccionCte(String direccionCte) {
        this.direccionCte = direccionCte;
    }

    @Basic
    @Column(name = "num_ext_cte", nullable = true, precision = 0)
    public Double getNumExtCte() {
        return numExtCte;
    }

    public void setNumExtCte(Double numExtCte) {
        this.numExtCte = numExtCte;
    }

    @Basic
    @Column(name = "colonia_cte", nullable = true, length = 100)
    public String getColoniaCte() {
        return coloniaCte;
    }

    public void setColoniaCte(String coloniaCte) {
        this.coloniaCte = coloniaCte;
    }

    @Basic
    @Column(name = "cp_cte", nullable = true, length = 15)
    public String getCpCte() {
        return cpCte;
    }

    public void setCpCte(String cpCte) {
        this.cpCte = cpCte;
    }

    @Basic
    @Column(name = "poblacion_cte", nullable = true, length = 100)
    public String getPoblacionCte() {
        return poblacionCte;
    }

    public void setPoblacionCte(String poblacionCte) {
        this.poblacionCte = poblacionCte;
    }

    @Basic
    @Column(name = "estado_cte", nullable = true, length = 100)
    public String getEstadoCte() {
        return estadoCte;
    }

    public void setEstadoCte(String estadoCte) {
        this.estadoCte = estadoCte;
    }

    @Basic
    @Column(name = "pais_cte", nullable = true, length = 10)
    public String getPaisCte() {
        return paisCte;
    }

    public void setPaisCte(String paisCte) {
        this.paisCte = paisCte;
    }

    @Basic
    @Column(name = "forma_pago", nullable = false)
    public Integer getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(Integer formaPago) {
        this.formaPago = formaPago;
    }

    @Basic
    @Column(name = "moneda", nullable = false, length = 10)
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Basic
    @Column(name = "tipo_comprobante", nullable = false, length = 1)
    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    @Basic
    @Column(name = "metodo_pago", nullable = false, length = 3)
    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Basic
    @Column(name = "lugar_expedicion", nullable = false, length = 15)
    public String getLugarExpedicion() {
        return lugarExpedicion;
    }

    public void setLugarExpedicion(String lugarExpedicion) {
        this.lugarExpedicion = lugarExpedicion;
    }

    @Basic
    @Column(name = "confirmacion", nullable = true, length = 5)
    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    @Basic
    @Column(name = "residencia_fiscal", nullable = true, length = 10)
    public String getResidenciaFiscal() {
        return residenciaFiscal;
    }

    public void setResidenciaFiscal(String residenciaFiscal) {
        this.residenciaFiscal = residenciaFiscal;
    }

    @Basic
    @Column(name = "num_reg_id_trib", nullable = true, length = 50)
    public String getNumRegIdTrib() {
        return numRegIdTrib;
    }

    public void setNumRegIdTrib(String numRegIdTrib) {
        this.numRegIdTrib = numRegIdTrib;
    }

    @Basic
    @Column(name = "uso_CFDI", nullable = false, length = 3)
    public String getUsoCfdi() {
        return usoCfdi;
    }

    public void setUsoCfdi(String usoCfdi) {
        this.usoCfdi = usoCfdi;
    }

    @Basic
    @Column(name = "cve_prod_serv", nullable = false, length = 10)
    public String getCveProdServ() {
        return cveProdServ;
    }

    public void setCveProdServ(String cveProdServ) {
        this.cveProdServ = cveProdServ;
    }

    @Basic
    @Column(name = "cve_unidad", nullable = false, length = 3)
    public String getCveUnidad() {
        return cveUnidad;
    }

    public void setCveUnidad(String cveUnidad) {
        this.cveUnidad = cveUnidad;
    }

    @Basic
    @Column(name = "descuento", nullable = false, precision = 2)
    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    @Basic
    @Column(name = "tasa_cuota", nullable = false, precision = 2)
    public BigDecimal getTasaCuota() {
        return tasaCuota;
    }

    public void setTasaCuota(BigDecimal tasaCuota) {
        this.tasaCuota = tasaCuota;
    }

    @Basic
    @Column(name = "base", nullable = false, precision = 2)
    public BigDecimal getBase() {
        return base;
    }

    public void setBase(BigDecimal base) {
        this.base = base;
    }

    @Basic
    @Column(name = "tipo_factor", nullable = false, length = 10)
    public String getTipoFactor() {
        return tipoFactor;
    }

    public void setTipoFactor(String tipoFactor) {
        this.tipoFactor = tipoFactor;
    }

    @Basic
    @Column(name = "impuesto", nullable = false)
    public Integer getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Integer impuesto) {
        this.impuesto = impuesto;
    }

    @Basic
    @Column(name = "importe", nullable = false, precision = 2)
    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    @Basic
    @Column(name = "tipo_impuesto", nullable = false)
    public Integer getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(Integer tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    @Basic
    @Column(name = "numero_pedimento", nullable = true, length = 30)
    public String getNumeroPedimento() {
        return numeroPedimento;
    }

    public void setNumeroPedimento(String numeroPedimento) {
        this.numeroPedimento = numeroPedimento;
    }

    @Basic
    @Column(name = "UUID", nullable = true, length = 50)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "tipo_relacion", nullable = true)
    public Integer getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(Integer tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    @Basic
    @Column(name = "folio_factura", nullable = true)
    public Integer getFolioFactura() {
        return folioFactura;
    }

    public void setFolioFactura(Integer folioFactura) {
        this.folioFactura = folioFactura;
    }

    @Basic
    @Column(name = "error", nullable = true, length = 50)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


}
