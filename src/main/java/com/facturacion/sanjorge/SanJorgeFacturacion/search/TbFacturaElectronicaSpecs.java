package com.facturacion.sanjorge.SanJorgeFacturacion.search;

import com.facturacion.sanjorge.SanJorgeFacturacion.model.TbFacturaElectronica;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class TbFacturaElectronicaSpecs implements Specification<TbFacturaElectronica> {

    private final TbFacturaElectronica item;

    public TbFacturaElectronicaSpecs(TbFacturaElectronica item) {
        this.item = item;
    }

    @Override
    public Predicate toPredicate(Root<TbFacturaElectronica> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();


        predicates.add(cb.equal(cb.lower(root.get("idOperacion")), item.getIdOperacion()));


        return andTogether(predicates, cb);
    }

    private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}