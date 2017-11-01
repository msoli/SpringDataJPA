package com.facturacion.sanjorge.SanJorgeFacturacion.repository;


import com.facturacion.sanjorge.SanJorgeFacturacion.model.TbFacturaElectronica;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Repository
public interface TbFacturaElectronicaRepository extends JpaRepository<TbFacturaElectronica, Integer>, QueryDslPredicateExecutor<TbFacturaElectronica>, JpaSpecificationExecutor {

    @Query("select a from TbFacturaElectronica a where a.fechaOperacion >= :inicio and a.fechaOperacion <= :fin and (a.uuid is null or a.uuid = '') and (a.error is null or a.error = '')")
    List<TbFacturaElectronica> findAllForTimbrado(Pageable page, @Param("inicio") Date fechaInicioOperacion, @Param("fin") Date fechaFinOperacion);

    @Modifying
    @Transactional
    @Query("UPDATE TbFacturaElectronica SET uuid =:uuid, folioFactura = :folio WHERE idOperacion = :idOperacion")
    Integer updateTimbradaByIdOperacion(@Param("idOperacion") int idOperaion, @Param("uuid") String uuid, @Param("folio") String folio);

    @Modifying
    @Transactional
    @Query("UPDATE TbFacturaElectronica SET error =:value WHERE idOperacion = :idOperacion")
    Integer updateErrorByIdOperacion(@Param("idOperacion") int idOperaion, @Param("value") String value);

}
