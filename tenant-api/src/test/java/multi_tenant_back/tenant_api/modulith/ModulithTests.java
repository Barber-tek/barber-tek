package multi_tenant_back.tenant_api.modulith;

import multi_tenant_back.tenant_api.TenantApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.ApplicationModule;
import org.springframework.modulith.core.ApplicationModules;

public class ModulithTests {
    @Test
    void applicationModuleStructureIsValid() {
        ApplicationModules modules = ApplicationModules.of(TenantApiApplication.class);
        modules.forEach(System.out::println);
        modules.verify();
    }
}
