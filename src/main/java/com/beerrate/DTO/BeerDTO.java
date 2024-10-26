package com.beerrate.DTO;

import com.beerrate.utils.Rating;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class BeerDTO implements Serializable {
    private Integer id;
    private String name;
    private Rating rating;
}
