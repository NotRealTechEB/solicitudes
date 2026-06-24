package cl.example.solicitudes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.example.solicitudes.modelo.Solicitud;
import java.util.List;


public interface SolicitudRepositorio extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByRutEmpresaMandante(String rutEmpresaMandante);
    List<Solicitud> findByRutEmpresaProveedora(String rutProvedora);
}