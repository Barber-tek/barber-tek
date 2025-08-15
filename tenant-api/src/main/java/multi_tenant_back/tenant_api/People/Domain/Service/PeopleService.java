package multi_tenant_back.tenant_api.People.Domain.Service;

import lombok.RequiredArgsConstructor;
import multi_tenant_back.tenant_api.Common.Utils.ExceptionsHandlers.ResourceNotFoundException;
import multi_tenant_back.tenant_api.People.Domain.Model.People;
import multi_tenant_back.tenant_api.People.Domain.Repository.PeopleRepository;
import multi_tenant_back.tenant_api.People.Infrastructure.PeopleDTO;
import multi_tenant_back.tenant_api.Common.Utils.ExceptionsHandlers.ConflictException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Transactional
    public People createPeople(PeopleDTO dto) {
        People person = new People(dto);
        peopleRepository.findFirstByCcOrPhoneNumbers(dto.getCc(),dto.getPhoneNumbers()).ifPresent(existing -> {
            if(existing.getCc().equals(dto.getCc())){
                throw new DataIntegrityViolationException(
                        String.format("La persona con identificación \"%s\" ya existe.", dto.getCc())
                );
            } else if (existing.getPhoneNumbers().equals(dto.getPhoneNumbers())) {
                throw new DataIntegrityViolationException(
                        String.format("La persona con celular \"%s\" ya existe.", dto.getPhoneNumbers())
                );
            }
        });

        return peopleRepository.save(person);
    }

    public List<People> getAllPeople() {
        List<People> people = peopleRepository.findAll();
        if(people.isEmpty()) {
            throw new ResourceNotFoundException("No hay gente registrada");
        }
        return  people;
    }


    public People updatePeople(UUID id, PeopleDTO dto) {
        if(!peopleRepository.existsById(id)){
            throw new ResourceNotFoundException("No existe la persona con id \""+id+"\"");
        }
        People people = new People(dto);
        people.setId(id);
        return peopleRepository.save(people);
    }

    public void deletePeople(UUID id) {
        if(!peopleRepository.existsById(id)){
            throw new ResourceNotFoundException("No existe la persona con id \""+id+"\"");
        }
        peopleRepository.deleteById(id);
    }

    public People getPersonById(UUID id) {
        Optional<People> people = peopleRepository.findById(id);
        if(people.isEmpty()){
            throw new ResourceNotFoundException("No existe la persona con id \""+id+"\"");
        }
        return people.get();
    }
}
