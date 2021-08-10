/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.controller;

import ec.edu.espe.distribuidas.dto.PreguntasRS;
import ec.edu.espe.distribuidas.model.PreguntaAutogestion;
import ec.edu.espe.distribuidas.service.Servicio;
import ec.edu.espe.distribuidas.transform.PreguntasTS;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
