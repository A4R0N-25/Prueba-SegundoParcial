/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.transform;

import ec.edu.espe.distribuidas.dto.PreguntasRS;
import ec.edu.espe.distribuidas.model.PreguntaAutogestion;

/**
 *
 * @author bran-
 */
public class PreguntasTS {
    
    public static PreguntasRS preguntasTS(PreguntaAutogestion preguntaAutogestion){
        
        PreguntasRS preguntasRS = PreguntasRS.builder()
                .codigo(preguntaAutogestion.getCodigo())
                .enunciado(preguntaAutogestion.getEnunciado())
                .build();
        return  preguntasRS;
        
    }
    
}
