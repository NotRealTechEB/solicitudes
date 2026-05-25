package cl.example.solicitudes.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    @Column(nullable = false)
    private Boolean atendida = false; 

    @Column(nullable = false)
    private Long tipo; 

    @Column(name = "id_empresa_mandante", nullable = false)
    private Long idEmpresaMandante; 

    @Column(name = "id_empresa_proveedora", nullable = false)
    private Long idEmpresaProveedora;

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean getAtendida() {
        return atendida;
    }

    public void setAtendida(Boolean atendida) {
        this.atendida = atendida;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public Long getIdEmpresaMandante() {
        return idEmpresaMandante;
    }

    public void setIdEmpresaMandante(Long idEmpresaMandante) {
        this.idEmpresaMandante = idEmpresaMandante;
    }

    public Long getIdEmpresaProveedora() {
        return idEmpresaProveedora;
    }

    public void setIdEmpresaProveedora(Long idEmpresaProveedora) {
        this.idEmpresaProveedora = idEmpresaProveedora;
    }

    
}