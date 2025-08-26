package multi_tenant_back.tenant_api.Common.Utils.response;

import lombok.Getter;

@Getter
public enum ApiMessages {
    OK("Operacion exitosa"),
    RESOURCE_FOUND("Recurso encontrado"),
    RESOURCE_NOT_FOUND("Recurso no encontrado"),
    RESOURCE_CREATED("Recurso creado exitosamente"),
    RESOURCE_UPDATED("Recurso actualizado"),
    RESOURCE_DELETED("Recurso eliminado"),
    BAD_REQUEST("Petición inválida"),
    INTERNAL_ERROR("Error interno del servidor"),
    VALIDATION_ERROR("Error de validación"),
    CONFLICT("Recurso duplicado"),
    UNAUTHORIZED("Peticion no autorizada"),
    SERVER_ALIVE("Servidor vivo y corriendo");

    private final String message;

    ApiMessages(String message) {
        this.message = message;
    }

}
