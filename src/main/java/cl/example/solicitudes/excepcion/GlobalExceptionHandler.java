package cl.example.solicitudes.excepcion;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cl.example.solicitudes.dto.DtoExeption;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<DtoExeption> buildResponse(
        HttpStatus status,String mnesaje,
        String ruta,Map<String, String> detalles){
        DtoExeption dto = new DtoExeption(
            LocalDateTime.now(),status.value(),
            status.getReasonPhrase(),mnesaje,
            detalles,ruta);
            return ResponseEntity.status(status).body(dto);
        };

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DtoExeption> manejarValidaciones(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return buildResponse(HttpStatus.BAD_REQUEST, "errores en los argumentos",
        request.getRequestURI(), errores); 
    }
    @ExceptionHandler(ResourceError.class)
    public ResponseEntity<DtoExeption> resourceNotFound (ResourceError ex, HttpServletRequest request){
        System.out.println("¡ENTRÓ EN MI MANEJADOR DE ERRORES!");
        System.out.println(ex.getMessage());
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(),
        request.getRequestURI(), null);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DtoExeption> manejarErroresDeNegocio(RuntimeException ex, HttpServletRequest request) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,
        "error interno en el servidor ", request.getRequestURI(), null);
    }
}