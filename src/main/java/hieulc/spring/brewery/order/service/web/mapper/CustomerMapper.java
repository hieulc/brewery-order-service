package hieulc.spring.brewery.order.service.web.mapper;

import hieulc.spring.brewery.order.service.domain.Customer;
import hieulc.spring.brewery.order.service.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);

}
