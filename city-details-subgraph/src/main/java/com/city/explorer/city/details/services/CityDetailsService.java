package com.city.explorer.city.details.services;

import com.city.explorer.city.details.generated.types.CityDetails;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CityDetailsService {

    List<CityDetails> findCities(@NonNull String prefix);

}
