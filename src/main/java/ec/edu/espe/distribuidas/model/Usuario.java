/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author bran-
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @Column(name = "cod_usuario", nullable = false,length = 150)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "identificacion", nullable = false, length = 30)
    private String identificacion;

    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @Column(name = "estado", nullable = false, length = 3)
    private String estado;

    @Column(name = "nro_intentos_fallidos", nullable = false, precision = 5)
    private BigDecimal intentosFallidos;

    @Column(name = "fecha_ultima_session", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

}
