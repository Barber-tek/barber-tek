package multi_tenant_back.tenant_api.Barbershops.Infrastructure;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import multi_tenant_back.tenant_api.CitiesStates.Domain.Model.CitiesModel;

@Getter
@Setter
public class BarberShopsDto {
    @NotBlank(message = "Falto el nombre de la barberia")
    private String name;
    @NotBlank(message = "No se proporciono la dirección de la barberia")
    private String address;
    @NotNull(message = "No fue proporcionado el numero de la barbería")
    private Long phone;
    @NotNull(message = "No se proporciono una ciudad a la que pertenezca la barberia")
    private CitiesModel city;
}
