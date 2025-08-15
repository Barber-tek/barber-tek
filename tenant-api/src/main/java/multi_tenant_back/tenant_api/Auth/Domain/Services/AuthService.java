package multi_tenant_back.tenant_api.Auth.Domain.Services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import multi_tenant_back.tenant_api.Auth.Domain.Model.UserModel;
import multi_tenant_back.tenant_api.Auth.Domain.Repository.UsersRepository;
import multi_tenant_back.tenant_api.Auth.Infrastructure.UserDTO;
import multi_tenant_back.tenant_api.Common.Utils.ExceptionsHandlers.ConflictException;
import multi_tenant_back.tenant_api.Common.Utils.ExceptionsHandlers.ResourceNotFoundException;
import multi_tenant_back.tenant_api.Common.Utils.ExceptionsHandlers.UnauthorizedException;
import multi_tenant_back.tenant_api.People.Domain.Model.People;
import multi_tenant_back.tenant_api.People.Domain.Service.PeopleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final PeopleService peopleService;

    public void login(@Valid UserDTO dto) {
        UserModel user = usersRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with email address: " + dto.getEmail()
                ));
        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new UnauthorizedException("Wrong Password");
        }
    }

    public void singUp(@Valid UserDTO dto) {

        if(usersRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ConflictException("User already exists");
        }
        People people = peopleService.getPersonById(dto.getPersonId());
        UserModel userBd = new UserModel(dto,people);
        String passwdCrypt = passwordEncoder.encode(dto.getPassword());
        userBd.setPassword(passwdCrypt);
        usersRepository.save(userBd);
    }
}