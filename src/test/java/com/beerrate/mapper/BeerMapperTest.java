package com.beerrate.mapper;

import com.beerrate.DTO.BeerDTO;
import com.beerrate.entity.Beer;
import com.beerrate.utils.Rating;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BeerMapperTest {

    private final BeerMapper beerMapper = new BeerMapper();

    @Test
    void correctBeerEntity_convertsToDTO_successfully() {
        final Beer beer = Beer.builder()
                .id(1)
                .name("Lager")
                .rating(Rating.POOR)
                .build();

        // Act
        BeerDTO beerDTO = beerMapper.toDTO(beer);

        // Assert
        assertNotNull(beerDTO);
        assertEquals(1, beerDTO.getId());
        assertEquals("Lager", beerDTO.getName());
        assertEquals(Rating.POOR, beerDTO.getRating());

    }

    @Test
    void correctBeerDTO_convertsToEntity_successfully() {
        final BeerDTO beerDTO = BeerDTO.builder()
                .id(1)
                .name("Lager")
                .rating(Rating.EXCELLENT)
                .build();
        final Beer beer = beerMapper.toEntity(beerDTO);

        assertNotNull(beer);
        assertEquals("Lager",beer.getName());
        assertEquals(Rating.EXCELLENT,beer.getRating());
    }
}