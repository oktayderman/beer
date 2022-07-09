package springframework.mmscbrewery.web.services;

import brewery.model.CustomerDto;


import java.util.UUID;

public interface CustomerService {

    CustomerDto getCustomerById(UUID customerId);

}
