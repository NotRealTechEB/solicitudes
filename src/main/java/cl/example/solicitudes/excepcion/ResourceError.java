package cl.example.solicitudes.excepcion;

public class ResourceError extends RuntimeException {
    public ResourceError (String mensaje){
        super(mensaje);
    }

}
