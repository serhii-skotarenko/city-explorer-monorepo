package com.city.explorer.geo.db.api.repository;

import com.city.explorer.geo.db.api.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends PagingAndSortingRepository<Country, String> {

    Page<Country> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
