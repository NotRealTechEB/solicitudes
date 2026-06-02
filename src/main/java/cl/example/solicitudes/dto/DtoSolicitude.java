package cl.example.solicitudes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record DtoSolicitude(
    Long Id,
    @NotBlank(message = "la solicitud debe tener descripcion ") 
    @Size(message = "debemos tener entre 4 a 255 caracteres", max = 255, min = 4)
    String descripcion,
    @PositiveOrZero(message= "el pesso deber ser entre 0 o mayor")
    double peso,
    @NotBlank(message = "la ubicacion nunca debe esta vacia")
    @Size(message = "la ubicacion debe tener entre 5 a 150")
    String ubicacion,
    Boolean atendiada,
    @NotBlank(message = "debe tener un tipo")
    String tipo,
    @NotBlank(message = "la empresa mandante debe tener rut")
    String rutEmpresaMandante,
    @NotBlank(message =  "la empresa a la que se le pide la solicitud debe tener rut")
    String rutEmpresaProveedora
) {

}
