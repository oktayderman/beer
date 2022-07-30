package springframework.msscbreweryclient.web.client;

import brewery.model.CustomerDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springframework.msscbreweryclient.web.model.BeerDto;
import springframework.msscbreweryclient.web.model.BeerStyleEnum;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
class BreweryClientTest {

    @Autowired
    BreweryClient client;


    @Test
    void getBeerById() {
        BeerDto requestSaveBeer  = BeerDto.builder()
                .beerStyle(BeerStyleEnum.ALE)
                .beerName("Peroni")
                .price(new BigDecimal("1.23"))
                .upc("2152151521")
                .build();
        BeerDto responseBeerSaved  = client.saveNewBeer(requestSaveBeer);
        BeerDto responseGetBeer = client.getBeerById(responseBeerSaved.getId());

        assertEquals(requestSaveBeer.getBeerName(), responseGetBeer.getBeerName());
    }

    @Test
    void saveNewBeer() {
        BeerDto request = BeerDto.builder()
                .beerStyle(BeerStyleEnum.ALE)
                .beerName("Peroni")
                .price(new BigDecimal("1.23"))
                .upc("13241214")
                .build();

        BeerDto response = client.saveNewBeer(request);
        assertEquals(request.getBeerName(), response.getBeerName());
    }
    

    @Test
    void updateBeer() {
        String newName = "Ichnusa";

        BeerDto requestSaveBeer  = BeerDto.builder()
                .beerStyle(BeerStyleEnum.ALE)
                .beerName("Peroni")
                .price(new BigDecimal("1.23"))
                .upc("52352352")
                .build();
        BeerDto responseBeerSaved  = client.saveNewBeer(requestSaveBeer);

        responseBeerSaved.setBeerName(newName);

        client.updateBeer(responseBeerSaved.getId(), responseBeerSaved);

        BeerDto responseGetBeer = client.getBeerById(responseBeerSaved.getId());

        assertEquals(responseGetBeer.getBeerName(), newName);
    }
    @Test
    void getCustomerById() {
        CustomerDto dto = client.getCustomerById(UUID.randomUUID());

        assertNotNull(dto);

    }

    @Test
    void testSaveNewCustomer() {
        //given
        CustomerDto customerDto = CustomerDto.builder().name("Joe").build();

        URI uri = client.saveNewCustomer(customerDto);

        assertNotNull(uri);

        System.out.println(uri.toString());

    }

    @Test
    void testUpdateCustomer() {
        //given
        CustomerDto customerDto = CustomerDto.builder().name("Jim").build();

        client.updateCustomer(UUID.randomUUID(), customerDto);

    }

    @Test
    void testDeleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }
}