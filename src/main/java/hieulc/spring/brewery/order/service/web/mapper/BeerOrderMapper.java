package hieulc.spring.brewery.order.service.web.mapper;

import hieulc.spring.brewery.order.service.domain.BeerOrder;
import hieulc.spring.brewery.order.service.model.BeerOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {

    @Mapping(target = "customerId", source = "customer.id")
    BeerOrderDto beerOrderToBeerOrderDto(BeerOrder beerOrder);

    BeerOrder beerOrderDtoToBeerOrder(BeerOrderDto beerOrderDto);

}
