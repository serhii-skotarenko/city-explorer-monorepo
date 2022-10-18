package com.city.explorer.city.details.datafetchers;

import com.city.explorer.city.details.services.CityDetailsService;
import com.city.explorer.city.details.services.ListCitiesService;
import com.city.explorer.geo.db.adapter.cities.details.CityDetails;
import com.city.explorer.geo.db.adapter.cities.list.City;
import com.netflix.graphql.dgs.*;

import java.util.List;

@DgsComponent
public class CitiesDataFetcher {
    private final ListCitiesService listCitiesService;
    private final CityDetailsService cityDetailsService;

    public CitiesDataFetcher(ListCitiesService listCitiesService, CityDetailsService cityDetailsService) {
        this.listCitiesService = listCitiesService;
        this.cityDetailsService = cityDetailsService;
    }

    @DgsQuery
    public List<City> cities(@InputArgument String namePrefix) {
        return listCitiesService.findCities(namePrefix);
    }

    @DgsData(parentType = "City", field = "details")
    public CityDetails cityDetails(DgsDataFetchingEnvironment dfe) {
        City city = dfe.getSource();
        return cityDetailsService.getForCity(city.getId());
    }
}
