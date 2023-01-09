package hieulc.spring.brewery.order.service.web.mapper;

import hieulc.spring.brewery.order.service.domain.BeerOrderLine;
import hieulc.spring.brewery.order.service.model.BeerOrderLineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {

    BeerOrderLine beerOrderLineDtoToBeerOrderLine(BeerOrderLineDto beerOrderLineDto);

    BeerOrderLineDto beerOrderLineToBeerOrderLineDto(BeerOrderLine beerOrderLine);
}
