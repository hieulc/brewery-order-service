package hieulc.spring.brewery.order.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class BeerOrderPagedList extends PageImpl<BeerOrderDto> implements Serializable {

    static final long serialVersionUID = -4360158894408169103L;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BeerOrderPagedList(@JsonProperty("content") List<BeerOrderDto> content,
                              @JsonProperty("totalElements") long totalElements,
                              @JsonProperty("number") int number,
                              @JsonProperty("size") int size,
                              @JsonProperty("sort") JsonNode sort,
                              @JsonProperty("pageable") JsonNode pageable,
                              @JsonProperty("last") boolean last,
                              @JsonProperty("first") boolean first,
                              @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public BeerOrderPagedList(List<BeerOrderDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerOrderPagedList(List<BeerOrderDto> content) {
        super(content);
    }
}