package multi_tenant_back.tenant_api;

import multi_tenant_back.tenant_api.Common.Utils.response.ApiMessages;
import multi_tenant_back.tenant_api.Common.Utils.response.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.modulith.Modulith;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Modulith(
		systemName = "multi-tenant-back"
)
@SpringBootApplication
@RestController
public class TenantApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantApiApplication.class, args);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<String>> getHelloConection() {
		return ResponseEntity.ok().body(ApiResponse.success(ApiMessages.SERVER_ALIVE));
	}

}
