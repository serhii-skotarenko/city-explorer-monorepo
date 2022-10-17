package com.city.explorer.city.details.services;

import com.city.explorer.geo.db.adapter.cities.details.CityDetails;
import org.springframework.lang.NonNull;

public interface CityDetailsService {

    CityDetails getForCity(@NonNull String cityId);

}
