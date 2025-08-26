package multi_tenant_back.tenant_api.Auth.Infrastructure;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import multi_tenant_back.tenant_api.Auth.Domain.Services.AuthService;
import multi_tenant_back.tenant_api.Common.Utils.response.ApiMessages;
import multi_tenant_back.tenant_api.Common.Utils.response.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${api.enviroment}")
    private String apiEnviroment;

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<String>> logIn(@Valid @RequestBody UserDTO dto, HttpServletResponse response){
        String jwt = authService.login(dto);
        Cookie cookie = new Cookie("access_token",jwt);
        cookie.setHttpOnly(true);   // protege contra JS en navegador
        cookie.setSecure(apiEnviroment.equals("production"));
        cookie.setPath("/");        // válida para toda la app
        cookie.setMaxAge(60 * 60);  // 1 hora

        response.addCookie(cookie);
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
