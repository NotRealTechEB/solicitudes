package cl.example.solicitudes.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.example.solicitudes.dto.DtoSolicitude;
import cl.example.solicitudes.mapper.Mapper;
import cl.example.solicitudes.repository.SolicitudRepositorio;

@Service
public class SolicitudService {
    @Autowired
    SolicitudRepositorio repo ;

    public List<DtoSolicitude> listar(){
        if (Mapper.parseodeLista(repo.findAll()).isEmpty()){
            throw new RuntimeException("sin execepcuion");
        }
        return Mapper.parseodeLista(repo.findAll());
    }

    public List<DtoSolicitude> listarRutMandante(String rut){
        if(Mapper.parseodeLista(repo.findByRutEmpresaMandante(rut)).isEmpty()){
            throw new RuntimeException("no existen registros con ese rut");
        };
        return Mapper.parseodeLista(repo.findByRutEmpresaMandante(rut));
    }

    public List<DtoSolicitude> listarRutProvedora(String rut){
        if(Mapper.parseodeLista(repo.findByRutEmpresaProveedora(rut)).isEmpty()){
            throw new RuntimeException("no existen registros con ese rut");
        };
        return Mapper.parseodeLista(repo.findByRutEmpresaProveedora(rut));
    }

    public DtoSolicitude crearSolicitud (DtoSolicitude ex){
        repo.save(Mapper.add(ex));
        return ex;
    }

    public DtoSolicitude modificarSolicitud (DtoSolicitude ex){
        repo.save(Mapper.update(ex.Id(),ex));
        return ex;
    }
}