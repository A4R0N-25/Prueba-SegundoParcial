/*
 * Copyright (c) 2021 bran-.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    bran- - initial API and implementation and/or initial documentation
 */
package ec.edu.espe.distribuidas.controller;

import ec.edu.espe.distribuidas.dto.PreguntasRS;
import ec.edu.espe.distribuidas.dto.RespuestasRQ;
import ec.edu.espe.distribuidas.exception.EntityNotFoundException;
import ec.edu.espe.distribuidas.model.PreguntaAutogestion;
import ec.edu.espe.distribuidas.service.Servicio;
import ec.edu.espe.distribuidas.transform.PreguntasTS;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bran-
 */
@RestController
@RequestMapping("/v1/servicio/")
public class Controller {

    private final Servicio servicio;

    public Controller(Servicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity obtenerPreguntasActivas() {
        try {
            List<PreguntaAutogestion> preguntasAutogestion = this.servicio.obtenerPreguntasActivas();
            List<PreguntasRS> preguntasRS = new ArrayList<>();
            for (PreguntaAutogestion preguntas : preguntasAutogestion) {
                preguntasRS.add(PreguntasTS.preguntasTS(preguntas));
            }
            return ResponseEntity.ok(preguntasRS);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity ingresarRespuestasPreguntas(@RequestBody RespuestasRQ respuestasRQ) {
        try {
            this.servicio.insertarRespuestasPreguntas(respuestasRQ);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } /*catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }*/
    }

}
