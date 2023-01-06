package hieulc.spring.brewery.order.service.repositories;

import hieulc.spring.brewery.order.service.domain.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {
}
