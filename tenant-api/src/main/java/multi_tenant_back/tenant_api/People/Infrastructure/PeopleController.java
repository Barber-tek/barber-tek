package multi_tenant_back.tenant_api.People.Infrastructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import multi_tenant_back.tenant_api.People.Domain.Model.People;
import multi_tenant_back.tenant_api.People.Domain.Service.PeopleService;
import multi_tenant_back.tenant_api.Common.Utils.response.ApiMessages;
import multi_tenant_back.tenant_api.Common.Utils.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {
    private final static String location = "/people/";
    private final PeopleService peopleService;
    @PostMapping
    public ResponseEntity<ApiResponse<People>> createPerson(@Valid @RequestBody PeopleDTO dto){
        People person = peopleService.createPeople(dto);
        return ResponseEntity
                .created(URI.create(location+"1"))
                .body(ApiResponse.success(ApiMessages.RESOURCE_CREATED,person));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<People>>> getPeople(){
        List<People> people = peopleService.getAllPeople();
        return ResponseEntity.ok(ApiResponse.success(ApiMessages.OK,people));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<People>> updatePerson(@PathVariable UUID id, @Valid @RequestBody PeopleDTO dto){
        return ResponseEntity.ok(ApiResponse.success(ApiMessages.RESOURCE_UPDATED,peopleService.updatePeople(id, dto)));
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePerson(@PathVariable UUID id){
        peopleService.deletePeople(id);
        return ResponseEntity.ok(ApiResponse.success(ApiMessages.RESOURCE_DELETED));
     }
}
