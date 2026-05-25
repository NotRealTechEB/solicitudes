package cl.example.solicitudes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.example.solicitudes.modelo.Solicitud;

@Repository
public interface SolicitudRepositorio extends JpaRepository<Solicitud, Long> {
}