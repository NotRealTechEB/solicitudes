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
        List<DtoSolicitude> lista = Mapper.parseodeLista(repo.findAll());
        if (lista.isEmpty()){
        throw new RuntimeException("no existen solicitudes");
    }
        return lista;
    }

    public List<DtoSolicitude> listarRutMandante(String rut){
        List<DtoSolicitude> lista= Mapper.parseodeLista(repo.findByRutEmpresaMandante(rut));
        if(lista .isEmpty()){
            throw new RuntimeException("no existen registros con ese rut");
        };
        return lista;
    }

    public List<DtoSolicitude> listarRutProvedora(String rut){
        List<DtoSolicitude> lista =Mapper.parseodeLista(repo.findByRutEmpresaProveedora(rut));
        if(lista.isEmpty()){
            throw new RuntimeException("no existen registros con ese rut");
        };
        return lista;
    }

    public DtoSolicitude crearSolicitud (DtoSolicitude ex){
        repo.save(Mapper.add(ex));
        return ex;
    }

    public DtoSolicitude modificarSolicitud (DtoSolicitude ex){
        repo.save(Mapper.update(ex.Id(),ex));
        return ex;
    }

    public DtoSolicitude busarId(Long id){
        DtoSolicitude ex= Mapper.DtotoModel(repo.findById(id).orElseThrow(() ->  
        new RuntimeException("la "+id+" no existe")));
        return ex;
    }
    
    public String eliminar (Long id ){
        busarId(id);
        repo.deleteById(id);
        return "la solicitud "+id +" fue eliminada";
    }
}