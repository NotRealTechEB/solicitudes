package cl.example.solicitudes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.example.solicitudes.dto.SolicitudDTO;
import cl.example.solicitudes.modelo.Solicitud;
import cl.example.solicitudes.repository.SolicitudRepositorio;

@Service
public class SolicitudService {
    @Autowired
    private SolicitudRepositorio repositorio;

    public List<Solicitud> obtenerTodas() {
        return repositorio.findAll();
    }

    public Optional<Solicitud> obtenerPorId(Long id) {
        return repositorio.findById(id);
    }

    public Solicitud guardarSolicitud(SolicitudDTO dto) {
        Solicitud solicitud = new Solicitud();
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setPeso(dto.getPeso());
        solicitud.setUbicacion(dto.getUbicacion());
        solicitud.setAtendida(dto.getAtendida());
        solicitud.setTipo(dto.getTipo());
        solicitud.setIdEmpresaMandante(dto.getIdEmpresaMandante());
        solicitud.setIdEmpresaProveedora(dto.getIdEmpresaProveedora());
        
        return repositorio.save(solicitud);
    }

    public Solicitud actualizarSolicitud(Long id, SolicitudDTO dto) {
        Optional<Solicitud> existente = repositorio.findById(id);
        if (existente.isPresent()) {
            Solicitud solicitud = existente.get();
            solicitud.setDescripcion(dto.getDescripcion());
            solicitud.setPeso(dto.getPeso());
            solicitud.setUbicacion(dto.getUbicacion());
            solicitud.setAtendida(dto.getAtendida());
            solicitud.setTipo(dto.getTipo());
            solicitud.setIdEmpresaMandante(dto.getIdEmpresaMandante());
            solicitud.setIdEmpresaProveedora(dto.getIdEmpresaProveedora());
            
            return repositorio.save(solicitud);
        }
        return null;
    }

    public void eliminarSolicitud(Long id) {
        repositorio.deleteById(id);
    }
}