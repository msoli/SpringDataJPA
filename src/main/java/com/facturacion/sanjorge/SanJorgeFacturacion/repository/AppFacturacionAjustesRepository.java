package com.facturacion.sanjorge.SanJorgeFacturacion.repository;


import com.facturacion.sanjorge.SanJorgeFacturacion.model.AppFacuracionAjustes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface AppFacturacionAjustesRepository extends JpaRepository<AppFacuracionAjustes, Integer> {


    @Modifying
    @Query("UPDATE AppFacuracionAjustes SET folioCompra = :folio WHERE id = 1")
    boolean updateFolioCompra(@Param("folio") int folio);

    @Modifying
    @Query("UPDATE AppFacuracionAjustes SET folioVenta = :folio WHERE id = 1")
    boolean updateFolioVenta(@Param("folio") int folio);

    @Modifying
    @Query("UPDATE AppFacuracionAjustes SET error = :error WHERE id = 1")
    boolean updateError(@Param("error") String error);

    @Modifying
    @Query("UPDATE AppFacuracionAjustes SET serieVenta = :serie WHERE id = 1")
    boolean updateSerieVenta(@Param("serie") String serie);

    @Modifying
    @Query("UPDATE AppFacuracionAjustes SET serieVenta = :serie WHERE id = 1")
    boolean updateSerieCompra(@Param("serie") String serie);

    @Modifying
    @Query("UPDATE AppFacuracionAjustes SET timbradoActivo =:activo WHERE id = 1")
    boolean updateEstatusTimbrado(@Param("activo") int estatus);


    @Query("select fechaInicioTimbrado from AppFacuracionAjustes where id = 1")
    Date findFechaInicioTimbrado();

    @Query("select fechaFinTimbrado from AppFacuracionAjustes where id = 1")
    Date findFechaFinTimbrado();

    @Query("select fechaRevisionTimbrado from AppFacuracionAjustes where id = 1")
    Date findFechaInicioRevisionTimbrado();

    @Query("select fechaFinRevisionTimbrado from AppFacuracionAjustes where id = 1")
    Date findFechaFinRevisionTimbrado();

    @Query("select max(id)+1 from AppFacuracionAjustes where id = 1")
    Integer findNextFolioVenta();

    @Query("select max(id)+1 from AppFacuracionAjustes where id = 1")
    Integer findNextFolioCompra();

    @Query("select serieCompra from AppFacuracionAjustes where id = 1")
    String findSerieCompra();

    @Query("select serieVenta from AppFacuracionAjustes where id = 1")
    String findSerieVentaById();

    @Query("select timbrarAtrasadas from AppFacuracionAjustes where id = 1")
    Boolean isTimbrarAtrasadas();


}
