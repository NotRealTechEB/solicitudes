package cl.example.solicitudes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.example.solicitudes.modelo.Solicitud;
import java.util.List;


@Repository
public interface SolicitudRepositorio extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByRutEmpresaMandante(String rutEmpresaMandante);
    List<Solicitud> findByRutEmpresaProveedora(String rutProvedora);
}