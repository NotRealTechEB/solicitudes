package cl.example.solicitudes.mapper;

import java.util.ArrayList;
import java.util.List;

import cl.example.solicitudes.dto.DtoSolicitude;
import cl.example.solicitudes.modelo.Solicitud;

public class Mapper {
    public static Solicitud add ( DtoSolicitude ex){
        Solicitud model = ModelToDto(ex);
        model.setIdSolicitud(null);
        return model;
    }
    public static Solicitud update(Long id, DtoSolicitude ex){
        Solicitud model = ModelToDto(ex);
        model.setIdSolicitud(id );
        return model;
    }
    public static Solicitud ModelToDto (DtoSolicitude Dto){
        Solicitud model = new Solicitud(Dto.Id(),Dto.descripcion(),Dto.peso(),
        Dto.ubicacion(),Dto.atendiada(),Dto.tipo(),Dto.rutEmpresaMandante(),Dto.rutEmpresaProveedora());
    return model;}
    
    public static DtoSolicitude DtotoModel(Solicitud model){
        DtoSolicitude dto= new DtoSolicitude(model.getIdSolicitud(),
        model.getDescripcion(),model.getPeso(),
        model.getUbicacion(),model.getAtendida()
        ,model.getTipo(),model.getRutEmpresaMandante()
        ,model.getRutEmpresaProveedora());
        return dto;
    }

    public static List<DtoSolicitude> parseodeLista(List<Solicitud> oldList){
        List<DtoSolicitude> newList= new ArrayList<>();
        for (Solicitud i : oldList) {
            newList.add(DtotoModel(i));
        }
        return newList;
    }

    public static List<Solicitud> parseodeLista2(List<DtoSolicitude> oldList){
        List<Solicitud> newList= new ArrayList<>();
        for (DtoSolicitude i : oldList) {
            newList.add(ModelToDto(i));
        }
        return newList;
    }
}
