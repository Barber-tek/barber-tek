package multi_tenant_back.tenant_api.PhoneNumbersTenant.Domain.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import multi_tenant_back.tenant_api.Barbershops.Domain.Model.BarberShops;
import multi_tenant_back.tenant_api.PhoneNumbersTenant.Domain.Configuration.PhoneNumberConfiguration;
import multi_tenant_back.tenant_api.PhoneNumbersTenant.Domain.Model.PhoneNumbersModel;
import multi_tenant_back.tenant_api.PhoneNumbersTenant.Domain.Repository.PhoneNumbersRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneNumbersService {
    private final PhoneNumbersRepository phoneNumbersRepository;
    private boolean validLenghtNumber(Long number) {
        return Math.floor(Math.log10(Math.abs(number)) + 1) == PhoneNumberConfiguration.LENGTH_DIGITS;
    }

    @Transactional
    public PhoneNumbersModel createPhoneNumber(Long number, BarberShops barberShops){
        if(!this.validLenghtNumber(number)){
            throw new IllegalArgumentException("Invalid phone number");
        }
        PhoneNumbersModel phoneNumbersModel = new PhoneNumbersModel();
        phoneNumbersModel.setPhoneNumber(number);
        phoneNumbersModel.setBarberShops(barberShops);
        return phoneNumbersRepository.save(phoneNumbersModel);
    }
}