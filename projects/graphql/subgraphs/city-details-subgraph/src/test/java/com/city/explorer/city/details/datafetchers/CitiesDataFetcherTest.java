package com.city.explorer.city.details.datafetchers;

import com.city.explorer.city.details.services.CityDetailsServiceImpl;
import com.city.explorer.city.details.services.ListCitiesServiceImpl;
import com.city.explorer.geo.db.adapter.cities.list.City;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static graphql.Assert.assertNotEmpty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DgsAutoConfiguration.class, CitiesDataFetcher.class})
class CitiesDataFetcherTest {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;
    @MockBean
    ListCitiesServiceImpl listCitiesService;
    @MockBean
    CityDetailsServiceImpl cityDetailsService;

    @Test
    void cities() {
        when(listCitiesService.findCities(anyString())).thenReturn(
                List.of(
                        City.newBuilder()
                                .setId("1")
                                .setName("Dnipro")
                                .build(),
                        City.newBuilder()
                                .setId("2")
                                .setName("Dniprorudne")
                                .build()
                )
        );

        List<String> cityNames = dgsQueryExecutor.executeAndExtractJsonPath(
                "{ cities(namePrefix: \"dnipro\") { id name }}",
                "data.cities[*].name");

        assertNotNull(cityNames);
        assertNotEmpty(cityNames);
        assertThat(cityNames).allMatch(name -> name.toLowerCase().startsWith("dnipro"));
    }

}
