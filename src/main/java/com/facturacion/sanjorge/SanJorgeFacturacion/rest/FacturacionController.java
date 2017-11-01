package com.facturacion.sanjorge.SanJorgeFacturacion.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RestController
public class FacturacionController {

    @GetMapping(path = "/")
    public Collection getAllBooks() {
        String[] x = new String[]{"a", "b"};

        return Arrays.asList(x);
    }

}
