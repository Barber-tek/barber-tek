package multi_tenant_back.tenant_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulith;
import org.springframework.modulith.Modulithic;

@Modulith(
		systemName = "multi-tenant-back"
)
@SpringBootApplication
public class TenantApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantApiApplication.class, args);
	}

}
