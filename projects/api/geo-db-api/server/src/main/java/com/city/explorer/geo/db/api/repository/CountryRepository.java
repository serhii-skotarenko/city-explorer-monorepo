package com.city.explorer.geo.db.api.repository;

import com.city.explorer.geo.db.api.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {
}
