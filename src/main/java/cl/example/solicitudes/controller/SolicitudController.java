package cl.example.solicitudes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.example.solicitudes.dto.SolicitudDTO;
import cl.example.solicitudes.modelo.Solicitud;
import cl.example.solicitudes.service.SolicitudService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService service;

    @GetMapping
    public List<Solicitud> obtenerTodas() {
        return service.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> obtenerPorId(@PathVariable Long id) {
        Optional<Solicitud> solicitud = service.obtenerPorId(id);
        return solicitud.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Solicitud guardarSolicitud(@Valid @RequestBody SolicitudDTO dto) {
        return service.guardarSolicitud(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> actualizarSolicitud(@PathVariable Long id, @Valid @RequestBody SolicitudDTO dto) {
        Solicitud actualizada = service.actualizarSolicitud(id, dto);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Long id) {
        service.eliminarSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}
