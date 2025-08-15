package multi_tenant_back.tenant_api.Auth.Infrastructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import multi_tenant_back.tenant_api.Auth.Domain.Services.AuthService;
import multi_tenant_back.tenant_api.Common.Utils.response.ApiMessages;
import multi_tenant_back.tenant_api.Common.Utils.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<String>> logIn(@Valid @RequestBody UserDTO dto){
        authService.login(dto);
        return ResponseEntity
                .ok()
                .body(ApiResponse.success(ApiMessages.OK));
    }
    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<String>> singUp(@Valid @RequestBody UserDTO dto){
        authService.singUp(dto);
        return new ResponseEntity<>(ApiResponse.success(ApiMessages.RESOURCE_CREATED), HttpStatus.CREATED);
    }
}
