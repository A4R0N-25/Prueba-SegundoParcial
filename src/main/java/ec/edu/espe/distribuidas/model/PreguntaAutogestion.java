/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "pregunta_autogestion")
public class PreguntaAutogestion implements Serializable {

    @Id
    @Column(name = "cod_pregunta", nullable = false, precision = 8)
    private BigDecimal codigo;

    @Column(name = "enunciado", nullable = false, length = 100)
    private String enunciado;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

}
