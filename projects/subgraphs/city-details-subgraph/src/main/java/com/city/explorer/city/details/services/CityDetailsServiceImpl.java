package com.city.explorer.city.details.services;

import com.city.explorer.city.details.generated.types.CityDetails;
import com.city.explorer.city.details.services.dto.CitiesApiResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class CityDetailsServiceImpl implements CityDetailsService {

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    public CityDetailsServiceImpl(@Value("${geo-db-api.headers.api-key.name}") String apiKeyHeaderName,
                                  @Value("${geo-db-api.headers.api-key.value}") String apiKeyHeaderValue,
                                  @Value("${geo-db-api.headers.api-host.name}") String apiHostHeaderName,
                                  @Value("${geo-db-api.headers.api-host.value}") String apiHostHeaderValue) {
        this.restTemplate = new RestTemplate();

        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.add(apiKeyHeaderName, apiKeyHeaderValue);
        this.httpHeaders.add(apiHostHeaderName, apiHostHeaderValue);
    }

    @Override
    public List<CityDetails> findCities(@NotNull String prefix) {
        var uri = UriComponentsBuilder.fromHttpUrl("https://wft-geo-db.p.rapidapi.com/v1/geo/cities")
                .queryParam("types", "CITY")
                .queryParam("namePrefix", prefix)
                .build()
                .toUri();

        var reqEntity = new RequestEntity<>(httpHeaders, HttpMethod.GET, uri);

        return restTemplate.exchange(reqEntity, CitiesApiResponse.class).getBody().getData();
    }
}
