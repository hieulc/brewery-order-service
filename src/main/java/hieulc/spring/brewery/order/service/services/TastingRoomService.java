package hieulc.spring.brewery.order.service.services;

import hieulc.spring.brewery.order.service.bootstrap.BeerOrderBootstrap;
import hieulc.spring.brewery.order.service.domain.BeerOrder;
import hieulc.spring.brewery.order.service.domain.Customer;
import hieulc.spring.brewery.order.service.model.BeerOrderDto;
import hieulc.spring.brewery.order.service.model.BeerOrderLineDto;
import hieulc.spring.brewery.order.service.repositories.BeerOrderRepository;
import hieulc.spring.brewery.order.service.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class TastingRoomService {

    private final CustomerRepository customerRepository;
    private final BeerOrderService beerOrderService;
    private final BeerOrderRepository beerOrderRepository;
    private final List<String> beerUpcs = new ArrayList<>(3);

    public TastingRoomService(CustomerRepository customerRepository, BeerOrderService beerOrderService, BeerOrderRepository beerOrderRepository) {
        this.customerRepository = customerRepository;
        this.beerOrderService = beerOrderService;
        this.beerOrderRepository = beerOrderRepository;

        beerUpcs.add(BeerOrderBootstrap.BEER_1_UPC);
        beerUpcs.add(BeerOrderBootstrap.BEER_2_UPC);
        beerUpcs.add(BeerOrderBootstrap.BEER_3_UPC);
    }


    @Transactional
    @Scheduled(fixedRate = 2000)
    public void placeTastingRoomOrder() {
        List<Customer> customerList = customerRepository.findAllByCustomerNameLike(BeerOrderBootstrap.TASTING_ROOM);

        if (customerList.size() == 1) {
            doPlaceOrder(customerList.get(0));
        } else {
            log.error("Too many or too few tasting room customers found");
        }
    }

    private void doPlaceOrder(Customer customer) {
        String beerToOrder = getRandomBeerUpc();

        BeerOrderLineDto beerOrderLineDto = BeerOrderLineDto.builder()
                .upc(beerToOrder)
                .orderQuantity(new Random().nextInt(6))
                .build();

        List<BeerOrderLineDto> beerOrderLineSet = new ArrayList<>();
        beerOrderLineSet.add(beerOrderLineDto);

        BeerOrderDto beerOrder = BeerOrderDto.builder()
                .customerId(customer.getId())
                .customerRef(UUID.randomUUID().toString())
                .beerOrderLines(beerOrderLineSet)
                .build();

        BeerOrderDto savedOrder = beerOrderService.placeOrder(customer.getId(), beerOrder);

    }

    private String getRandomBeerUpc() {
        return beerUpcs.get(new Random().nextInt(beerUpcs.size() - 0));
    }
}
