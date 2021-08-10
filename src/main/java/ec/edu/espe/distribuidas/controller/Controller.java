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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Obtener preguntas activas",
            notes = "Obtiene todas las preguntas que se encuentren activas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok - Se encontraron los registros"),
        @ApiResponse(code = 404, message = "Not Found - No se encontro una entidad"),
        @ApiResponse(code = 500, message = "Internal Server Error - Problemas al realizar la busqueda")})
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
    @ApiOperation(value = "Ingresar respuestas",
            notes = "Se ingresan todas las respuestas de un usuario a las preguntas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok - Guardado exitosamente las respuestas"),
        @ApiResponse(code = 404, message = "Not Found - No se encontro una entidad"),
        @ApiResponse(code = 500, message = "Internal Server Error - Problemas al realizar la busqueda")})
    public ResponseEntity ingresarRespuestasPreguntas(@RequestBody RespuestasRQ respuestasRQ) {
        try {
            List<String> errores = this.servicio.insertarRespuestasPreguntas(respuestasRQ);
            return ResponseEntity.ok(errores);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
