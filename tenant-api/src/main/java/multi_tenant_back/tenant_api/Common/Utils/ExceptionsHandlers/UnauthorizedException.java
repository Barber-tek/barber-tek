package multi_tenant_back.tenant_api.Common.Utils.ExceptionsHandlers;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
