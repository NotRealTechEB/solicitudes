package cl.example.solicitudes.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cl.example.solicitudes.dto.DtoSolicitude;
import cl.example.solicitudes.service.SolicitudService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    
    private final  SolicitudService service;

    public SolicitudController(SolicitudService service) {
        this.service = service;
    }

    @GetMapping("/listarSolicitudes")
    public List<DtoSolicitude> obtenerTodas() {
        return service.listar();
    }

    @GetMapping("/Empresamandnate")
    public ResponseEntity<List<DtoSolicitude>> obtenerPorRutmandante(@RequestParam(name = "rut") String  rut) {
        return new ResponseEntity<List<DtoSolicitude>>(service.listarRutMandante(rut), HttpStatus.OK);}

    @GetMapping("/EmpresaProvedora")
    public ResponseEntity<List<DtoSolicitude>> obtenerPorRutmpProvera(@RequestParam(name = "rut") String  rut) {
        return new ResponseEntity<List<DtoSolicitude>>(service.listarRutProvedora(rut), HttpStatus.OK);}
    
    @PostMapping("/crearSolicitud")
    public ResponseEntity<DtoSolicitude> guardarSolicitud(@Valid @RequestBody DtoSolicitude dto) {
        return new ResponseEntity<DtoSolicitude>(service.crearSolicitud(dto),HttpStatus.OK);
    }

    @PutMapping("/updateSolicitud")
    public ResponseEntity<DtoSolicitude> actualizarSolicitud(@RequestParam(name= "id") Long id, @Valid @RequestBody DtoSolicitude dto) {
        return new ResponseEntity<DtoSolicitude>(service.modificarSolicitud(dto),HttpStatus.OK);
    }

    @DeleteMapping("/EliminarSolicitudes")
    public ResponseEntity<String> eliminarSolicitud(@RequestParam(name ="id") Long id) {
        return new ResponseEntity< String >(service.eliminar(id),HttpStatus.OK);
    }
}
