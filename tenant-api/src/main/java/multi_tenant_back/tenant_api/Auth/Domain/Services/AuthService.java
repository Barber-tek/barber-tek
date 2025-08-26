package multi_tenant_back.tenant_api.Auth.Domain.Services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import multi_tenant_back.tenant_api.Auth.Domain.Model.UserModel;
import multi_tenant_back.tenant_api.Auth.Domain.Repository.UsersRepository;
import multi_tenant_back.tenant_api.Auth.Infrastructure.UserDTO;
import multi_tenant_back.tenant_api.Common.Security.Components.JwtUtil;
import multi_tenant_back.tenant_api.Common.Utils.ExceptionsHandlers.ConflictException;
import multi_tenant_back.tenant_api.Common.Utils.ExceptionsHandlers.ResourceNotFoundException;
import multi_tenant_back.tenant_api.Common.Utils.ExceptionsHandlers.UnauthorizedException;
import multi_tenant_back.tenant_api.People.Domain.Model.People;
import multi_tenant_back.tenant_api.People.Domain.Service.PeopleService;
import multi_tenant_back.tenant_api.Roles.Domain.Model.RolesModel;
import multi_tenant_back.tenant_api.Roles.Domain.Service.RolesService;
import multi_tenant_back.tenant_api.Roles.infrastructure.RolesEnum;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final PeopleService peopleService;
    private final JwtUtil jwtUtil;
    private final RolesService rolesService;

    public String login(@Valid UserDTO dto) {
        UserModel user = usersRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with email address: " + dto.getEmail()
                ));
        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new UnauthorizedException("Wrong Password");
        }
        String userFullName = user.getPeople().getFirstName() + " " + user.getPeople().getLastName();
        return jwtUtil.generateToken(user.getEmail(), user.getRole().getName(), userFullName, user.getId());
    }

    public void singUp(@Valid UserDTO dto) {

        if(usersRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ConflictException("User already exists");
        }
        People people = peopleService.getPersonById(dto.getPersonId());
        RolesEnum rolEnum = RolesEnum.ADMIN;
        RolesModel rol = rolesService.findByName(rolEnum.name());
        UserModel userBd = new UserModel(dto,people,rol);
        String passwdCrypt = passwordEncoder.encode(dto.getPassword());
        userBd.setPassword(passwdCrypt);
        usersRepository.save(userBd);
    }
}