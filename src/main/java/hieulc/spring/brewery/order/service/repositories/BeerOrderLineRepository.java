package hieulc.spring.brewery.order.service.repositories;

import hieulc.spring.brewery.order.service.domain.BeerOrder;
import hieulc.spring.brewery.order.service.domain.BeerOrderLine;
import hieulc.spring.brewery.order.service.model.BeerOrderStatusEnum;
import hieulc.spring.brewery.order.service.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BeerOrderLineRepository extends JpaRepository<BeerOrderLine, UUID> {

    Page<BeerOrder> findAllByCustomer(Customer customer, Pageable pageable);

    List<BeerOrder> findAllByOrderStatus(BeerOrderStatusEnum beerOrderStatusEnum);
}
