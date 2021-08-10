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
package ec.edu.espe.distribuidas.service;

import ec.edu.espe.distribuidas.dao.PreguntaAutogestionRepository;
import ec.edu.espe.distribuidas.dao.RespuestaAutogestionRepository;
import ec.edu.espe.distribuidas.dao.UsuarioRepository;
import ec.edu.espe.distribuidas.dto.PreguntaRespuestaRQ;
import ec.edu.espe.distribuidas.dto.RespuestasRQ;
import ec.edu.espe.distribuidas.exception.EntityNotFoundException;
import ec.edu.espe.distribuidas.model.PreguntaAutogestion;
import ec.edu.espe.distribuidas.model.RespuestaAutogestion;
import ec.edu.espe.distribuidas.model.Usuario;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author bran-
 */
@Service
@Slf4j
public class Servicio {

    private final PreguntaAutogestionRepository preguntaAutogestionRepository;
    private final UsuarioRepository usuarioRepository;
    private final RespuestaAutogestionRepository respuestaAutogestionRepository;

    public Servicio(PreguntaAutogestionRepository preguntaAutogestionRepository, UsuarioRepository usuarioRepository, RespuestaAutogestionRepository respuestaAutogestionRepository) {
        this.preguntaAutogestionRepository = preguntaAutogestionRepository;
        this.usuarioRepository = usuarioRepository;
        this.respuestaAutogestionRepository = respuestaAutogestionRepository;
    }

    public List<PreguntaAutogestion> obtenerPreguntasActivas() {
        return this.preguntaAutogestionRepository.findByEstado("ACT");
    }

    public void insertarRespuestasPreguntas(RespuestasRQ respuestasRQ) {
        Optional<Usuario> usuarioOpt = this.usuarioRepository.findById(respuestasRQ.getUsuario());

        if (usuarioOpt.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el usuario");
        }

        Usuario usuario = usuarioOpt.get();

        for (PreguntaRespuestaRQ preguntaRespuestaRQ : respuestasRQ.getPreguntasRespuestasRQ()) {
            Optional<PreguntaAutogestion> preguntaOpt = this.preguntaAutogestionRepository.findById(preguntaRespuestaRQ.getPregunta());
            if (preguntaOpt.isEmpty()) {
                log.error("No se encontro la pregunta: {}", preguntaRespuestaRQ.getPregunta());
                continue;
            }
            if (preguntaRespuestaRQ.getRespuesta().length() <= 5) {
                log.error("La respuesta a la pregunta {} no cuenta con la longitud suficiente", preguntaRespuestaRQ.getPregunta());
                continue;
            }

            if ("ACT".equals(preguntaOpt.get().getEstado())) {
                RespuestaAutogestion respuestaAutogestion = new RespuestaAutogestion(preguntaRespuestaRQ.getPregunta(), respuestasRQ.getUsuario());

                respuestaAutogestion.setRespuesta(preguntaRespuestaRQ.getRespuesta());
                respuestaAutogestion.setPreguntaAutogestion(preguntaOpt.get());
                respuestaAutogestion.setUsuario(usuario);

                this.respuestaAutogestionRepository.save(respuestaAutogestion);
            }

        }

    }

}
