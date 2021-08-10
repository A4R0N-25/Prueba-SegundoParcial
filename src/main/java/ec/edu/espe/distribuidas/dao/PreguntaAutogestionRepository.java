/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.dao;

import ec.edu.espe.distribuidas.model.PreguntaAutogestion;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author bran-
 */
public interface PreguntaAutogestionRepository extends JpaRepository<PreguntaAutogestion,BigDecimal>{
    
    List<PreguntaAutogestion> findByEstado(String estado);
    
}
