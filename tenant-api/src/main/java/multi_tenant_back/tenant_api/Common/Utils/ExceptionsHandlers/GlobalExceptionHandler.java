package multi_tenant_back.tenant_api.Common.Utils.ExceptionsHandlers;

import multi_tenant_back.tenant_api.Common.Utils.response.ApiMessages;
import multi_tenant_back.tenant_api.Common.Utils.response.ApiResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 - Validación de datos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        String firstError = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(ApiResponse.warning(ApiMessages.VALIDATION_ERROR, firstError));
    }

    //401 Unhautorized
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<?>> handleUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(ApiMessages.UNAUTHORIZED, ex.getMessage()));
    }

    // 404 - Recurso no encontrado
    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ApiMessages.RESOURCE_NOT_FOUND, ex.getMessage()));
    }

    // 500 - Cualquier otro error no controlado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneric(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.internalServerError()
                .body(ApiResponse.error(ApiMessages.INTERNAL_ERROR, null));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.warning(ApiMessages.CONFLICT, ex.getMessage()));
    }
}
