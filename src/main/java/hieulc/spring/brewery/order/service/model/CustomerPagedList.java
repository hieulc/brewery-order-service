package hieulc.spring.brewery.order.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CustomerPagedList extends PageImpl<CustomerDto> implements Serializable {

    static long serialVersionUID = 5449412354218071447L;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CustomerPagedList(@JsonProperty("content") List<CustomerDto> content,
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

    public CustomerPagedList(List<CustomerDto> content) {
        super(content);
    }


    public CustomerPagedList(List<CustomerDto> content, Pageable pageable, long totalElements) {
        super(content, pageable, totalElements);
    }
}
