package com.city.explorer.geo.db.adapter.server.services;

import com.city.explorer.geo.db.adapter.server.exceptions.GeoDbApiException;
import com.city.explorer.geo.db.adapter.server.models.cities.CitiesListResponse;
import com.city.explorer.geo.db.adapter.server.models.cities.CityDetailsResponse;
import com.city.explorer.geo.db.adapter.server.models.countries.CountryDetailsResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class GeoDbApiClient {
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Value("${geo-db-api.host}")
    private String apiHost;
    @Value("${geo-db-api.headers.api-key.name}")
    private String apiKeyHeaderName;
    @Value("${geo-db-api.headers.api-key.value}")
    private String apiKeyHeaderValue;
    @Value("${geo-db-api.headers.api-host.name}")
    private String apiHostHeaderName;
    @Value("${geo-db-api.headers.api-host.value}")
    private String apiHostHeaderValue;

    public CitiesListResponse listCitiesByPrefix(@NonNull String prefix) {
        if (prefix.isEmpty()) {
            return new CitiesListResponse();
        } else {
            var url = apiHost + "cities?types=CITY&namePrefix=%s".formatted(prefix);
            var apiRequest = new Request.Builder()
                    .url(url)
                    .build();
            return doRequest(apiRequest, CitiesListResponse.class);
        }
    }

    public CityDetailsResponse getCityDetails(@NonNull String cityId) {
        if (cityId.isEmpty()) {
            return new CityDetailsResponse();
        } else {
            var url = apiHost + "cities/%s".formatted(cityId);
            var apiRequest = new Request.Builder()
                    .url(url)
                    .build();
            return doRequest(apiRequest, CityDetailsResponse.class);
        }
    }

    public CountryDetailsResponse getCountryDetails(@NonNull String countryId) {
        if (countryId.isEmpty()) {
            return new CountryDetailsResponse();
        } else {
            var url = apiHost + "countries/%s".formatted(countryId);
            var apiRequest = new Request.Builder()
                    .url(url)
                    .build();
            return doRequest(apiRequest, CountryDetailsResponse.class);
        }
    }

    private <T> T doRequest(@NonNull Request request, Class<T> responseClazz) {
        var requestWithHeaders = request.newBuilder()
                .addHeader(apiKeyHeaderName, apiKeyHeaderValue)
                .addHeader(apiHostHeaderName, apiHostHeaderValue)
                .build();
        try (var apiResponse = httpClient.newCall(requestWithHeaders).execute()) {
            if (apiResponse.isSuccessful()) {
                var apiResponseBody = apiResponse.body();
                if (apiResponseBody != null) {
                    return objectMapper.readValue(apiResponseBody.string(), responseClazz);
                }
                throw new GeoDbApiException("No data!");
            }
            throw new GeoDbApiException("Got response code: " + apiResponse.code());
        } catch (Exception e) {
            throw new GeoDbApiException("Failed to get data from API!", e);
        }
    }

}
