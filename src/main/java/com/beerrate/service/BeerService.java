package com.beerrate.service;

import com.beerrate.DTO.BeerDTO;
import com.beerrate.entity.Beer;
import com.beerrate.mapper.BeerMapper;
import com.beerrate.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    // Logger instance
    private static final Logger logger = LoggerFactory.getLogger(BeerService.class);
    @Cacheable(value = "beers", key = "#page + '-' + #size")
    public List<BeerDTO> getBeers(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        logger.info("Fetching beers with page: {}, size: {}", page, size);

        final Page<Beer> beerPage = beerRepository.findAll(pageable);
        List<BeerDTO> beerDTOs = beerPage.stream()
                .map(beerMapper::toDTO)
                .collect(Collectors.toList());

        logger.info("Retrieved {} beers", beerDTOs.size());
        return beerDTOs;
    }
}
