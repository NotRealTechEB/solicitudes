package cl.example.solicitudes.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import cl.example.solicitudes.dto.DtoSolicitude;
import cl.example.solicitudes.excepcion.ResourceError;
import cl.example.solicitudes.mapper.Mapper;
import cl.example.solicitudes.modelo.Solicitud;
import cl.example.solicitudes.repository.SolicitudRepositorio;

@Service
public class SolicitudService {
    
    private SolicitudRepositorio repo ;
    public SolicitudService (SolicitudRepositorio repo){
        this.repo = repo;
    }

    private final ArrayList<String>  regiones = new ArrayList<>(Arrays.asList(
            "REGIÓN DE ARICA Y PARINACOTA",
            "REGIÓN DE TARAPACÁ",
            "REGIÓN DE ANTOFAGASTA",
            "REGIÓN DE ATACAMA",
            "REGIÓN DE COQUIMBO",
            "REGIÓN DE VALPARAÍSO",
            "REGIÓN METROPOLITANA DE SANTIAGO",
            "REGIÓN DEL LIBERTADOR GENERAL BERNARDO O'HIGGINS",
            "REGIÓN DEL MAULE",
            "REGIÓN DE ÑUBLE",
            "REGIÓN DEL BÍO BÍO",
            "REGIÓN DE LA ARAUCANÍA",
            "REGIÓN DE LOS RÍOS",
            "REGIÓN DE LOS LAGOS",
            "REGIÓN DE AYSÉN DEL GENERAL CARLOS IBÁÑEZ DEL CAMPO",
            "REGIÓN DE MAGALLANES Y DE LA ANTÁRTICA CHILENA"
        ));
    

    public List<DtoSolicitude> listar(){
        List<DtoSolicitude> lista = Mapper.parseodeLista(repo.findAll());
        if (lista.isEmpty()){
        throw new ResourceError("no existen solicitudes");
    }
        return lista;
    }

    public List<DtoSolicitude> listarRutMandante(String rut){
        List<DtoSolicitude> lista= Mapper.parseodeLista(repo.findByRutEmpresaMandante(rut));
        if(lista .isEmpty()){
            throw new ResourceError("no existen registros con ese rut");
        };
        return lista;
    }

    public List<DtoSolicitude> listarRutProvedora(String rut){
        List<DtoSolicitude> lista =Mapper.parseodeLista(repo.findByRutEmpresaProveedora(rut));
        if(lista.isEmpty()){
            throw new ResourceError("no existen registros con ese rut");
        };
        return lista;
    }

    public DtoSolicitude crearSolicitud (DtoSolicitude ex){
        Solicitud modelo = Mapper.ModelToDto(ex);
        for (String i : regiones) {
            System.out.println(i);
            System.out.println(modelo.getRegion());
            if (i.equals(modelo.getRegion())){
                repo.save(modelo);
                return Mapper.DtotoModel(modelo);
            }
        }
        throw new ResourceError("no existe la region ingresada, el formato es el siguiente REGIÓN DE ARICA Y PARINACOTA");
    }

    public DtoSolicitude modificarSolicitud (DtoSolicitude ex){
        repo.save(Mapper.update(ex.Id(),ex));
        return ex;
    }

    public DtoSolicitude busarId(Long id){
        DtoSolicitude ex= Mapper.DtotoModel(repo.findById(id).orElseThrow(() ->  
        new ResourceError("la "+id+" no existe")));
        return ex;
    }
    
    public String eliminar (Long id ){
        busarId(id);
        repo.deleteById(id);
        return "la solicitud "+id +" fue eliminada";
    }
}