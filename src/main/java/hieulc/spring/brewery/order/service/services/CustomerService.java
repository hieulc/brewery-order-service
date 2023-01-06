package hieulc.spring.brewery.order.service.services;

import hieulc.spring.brewery.order.service.model.CustomerPagedList;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerPagedList listCustomers(Pageable pageable);

}
