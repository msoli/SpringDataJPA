/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facturacion.sanjorge.SanJorgeFacturacion.repository;

import com.facturacion.sanjorge.SanJorgeFacturacion.model.ClienteCorreos;
import com.facturacion.sanjorge.SanJorgeFacturacion.model.Contenedor;
import com.facturacion.sanjorge.SanJorgeFacturacion.model.FacturaCancelacion;
import com.facturacion.sanjorge.SanJorgeFacturacion.model.OperacionFx;
import com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi.Comprobante33;
import com.facturacion.sanjorge.SanJorgeFacturacion.model.cfdi.Receptor;
import com.facturacion.sanjorge.SanJorgeFacturacion.search.CriteriaSearch;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author SISTEMAS03
 */
@Repository
public class OperacionFxDAO {

    private final org.slf4j.Logger LOG = LoggerFactory.getLogger(OperacionFxDAO.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert jdbcInsertTemplate;

    private final BeanPropertyRowMapper<FacturaCancelacion> mapperFacturaCancelacion = new BeanPropertyRowMapper<>(FacturaCancelacion.class);
    private final BeanPropertyRowMapper<ClienteCorreos> clienteCorreosRowMapper = new BeanPropertyRowMapper<>(ClienteCorreos.class);


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsertTemplate = new SimpleJdbcInsert(dataSource).withTableName("archivos_correos").usingGeneratedKeyColumns("id_archivo");

    }


