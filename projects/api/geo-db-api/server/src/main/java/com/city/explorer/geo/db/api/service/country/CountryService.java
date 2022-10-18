package com.city.explorer.geo.db.api.service.country;

import com.city.explorer.geo.db.api.country.Country;
import com.city.explorer.geo.db.api.country.ListCountriesResponse;
import com.city.explorer.geo.db.api.exception.ResourceNotFoundException;
import com.city.explorer.geo.db.api.mapper.Mapper;
import com.city.explorer.geo.db.api.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country getById(@NonNull String id) {
        return countryRepository.findById(id.toUpperCase())
                .map(Mapper.Country::toProto)
                .orElseThrow(() -> new ResourceNotFoundException("Country with ID %s does not exist!".formatted(id)));
    }

    public ListCountriesResponse list(@NonNull Integer limit, @NonNull Integer offset, String nameFilter) {
        var page = offset / limit;
        var pageable = PageRequest.of(page, limit, Sort.by("name").ascending());
        Page<com.city.explorer.geo.db.api.model.Country> countriesPage = null;
        if (nameFilter == null || nameFilter.isEmpty()) {
            countriesPage = countryRepository.findAll(pageable);
        } else {
            countriesPage = countryRepository.findAllByNameContainingIgnoreCase(nameFilter, pageable);
        }

        var totalItems = countriesPage.getTotalElements();
        var countries = countriesPage.getContent().stream()
                .map(Mapper.Country::toProto)
                .toList();

        return ListCountriesResponse.newBuilder()
                .addAllItems(countries)
                .setTotalItems(totalItems)
                .build();
    }

}
