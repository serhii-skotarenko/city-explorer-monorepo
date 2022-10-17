package com.city.explorer.city.details.datafetchers;

import com.city.explorer.city.details.generated.types.CityDetails;
import com.city.explorer.city.details.services.CityDetailsService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class CityDetailsDataFetcher {
    private final CityDetailsService cityDetailsService;

    public CityDetailsDataFetcher(CityDetailsService cityDetailsService) {
        this.cityDetailsService = cityDetailsService;
    }

    @DgsQuery
    public List<CityDetails> cities(@InputArgument String namePrefix) {
        return cityDetailsService.findCities(namePrefix);
    }
}