    /**
     *
     * Metodo para encontrar las facturas que seran timbradas
     *
     * @param criteria
     * @return
     */
    private List<Contenedor> findFacturasForTimbrado(CriteriaSearch criteria) {

        List<Object> p = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY fecha_operacion asc) AS r  FROM ( ");
        sb.append(" SELECT ");
        sb.append(" CONVERT(NVARCHAR(10),o.fecha_operacion , 126) AS laFechaOperacion, ");
        sb.append(" o.nm_portafolio AS nm_portafolio, ");
        sb.append(" o.nm_operacion , ");
        sb.append(" o.fecha_operacion, ");
        sb.append(" CASE o.tipo_operacion WHEN 'C' THEN 'egreso' ELSE 'ingreso' END as comTipoDeComprobante , ");
        sb.append(" rtrim(ltrim(Str(o.tipo_cambio, 25, 6))) as comTipoCambio, ");
        sb.append(" o.monto_divisa_operada, ");
        //sb.append(" o.monto_divisa AS comTotal,");
        //sb.append(" o.monto_divisa AS comSubTotal, ");
        sb.append(" RTRIM(LTRIM(Str(monto_divisa, 25, 6))) as comTotal, ");
        sb.append(" RTRIM(LTRIM(Str(monto_divisa, 25, 6))) as comSubTotal, ");
        sb.append(" rtrim(ltrim(o.nm_divisa_operada)) as comMoneda, ");
        sb.append(" CONVERT(NVARCHAR(10),o.fecha_operacion , 126)+'T' + CONVERT(NVARCHAR(8), o.hora_operacion, 108) as comFecha,");
        sb.append(" o .nm_divisa, ");
        sb.append(" CASE o.nm_producto ");
        sb.append("WHEN 'EF' THEN 'EFECTIVO' ");
        sb.append("WHEN 'OP' THEN 'ORDEN DE PAGO' ");
        sb.append("WHEN 'GM' THEN 'MEX-DÓLAR' ");
        sb.append("WHEN 'GR' THEN 'GIRO REMESA' ");
        sb.append("WHEN 'TM' THEN 'TRAVEL MONEY' ");
        sb.append("WHEN 'CH' THEN 'CHEQUE' END AS oProducto,");
        sb.append(" pr.nm_producto as conProductoClave,");
        sb.append(" pr.producto as conProductoNombre,");
        sb.append(" pr.producto as conNombreAlternativo,");
        sb.append(" o.monto_divisa_operada as conCantidad,");
//        sb.append(" o.monto_divisa AS conTotal,");
//        sb.append(" o.monto_divisa AS conSubTotal,");
        sb.append(" RTRIM(LTRIM(Str(monto_divisa, 25, 6))) as conTotal, ");
        sb.append(" RTRIM(LTRIM(Str(monto_divisa, 25, 6))) as conSubTotal, ");

        sb.append(" o.tipo_cambio as conPrecioVenta,");

//        sb.append(" ('VENTA DE $'+ rtrim(ltrim(CONVERT(varchar(25), CAST(o.monto_divisa_operada as money),1))) ");
//        sb.append(" + ' '+ rtrim(ltrim(o.nm_divisa_operada)) + ' DE ' ");
//        sb.append(" + (CASE o.nm_producto WHEN 'EF' THEN 'EFECTIVO'  WHEN 'CH' THEN 'CHEQUE' END) ");
//        sb.append(" + ' AL T.C. ' + rtrim(ltrim(Str(o.tipo_cambio, 25, 6))) + ' EQ. M.N. $' ");
//        sb.append(" + rtrim(ltrim(CONVERT(varchar(25), CAST(o.monto_divisa as money),1))) ");
//        sb.append(" + ' RECIBE: $'+ rtrim(ltrim(CONVERT(varchar(25), CAST(o.monto_divisa as money),1))) ");
//        sb.append(" + ' ENTREGA: $' +  rtrim(ltrim(CONVERT(varchar(25), CAST(o.monto_divisa_operada as money),1)))) ");
//        sb.append(" as conDescripcion,");
        sb.append(" CASE o.tipo_operacion WHEN 'C' THEN ");
        sb.append(" ('COMPRA DE $'+ rtrim(ltrim(CONVERT(varchar(25), CAST(o.monto_divisa_operada AS money),1))) ");
        sb.append(" + ' '+ rtrim(ltrim(o.nm_divisa_operada)) + ' DE ' + ");
        sb.append(" (CASE o.nm_producto ");
        sb.append(" WHEN 'EF' THEN 'EFECTIVO' ");
        sb.append(" WHEN 'OP' THEN 'ORDEN DE PAGO' ");
        sb.append(" WHEN 'GM' THEN 'MEX-DÓLAR' ");
        sb.append(" WHEN 'GR' THEN 'GIRO REMESA' ");
        sb.append(" WHEN 'TM' THEN 'TRAVEL MONEY' ");
        sb.append(" WHEN 'CH' THEN 'CHEQUE' END) ");
        sb.append(" + ' AL T.C. ' + rtrim(ltrim(Str(o.tipo_cambio, 25, 6))) + ' EQ. M.N. $' + rtrim(ltrim(CONVERT(varchar(25), CAST(o.monto_divisa AS money),1))) ");
        sb.append(" + ' RECIBE: $'+ rtrim(ltrim(CONVERT(varchar(25), CAST(o.monto_divisa AS money),1))) ");
        sb.append(" + ' ENTREGA: $' + rtrim(ltrim(CONVERT(varchar(25), CAST(o.monto_divisa_operada AS money),1))))");
        sb.append(" ELSE ");
        sb.append(" ('VENTA DE $'+ rtrim(ltrim(CONVERT(varchar(25), CAST(o.monto_divisa_operada AS money),1))) ");
        sb.append(" + ' '+ rtrim(ltrim(o.nm_divisa_operada)) + ' DE ' + ");
        sb.append(" (CASE o.nm_producto ");
        sb.append(" WHEN 'EF' THEN 'EFECTIVO' ");
        sb.append(" WHEN 'OP' THEN 'ORDEN DE PAGO'");
        sb.append(" WHEN 'GM' THEN 'MEX-DÓLAR'");
        sb.append(" WHEN 'GR' THEN 'GIRO REMESA'");
        sb.append(" WHEN 'TM' THEN 'TRAVEL MONEY'");
        sb.append(" WHEN 'CH' THEN 'CHEQUE' END)");
        sb.append(" + ' AL T.C. ' + rtrim(ltrim(Str(o.tipo_cambio, 25, 6))) + ' EQ. M.N. $' + rtrim(ltrim(CONVERT(varchar(25), CAST(o.monto_divisa AS money),1))) ");
        sb.append(" + ' RECIBE: $'+ rtrim(ltrim(CONVERT(varchar(25), CAST(o.monto_divisa AS money),1))) ");
        sb.append(" + ' ENTREGA: $' + rtrim(ltrim(CONVERT(varchar(25), CAST(o.monto_divisa_operada AS money),1))))");
        sb.append(" END ");
        sb.append(" AS conDescripcion,");
        sb.append(" s.direccion as expDireccion,");
        sb.append(" s.cp as expCp,");
        sb.append(" upper(pa.nombre) as expPais,");
        sb.append(" upper(e.nombre) as expEstado,");
        sb.append(" upper(e.nombre_corto) as expNombreCorto,");
        sb.append(" e.nm_pais,");
        sb.append(" o.nm_cliente as nmCliente,");//id
        sb.append(" c.rfc as cliRfc,");
        sb.append(" c.nombre as cliNombre,");
        sb.append(" c.direccion as cliCalle,");
        sb.append(" c.num_exterior AS cliNoExterior,");
        sb.append(" c.num_interior AS cliNoInterior,");
        sb.append(" UPPER(c.colonia) as cliColonia, ");
        sb.append(" c.nm_cliente as referencia,");
        sb.append(" upper(c.poblacion) as cliMunicipio, ");
        sb.append(" upper(c.estado) as cliEstado, ");
        sb.append(" UPPER((select nombre from pais where nm_pais = c.nm_pais)) as cliPais,");
        sb.append(" c.codigo_postal as cliCodigoPostal,");
        sb.append(" o.nm_cliente_tmp as nmClienteTemp,");//id
        sb.append(" ct.rfc as tRfc,");
        sb.append(" LTRIM(RTRIM(ct.clte_nombre))+ ' '  + LTRIM(RTRIM(ISNULL(ct.clte_apellidop, '')))  + ' ' +  LTRIM(RTRIM(ISNULL(ct.clte_apellidom, '')))  AS tNombreCliente,");
        sb.append(" ct.direccion as tDireccion,");
        sb.append(" ct.num_exterior as tNumExterior,");
        sb.append(" ct.num_interior as tNumInterior,");
        sb.append(" ct.colonia as tColonia,");
        sb.append(" ct.nm_cliente_tmp as tReferencia,  ");
        sb.append(" upper(ct.poblacion) as tPoblacion,");
        sb.append(" upper(ct.estado) as tEstado,");
        sb.append(" UPPER( (select nombre from pais where nm_pais = ct.nm_pais)) as tNombrePais,");
        sb.append(" ct.codigo_postal as tCodigoPostal ");
        sb.append(" FROM operacion_fx o ");
        sb.append(" LEFT OUTER JOIN cliente c ON c.nm_cliente = o.nm_cliente ");
//        sb.append(" RIGHT OUTER JOIN clientes_temporales ct ON ct.nm_cliente_tmp = o.nm_cliente_tmp ");
        sb.append(" LEFT OUTER JOIN clientes_temporales ct ON ct.nm_cliente_tmp = o.nm_cliente_tmp ");
        sb.append(" LEFT OUTER JOIN sucursal s ON s.nm_sucursal= o.nm_sucursal_orig ");
        sb.append(" LEFT OUTER JOIN pais pa ON pa.nm_pais = s.nm_pais ");
        sb.append(" LEFT OUTER JOIN catestado e ON e.nm_pais = s.nm_pais and e.nombre = s.estado ");
        sb.append(" LEFT OUTER JOIN producto pr on pr.nm_producto = o.nm_producto ");
        sb.append(" WHERE o.status_divisa != '9' ");
        sb.append(" AND  o.status_factura = '0' ");//cambiar a 0

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (criteria.getFechaInicioOperacion() != null) {

            p.add(sdf.format(criteria.getFechaInicioOperacion()));
//            sb.append(" and o.fecha_operacion >= CONVERT(NVARCHAR(10),'2015-03-11', 126) ");
            //sb.append(" and o.fecha_operacion >= CONVERT(NVARCHAR(10),? , 126) ");
            sb.append(" AND CAST(CONVERT(nvarchar(10), o.fecha_operacion, 126) + ' ' + CONVERT(nvarchar(8), o.hora_operacion, 108) as DATETIME) >= convert(datetime, ?, 120)  ");
        }

        if (criteria.getFechaFinOperacion() != null) {

            p.add(sdf.format(criteria.getFechaFinOperacion()));
//            sb.append(" and o.fecha_operacion >= CONVERT(NVARCHAR(10),'2015-03-11', 126) ");
//            sb.append(" and o.fecha_operacion <= CONVERT(NVARCHAR(10),? , 126) ");
            sb.append(" AND CAST(CONVERT(nvarchar(10), o.fecha_operacion, 126) + ' ' + CONVERT(nvarchar(8), o.hora_operacion, 108) as DATETIME) <= convert(datetime, ?, 120)  ");
        }

        sb.append(" ) as a ) as b WHERE   r >=  ? AND r <= ?");

        p.add(criteria.getStart());
        p.add(criteria.getStart() + criteria.getLength());

        LOG.info(sb.toString());


//        return jdbcTemplate.query(sb.toString(), p.toArray(), new ComprobanteRowMapper(criteria));

        return null;
    }


