package br.com.veiculo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CarroVendidoExceptionHandler {

    @ExceptionHandler(CarroVendidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(CarroVendidoException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorCode", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorMap.put("message", ex.getMessage());
        return errorMap;
    }
}
