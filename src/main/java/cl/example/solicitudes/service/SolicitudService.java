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

private final ArrayList<String> regiones = new ArrayList<>(Arrays.asList(
    "ARICA Y PARINACOTA",
    "TARAPACÁ",
    "ANTOFAGASTA",
    "ATACAMA",
    "COQUIMBO",
    "VALPARAÍSO",
    "METROPOLITANA DE SANTIAGO",
    "OHIGGINS",
    "MAULE",
    "ÑUBLE",
    "BÍO BÍO",
    "ARAUCANÍA",
    "LOS RÍOS",
    "LOS LAGOS",
    "AYSÉN",
    "MAGALLANES Y ANTÁRTICA CHILENA"
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
        throw new ResourceError("no existe la region ingresada, el formato es el siguiente \"ARICA Y PARINACOTA\",\n" + //
                        "    \"TARAPACÁ\",\n" + //
                        "    \"ANTOFAGASTA\",\n" + //
                        "    \"ATACAMA\",\n" + //
                        "    \"COQUIMBO\",\n" + //
                        "    \"VALPARAÍSO\",\n" + //
                        "    \"METROPOLITANA DE SANTIAGO\",\n" + //
                        "    \"OHIGGINS\",\n" + //
                        "    \"MAULE\",\n" + //
                        "    \"ÑUBLE\",\n" + //
                        "    \"BÍO BÍO\",\n" + //
                        "    \"ARAUCANÍA\",\n" + //
                        "    \"LOS RÍOS\",\n" + //
                        "    \"LOS LAGOS\",\n" + //
                        "    \"AYSÉN\",\n" + //
                        "    \"MAGALLANES Y ANTÁRTICA CHILENA\"");
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
    public String despierta(){
        return "despierto";
    }
}