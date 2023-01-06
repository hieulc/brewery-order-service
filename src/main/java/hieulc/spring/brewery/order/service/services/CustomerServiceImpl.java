package hieulc.spring.brewery.order.service.services;

import hieulc.spring.brewery.order.service.domain.Customer;
import hieulc.spring.brewery.order.service.model.CustomerDto;
import hieulc.spring.brewery.order.service.model.CustomerPagedList;
import hieulc.spring.brewery.order.service.repositories.CustomerRepository;
import hieulc.spring.brewery.order.service.web.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public CustomerPagedList listCustomers(Pageable pageable) {

        CustomerPagedList customerPagedList;
        Page<Customer> customerPage = customerRepository.findAll(pageable);

        customerPagedList = new CustomerPagedList(
                getListCustomerDto(customerPage),
                getPageRequest(customerPage),
                getTotalElements(customerPage)
        );
        return customerPagedList;
    }

    private PageRequest getPageRequest(Page<Customer> customerPage) {
        return PageRequest.of(customerPage.getPageable().getPageNumber(),
                customerPage.getPageable().getPageSize());
    }

    private List<CustomerDto> getListCustomerDto(Page<Customer> customerPage) {
        return customerPage.getContent()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    private long getTotalElements(Page<Customer> customerPage) {
        return customerPage.getTotalElements();
    }
}