    /**
     * Metodo para buscar las operaciones timbradas
     *
     * @return List
     */
    public List<OperacionFx> searchOperaciones(CriteriaSearch criteria) {


        String[] FIELDS_LIST_COLUMS_ARRAY = {
                "nm_portafolio",
                "nm_operacion",
                "tipo_operacion",
                "fecha_operacion",
                "hora_operacion",
                "nm_divisa",
                "nm_divisa_operada",
                "monto_divisa",
                "status_factura",
                "folio",
                "serie"
        };

        List<Object> params = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

//        if (contar) {
//            sb.append(" SELECT count(*) FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY " + FIELDS_LIST_COLUMS_ARRAY[Integer.valueOf(criteria.getSortCol())] + " " + criteria.getSortDir() + ") AS r ");
//        } else {
        sb.append(" SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY " + FIELDS_LIST_COLUMS_ARRAY[Integer.valueOf(criteria.getSortCol())] + " " + criteria.getSortDir() + ") AS r ");
//        }

        sb.append(" FROM (SELECT ");
        sb.append(" opfx.nm_portafolio, opfx.nm_operacion, opfx.tipo_operacion, opfx.fecha_operacion, opfx.hora_operacion, opfx.nm_divisa, opfx.nm_divisa_operada,  ");
        sb.append(" RTRIM(LTRIM(Str(opfx.monto_divisa, 25, 2))) as monto_divisa, opfx.status_factura, c.nm_cliente,  c.nombre, ct.nm_cliente_tmp, "
                + " LTRIM(RTRIM(ct.clte_nombre))+ ' '  + LTRIM(RTRIM(ISNULL(ct.clte_apellidop, '')))  + ' ' +  LTRIM(RTRIM(ISNULL(ct.clte_apellidom, '')))  AS tNombreCliente, "
                + " c.email_to as cEmail, ct.email_to as ctEmail, ap.serie, ap.folio, ap.error ");

        sb.append(" FROM operacion_fx opfx ");

        sb.append(" LEFT OUTER JOIN app_datos_factura ap ON ap.nm_operacion = opfx.nm_operacion ");
        sb.append(" LEFT OUTER JOIN cliente c ON c.nm_cliente = opfx.nm_cliente ");
        sb.append(" LEFT OUTER JOIN clientes_temporales ct ON ct.nm_cliente_tmp = opfx.nm_cliente_tmp ");

        sb.append(" WHERE ");
        sb.append(" status_factura = ? ");
        params.add(criteria.getEstaus());


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cFin = Calendar.getInstance();
        cFin.setTime(criteria.getFechaFinOperacion());


        if (criteria.getFechaInicioOperacion() != null && criteria.getFechaFinOperacion() != null) {
            params.add(sdf.format(new Date()));
            params.add(sdf.format(cFin.getTime()));
            sb.append(" AND fecha_operacion >= CONVERT(NVARCHAR(10),? , 126) AND fecha_operacion <= CONVERT(NVARCHAR(10),? , 126) ");
        }

        if (criteria.getFechaInicioOperacion() != null && criteria.getFechaFinOperacion() == null) {
            params.add(sdf.format(criteria.getFechaInicioOperacion()));
            params.add(sdf.format(new Date()));
            sb.append(" AND fecha_operacion >= CONVERT(NVARCHAR(10),? , 126) <= CONVERT(NVARCHAR(10),? , 126) ");
        }

        if (criteria.getFechaInicioOperacion() == null && criteria.getFechaFinOperacion() != null) {
            params.add(sdf.format(cFin.getTime()));
            sb.append(" AND fecha_operacion <= CONVERT(NVARCHAR(10),? , 126)   ");
        }

        sb.append(" AND status_divisa = 3 ");//las validas para poder enviarse a timbrar

        sb.append(" ) as a ) as b");

        params.add(criteria.getStart());
        params.add(criteria.getStart() + criteria.getLength());
        sb.append(" WHERE   r >=  ? AND r <= ?");


        return jdbcTemplate.query(sb.toString(), params.toArray(), new OperacionRowMapper());//se agrega <= para sqlserver


    }

