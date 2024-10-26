package com.beerrate.mapper;

import com.beerrate.DTO.BeerDTO;
import com.beerrate.entity.Beer;
import org.springframework.stereotype.Component;

@Component
public class BeerMapper {

    public  BeerDTO toDTO(Beer beer) {
        return BeerDTO
                .builder()
                .id(beer.getId())
                .name(beer.getName())
                .rating(beer.getRating())
                .build();
    }

    public  Beer toEntity(BeerDTO beerDTO) {
        return Beer
                .builder()
                .name(beerDTO.getName())
                .rating(beerDTO.getRating())
                .build();
    }

}
