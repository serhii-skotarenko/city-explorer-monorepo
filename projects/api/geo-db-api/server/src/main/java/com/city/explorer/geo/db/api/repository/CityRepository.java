package com.city.explorer.geo.db.api.repository;

import com.city.explorer.geo.db.api.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {
}
