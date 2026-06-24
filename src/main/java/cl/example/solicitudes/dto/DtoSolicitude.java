package cl.example.solicitudes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record DtoSolicitude(
    Long Id,
    @Schema(description = "descripcion de la solicitud")
    @NotBlank(message = "la solicitud debe tener descripcion ") 
    @Size(message = "debemos tener entre 4 a 255 caracteres", max = 255, min = 4)
    String descripcion,
    @Schema(description = "peso maximo de que cargara el dron")
    @PositiveOrZero(message= "el pesso deber ser entre 0 o mayor")
    double peso,
    @Schema(description = "ubicacon donde iniciara y finalizara el vuelo")
    @NotBlank(message = "la ubicacion nunca debe esta vacia")
    @Size(message = "la ubicacion debe tener entre 5 a 150", max = 150, min = 5)
    String ubicacion,
    @Schema(description = "la atencion fue respondida por la empresa")
    Boolean atendiada,
    @Schema(description = "el tipo de trabajo que se plantea ")
    @NotBlank(message = "debe tener un tipo")
    String tipo,
    @Schema(description = "Rut de la empresa que crea la solicitud")
    @NotBlank(message = "la empresa mandante debe tener rut")
    String rutEmpresaMandante,
    @Schema(description = "rut de la empresa a la que se hace la solicitud")
    @NotBlank(message =  "la empresa a la que se le pide la solicitud debe tener rut")
    String rutEmpresaProveedora,
    @Schema(description = "region donde se realizara el vuelo",
        example = "REGIÓN DE ARICA Y PARINACOTA")
    @NotBlank(message =  "deve ingreasr la region")
    @Size(message = "debemos tener entre 4 a 150 caracteres", max = 150, min = 5)
    String region
) {

}