    /**
     * @param ids
     * @return
     */
    public List<FacturaCancelacion> findAllFacturasByIds(Integer[] ids) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", ids);


        String sql = " select opfx.nm_operacion, " +
                "adf.serie, " +
                "adf.folio," +
                " adf.uuid, " +
                "opfx.fecha_operacion, " +
                "opfx.hora_operacion, " +
                "opfx.nm_cliente, " +
                "opfx.nm_cliente_tmp, " +
                "adf.rfc_receptor " +
                " FROM operacion_fx opfx " +
                " LEFT OUTER JOIN app_datos_factura adf ON adf.nm_operacion = opfx.nm_operacion " +
                " where   status_factura = 1 and " +
                " opfx.nm_operacion in (:ids)";


        return namedParameterJdbcTemplate.query(sql, parameters, mapperFacturaCancelacion);

    }


    /**
     * @param tipo int 1 = cliente, 2 = cliente tmp
     * @return Receptor
     */
    public Receptor getCliente(int idOperacion, String idCliente, int tipo) {

        String qryCliente = "SELECT o.nm_cliente,"
                + " c.rfc  AS cliRfc, "
                + " c.nombre  AS cliNombre, "
                + " c.direccion  AS cliCalle, "
                + " c.num_exterior  AS cliNoExterior, "
                + " c.num_interior  AS cliNoInterior, "
                + " Upper(c.colonia)   AS cliColonia, "
                + " c.nm_cliente   AS referencia, "
                + " Upper(c.poblacion)  AS cliMunicipio, "
                + " Upper(c.estado)  AS cliEstado, "
                + " Upper((SELECT nombre FROM   pais  WHERE  nm_pais = c.nm_pais)) AS cliPais, "
                + " c.codigo_postal  AS cliCodigoPostal"
                + " FROM   operacion_fx o "
                + " LEFT  OUTER JOIN cliente c ON c.nm_cliente = o.nm_cliente "
                + " WHERE o.nm_operacion = ?"
                + " and c.nm_cliente = ?";

        String qryClienteTmp = "SELECT o.nm_cliente ,"
                + " o.nm_cliente_tmp ,"
                + " ct.rfc  AS tRfc, "
                + " Ltrim(Rtrim(ct.clte_nombre)) + ' ' +Ltrim(Rtrim(Isnull(ct.clte_apellidop, '')))  + ' ' + Ltrim(Rtrim(Isnull(ct.clte_apellidom, ''))) AS tNombreCliente, "
                + " ct.direccion          AS tDireccion, "
                + " ct.num_exterior       AS tNumExterior, "
                + " ct.num_interior       AS tNumInterior, "
                + " ct.colonia            AS tColonia, "
                + " ct.nm_cliente_tmp     AS tReferencia, "
                + " Upper(ct.poblacion)   AS tPoblacion, "
                + " Upper(ct.estado) AS tEstado, "
                + " Upper((SELECT nombre  FROM   pais   WHERE  nm_pais = ct.nm_pais))  AS tNombrePais, "
                + " ct.codigo_postal AS tCodigoPostal "
                + " FROM   operacion_fx o "
                + " full OUTER JOIN clientes_temporales ct ON ct.nm_cliente_tmp = o.nm_cliente_tmp "
                + " WHERE o.nm_operacion = ? "
                + " and ct.nm_cliente_tmp = ?";

        try {

            return jdbcTemplate.queryForObject((tipo == 1) ? qryCliente : qryClienteTmp, new Object[]{idOperacion, idCliente}, (tipo == 1) ? new ClienteRowMapper() : new ClienteTmpRowMapper());

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }

    }


    /**
     * @param tipo int 1 = cliente, 2 = cliente tmp
     * @return ClienteCorreos
     */
    public ClienteCorreos getEmailCliente(String idCliente, int tipo) {

        String qryCliente = "select email_to, email_cc from cliente where nm_cliente = ?";

        String qryClienteTmp = "select email_to, email_cc from clientes_temporales where nm_cliente_tmp = ?";

        try {

            return jdbcTemplate.queryForObject(tipo == 1 ? qryCliente : qryClienteTmp, new Object[]{idCliente}, clienteCorreosRowMapper);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ClienteCorreos();
        }

    }


    public boolean updateOperacionFacturaToTimbrada(int idOperacion) {
        int res = 0;
        try {
            res = jdbcTemplate.update("update operacion_fx set status_factura = 1 where nm_operacion = ?", idOperacion);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }

        return res == 1;

    }

    public boolean updateOperacionFacturaToCancelada(int idOperacion) {
        int res = 0;
        try {
            res = jdbcTemplate.update("update operacion_fx set status_factura = 9 where nm_operacion = ?", idOperacion);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }

        return res == 1;

    }


    /**
     * Solo se pueden cancelar las facturas que ya han sido timbrado las
     * pendientes NO, de echo este metodo no seria valido no se puede mandar a
     * cancelar toda la consulta por que no hay filtro en las timbradas
     */
    public List<FacturaCancelacion> findAllForCancelacion() {

        String sql = " select opfx.nm_operacion, adf.serie, adf.folio, adf.uuid, opfx.fecha_operacion, opfx.hora_operacion, opfx.nm_cliente, opfx.nm_cliente_tmp, opfx.fecha_operacion, adf.rfc_receptor" +
                " FROM operacion_fx opfx " +
                "        LEFT OUTER JOIN app_datos_factura adf ON adf.nm_operacion = opfx.nm_operacion " +
                "        where  and status_factura = 1";

        return jdbcTemplate.query(sql, mapperFacturaCancelacion);//se agrega <= para sqlserver
    }


    private final class ClienteRowMapper implements RowMapper<Receptor> {

        @Override
        public Receptor mapRow(ResultSet rs, int rowNum) throws SQLException {

            Receptor r = new Receptor();

            r.setRfc(rs.getString("cliRfc"));
            r.setNombre(rs.getString("cliNombre"));
            r.setNumRegIdTrib("");
            r.setUsoCFDI("");
            r.setResidenciaFiscal("");

//            r.setCalle(rs.getString("cliCalle"));
//            r.setNoExterior(rs.getString("cliNoExterior"));
//            r.setNoInterior(rs.getString("cliNoInterior"));
//            r.setColonia(rs.getString("cliColonia"));
//            r.setMunicipio(rs.getString("cliMunicipio"));
//            r.setEstado(rs.getString("cliEstado"));
//            r.setPais(rs.getString("cliPais"));
//            r.setCodigoPostal(rs.getString("cliCodigoPostal"));

            return r;
        }

    }

    private final class ClienteTmpRowMapper implements RowMapper<Receptor> {

        @Override
        public Receptor mapRow(ResultSet rs, int rowNum) throws SQLException {

            Receptor r = new Receptor();

            r.setRfc(rs.getString("tRfc"));
            r.setNombre(rs.getString("tNombreCliente"));
            r.setNumRegIdTrib("");
            r.setUsoCFDI("");
            r.setResidenciaFiscal("");
//            r.setCalle(rs.getString("tDireccion"));
//            r.setNoExterior(rs.getString("tNumExterior"));
//            r.setNoInterior(rs.getString("tNumInterior"));
//            r.setColonia(rs.getString("tColonia"));
//
//            r.setMunicipio(rs.getString("tPoblacion"));
//
//            r.setEstado(rs.getString("tEstado"));
//            r.setPais(rs.getString("tNombrePais"));
//            r.setCodigoPostal(rs.getString("tCodigoPostal"));

            return r;
        }

    }


    private final class OperacionRowMapper implements RowMapper<OperacionFx> {

        @Override
        public OperacionFx mapRow(ResultSet rs, int i) throws SQLException {
            OperacionFx o = new OperacionFx();

            o.setNmPortafolio(rs.getString("nm_portafolio"));
            o.setNmOperacion(rs.getInt("nm_operacion"));

            o.setTipoOperacion(rs.getString("tipo_operacion").charAt(0));
            o.setFechaOperacion(rs.getTimestamp("fecha_operacion"));
            o.setHoraOperacion(rs.getTimestamp("hora_operacion"));
            o.setNmDivisa(rs.getString("nm_divisa"));
            o.setNmDivisaOperada(rs.getString("nm_divisa_operada"));
            o.setMontoDivisa(rs.getString("monto_divisa"));
            o.setStatusFactura(rs.getString("status_factura").charAt(0));

            if (rs.getString("nm_cliente_tmp") != null && !rs.getString("nm_cliente_tmp").trim().equals("")) {
                o.setNmCliente(rs.getString("nm_cliente_tmp"));
                o.setNombre(rs.getString("tNombreCliente"));
                o.setEmail(rs.getString("ctEmail"));

            } else {

                o.setNmCliente(rs.getString("nm_cliente"));
                o.setNombre(rs.getString("nombre"));
                o.setEmail(rs.getString("cEmail"));

            }

            o.setSerie(rs.getString("serie"));
            o.setFolio(rs.getString("folio"));
            o.setError(rs.getString("error"));

            return o;

        }

    }


}
