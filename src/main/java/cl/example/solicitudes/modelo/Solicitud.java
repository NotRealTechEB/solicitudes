package cl.example.solicitudes.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false, length = 150)
    private String ubicacion;

    @Column(nullable = false, length = 150)
    private String region;

    @Column(nullable = false)
    private Boolean atendida = false; 

    @Column(nullable = false, length = 30 )
    private String tipo; 

    @Column(name = "rut_empresa_mandante", nullable = false, length = 15) 
    private String rutEmpresaMandante; 

    @Column(name = "rut_empresa_proveedora", nullable = false, length= 15)
    private String  rutEmpresaProveedora;
    
}