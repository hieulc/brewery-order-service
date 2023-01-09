package hieulc.spring.brewery.order.service.domain;


import hieulc.spring.brewery.order.service.model.BeerOrderStatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class BeerOrder extends BaseEntity {

    @Builder
    public BeerOrder(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                     String customerRef,
                     Customer customer,
                     Set<BeerOrderLine> beerOrderLines,
                     String orderStatusCallbackUrl,
                     BeerOrderStatusEnum orderStatus) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerRef = customerRef;
        this.customer = customer;
        this.beerOrderLines = beerOrderLines;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
        this.orderStatus = orderStatus;
    }

    private String customerRef;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "beerOrder", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<BeerOrderLine> beerOrderLines;
    private String orderStatusCallbackUrl;
    private BeerOrderStatusEnum orderStatus = BeerOrderStatusEnum.NEW;
}
