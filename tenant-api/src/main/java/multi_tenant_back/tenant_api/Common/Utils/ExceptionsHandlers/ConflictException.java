package multi_tenant_back.tenant_api.Common.Utils.ExceptionsHandlers;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
