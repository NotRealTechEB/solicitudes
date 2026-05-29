package cl.example.solicitudes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SolicitudDTO {

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotNull(message = "El peso es obligatorio")
    private Double peso;

    @NotBlank(message = "La ubicación no puede estar vacía")
    private String ubicacion;

    @NotNull(message = "Debe indicar si está atendida o no")
    private Boolean atendida;

    @NotNull(message = "El tipo de trabajo es obligatorio")
    private Long tipo;

    @NotNull(message = "El rut de la empresa mandante es obligatorio")
    private String rutEmpresaMandante;

    @NotNull(message = "El rut de la empresa proveedora es obligatorio")
    private String rutEmpresaProveedora;

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

    public String getrutEmpresaMandante() {
        return rutEmpresaMandante;
    }

    public void setrutEmpresaMandante(String rutEmpresaMandante) {
        this.rutEmpresaMandante = rutEmpresaMandante;
    }

    public String getrutEmpresaProveedora() {
        return rutEmpresaProveedora;
    }

    public void setrutEmpresaProveedora(String rutEmpresaProveedora) {
        this.rutEmpresaProveedora = rutEmpresaProveedora;
    }

   
}