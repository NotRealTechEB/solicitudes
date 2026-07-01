package cl.example.solicitudes.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import cl.example.solicitudes.dto.DtoSolicitude;
import cl.example.solicitudes.excepcion.ResourceError;
import cl.example.solicitudes.service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1.6x/solicitudes")
@Tag(name = "Solicitud",
    description = "esta api se encarga de comunicarse con la BD para hacer el crud basico y flitros de busqueda"
)
public class SolicitudController {
    
    private final  SolicitudService service;


    public SolicitudController(SolicitudService service) {
        this.service = service;

    }

    @Operation(
    summary = "Lsita todas las solicitudes ",
    description = "Retorna todas las solicitudes existentes " )

    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
    @ApiResponse(responseCode = "404", description = "lista vacia es decir ninguna solicitd existente")
})
    @GetMapping("/listarSolicitudes")
    public List<DtoSolicitude> obtenerTodas() {
        return service.listar();
    }

    @Operation(
    summary = "Obtener Lista de solicitudes  por rut de Empresa Mandante",
    description = "Retorna una lista con todos las solicitudes creadas para La Empresa mandante")
    
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
    @ApiResponse(responseCode = "404", description = "lsita vacia empresa mandante sin solicituides")
})
    @GetMapping("/Empresamandnate")
    public ResponseEntity<List<DtoSolicitude>> obtenerPorRutmandante(@RequestParam(name = "rut") String  rut) {
        return new ResponseEntity<List<DtoSolicitude>>(service.listarRutMandante(rut), HttpStatus.OK);}

    
    @Operation(
    summary = "Obtener Lista de solicitudes  por rut de Empresa Provedora",
    description = "Retorna una lista con todos las solicitudes creadas para La Empresa provedora")
    
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
    @ApiResponse(responseCode = "404", description = "lsita vacia empresa provedroa sin solicitudes ")
})

    @GetMapping("/EmpresaProvedora")
    public ResponseEntity<List<DtoSolicitude>> obtenerPorRutmpProvera(@RequestParam(name = "rut") String  rut) {
        return new ResponseEntity<List<DtoSolicitude>>(service.listarRutProvedora(rut), HttpStatus.OK);}
    // operacion 
    @Operation(
    summary = "Crea una  solicitud  por la  Empresa mandante",
    description = "Se usa para crear una solicitud para que la empresa provedora la consuma")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
    @ApiResponse(responseCode = "400", description = "error en uno o mas puntos del json ")
    })
    //post
    @PostMapping("/crearSolicitud")
    public ResponseEntity<DtoSolicitude> guardarSolicitud(
        @Valid
        @RequestBody (
        description = "Datos de la solicitud a crear",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = DtoSolicitude.class),
            examples = @ExampleObject(
                name = "Solicitud Completa",
                summary = "Ejemplo de una solicitud válida para dron",
                value = """
                {
                    "id": 1,
                    "descripcion": "Inspección de paneles solares en planta norte",
                    "peso": 2.5,
                    "ubicacion": "Parque Industrial, Calle Norte 123",
                    "atendiada": false,
                    "tipo": "Mantenimiento Preventivo",
                    "rutEmpresaMandante": "12.345.678-9",
                    "rutEmpresaProveedora": "98.765.432-1",
                    "region": "REGIÓN DE ARICA Y PARINACOTA"
                }
            """)
        )) @org.springframework.web.bind.annotation.RequestBody DtoSolicitude dto) {
        return new ResponseEntity<DtoSolicitude>(service.crearSolicitud(dto),HttpStatus.OK);
    }

    @Operation(
    summary = "Actualiza una solicitud  por id ",
    description = "Actualiza una solicitud mediante las id, tanto para la empresa mandante como para la provedora ")

    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
    @ApiResponse(responseCode = "400", description = "error en uno o mas puntos del json "),
    @ApiResponse(responseCode = "404", description = "ip inexistente")
    })
    
    @PutMapping("/updateSolicitud")
    public ResponseEntity<DtoSolicitude> actualizarSolicitud(@RequestParam(name= "id") Long id, @Valid 
    @RequestBody(
        description = "Datos de la solicitud a Actualizar",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = DtoSolicitude.class),
            examples = @ExampleObject(
                name = "Solicitud Completa",
                summary = "Ejemplo de una solicitud válida para dron",
                value = """
                {
                    "id": 1,
                    "descripcion": "Inspección de paneles solares en planta norte",
                    "peso": 2.5,
                    "ubicacion": "Parque Industrial, Calle Norte 123",
                    "atendiada": false,
                    "tipo": "Mantenimiento Preventivo",
                    "rutEmpresaMandante": "12.345.678-9",
                    "rutEmpresaProveedora": "98.765.432-1",
                    "region": "REGIÓN DE ARICA Y PARINACOTA"
                }
            """)
        )) @org.springframework.web.bind.annotation.RequestBody 
    DtoSolicitude dto) {
        if (dto.Id() != id){
            throw new ResourceError("la id de del Json deve ser igual al que buscas en la url");
        }
        return new ResponseEntity<DtoSolicitude>(service.modificarSolicitud(dto),HttpStatus.OK);
    }
    @Operation(
    summary = "elimina  una solicitud  por id ",
    description = "elimina  una  solicitudes mediante las id, solo los administradoes puenden acceder a esta funcion. ")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
    @ApiResponse(responseCode = "404", description = "ip inexistente")})
    @DeleteMapping("/EliminarSolicitudes")
    public ResponseEntity<String> eliminarSolicitud(@RequestParam(name ="id") Long id) {
        return new ResponseEntity< String >(service.eliminar(id),HttpStatus.OK);
    }
}
