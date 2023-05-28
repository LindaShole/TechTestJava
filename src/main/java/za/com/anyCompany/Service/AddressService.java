package za.com.anyCompany.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.com.anyCompany.Repo.AddressRepo;
import za.com.anyCompany.model.AddressModel;
import za.com.anyCompany.model.CustomerModel;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepo addressRepo;
    public AddressModel findShippingAddress(CustomerModel model) {
        return addressRepo.findByCustomerId(model.getId());
    }
}
