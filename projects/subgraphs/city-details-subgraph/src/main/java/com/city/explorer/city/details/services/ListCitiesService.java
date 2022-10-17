package com.city.explorer.city.details.services;

import com.city.explorer.geo.db.adapter.cities.list.City;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ListCitiesService {

    List<City> findCities(@NonNull String prefix);

}
