/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author bran-
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "respuesta_autogestion")
public class RespuestaAutogestion implements Serializable {

    @EmbeddedId
    private RespuestaAutogestionPK pk;

    @Column(name = "respuesta", nullable = false, length = 100)
    private String respuesta;
    
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario", nullable = false, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Usuario usuario;
    
    @JoinColumn(name = "cod_pregunta", referencedColumnName = "cod_pregunta", nullable = false, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private PreguntaAutogestion preguntaAutogestion;

}
