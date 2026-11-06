package pe.tecsup.mantenimiento.cliente.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> datosInvalidos(MethodArgumentNotValidException exception) {
        Map<String, String> campos = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors()
            .forEach(error -> campos.putIfAbsent(error.getField(), error.getDefaultMessage()));
        return error(HttpStatus.BAD_REQUEST, "Revisa los campos indicados.", campos);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> jsonInvalido(HttpMessageNotReadableException exception) {
        return error(HttpStatus.BAD_REQUEST, "Revisa la estructura del JSON y el tipo de dato de cada campo.", Map.of());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> idInvalido(MethodArgumentTypeMismatchException exception) {
        return error(HttpStatus.BAD_REQUEST, "El ID debe ser un número entero.", Map.of());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> errorDeServicio(ResponseStatusException exception) {
        return error(exception.getStatusCode(), exception.getReason(), Map.of());
    }

    private ResponseEntity<ApiError> error(HttpStatusCode status, String mensaje, Map<String, String> campos) {
        return ResponseEntity.status(status).body(new ApiError(status.value(), mensaje, campos));
    }

    public record ApiError(int estado, String mensaje, Map<String, String> campos) {
    }
}
