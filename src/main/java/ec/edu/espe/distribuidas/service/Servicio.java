/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.service;

import ec.edu.espe.distribuidas.dao.PreguntaAutogestionRepository;
import ec.edu.espe.distribuidas.model.PreguntaAutogestion;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author bran-
 */
@Service
public class Servicio {
    
    private final PreguntaAutogestionRepository autogestionRepository;

    public Servicio(PreguntaAutogestionRepository autogestionRepository) {
        this.autogestionRepository = autogestionRepository;
    }
    
    public List<PreguntaAutogestion> obtenerPreguntasActivas(){
        return this.autogestionRepository.findByEstado("ACT");
    }
    
}
