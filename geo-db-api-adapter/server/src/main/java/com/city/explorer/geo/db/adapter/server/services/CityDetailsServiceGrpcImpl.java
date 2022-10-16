package com.city.explorer.geo.db.adapter.server.services;


import com.city.explorer.geo.db.adapter.city.CityDetails;
import com.city.explorer.geo.db.adapter.city.CityDetailsRequest;
import com.city.explorer.geo.db.adapter.city.CityDetailsResponse;
import com.city.explorer.geo.db.adapter.city.CityDetailsServiceGrpc;
import com.city.explorer.geo.db.adapter.server.models.CitiesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Collections;

@GrpcService
public class CityDetailsServiceGrpcImpl extends CityDetailsServiceGrpc.CityDetailsServiceImplBase {
    private static final String CITIES_API_URL = "https://wft-geo-db.p.rapidapi.com/v1/geo/cities?types=CITY&namePrefix=%s";

    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${geo-db-api.headers.api-key.name}")
    private String apiKeyHeaderName;
    @Value("${geo-db-api.headers.api-key.value}")
    private String apiKeyHeaderValue;
    @Value("${geo-db-api.headers.api-host.name}")
    private String apiHostHeaderName;
    @Value("${geo-db-api.headers.api-host.value}")
    private String apiHostHeaderValue;

    public CityDetailsServiceGrpcImpl(OkHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void listCityDetails(CityDetailsRequest request, StreamObserver<CityDetailsResponse> responseObserver) {
        var responseBuilder = CityDetailsResponse.newBuilder();
        var prefix = request.getPrefix();

        if (prefix.isEmpty()) {
            responseBuilder
                    .setTotalCount(0)
                    .addAllData(Collections.emptyList());
        } else {
            var apiRequest = new Request.Builder()
                    .url(CITIES_API_URL.formatted(prefix))
                    .addHeader(apiKeyHeaderName, apiKeyHeaderValue)
                    .addHeader(apiHostHeaderName, apiHostHeaderValue)
                    .build();
            try (var apiResponse = httpClient.newCall(apiRequest).execute()) {
                var apiResponseBody = apiResponse.body();
                var citiesApiResponse = objectMapper
                        .readValue(apiResponseBody.string(), CitiesResponse.class);

                var data = citiesApiResponse.getData().stream().map(cityDetails ->
                                CityDetails.newBuilder()
                                        .setId(cityDetails.getId())
                                        .setName(cityDetails.getName())
                                        .setCountry(cityDetails.getCountry())
                                        .setCountryCode(cityDetails.getCountryCode())
                                        .setRegion(cityDetails.getRegion())
                                        .setRegionCode(cityDetails.getRegionCode())
                                        .setLatitude(cityDetails.getLatitude())
                                        .setLongitude(cityDetails.getLongitude())
                                        .setPopulation(cityDetails.getPopulation())
                                        .build())
                        .toList();

                responseBuilder
                        .addAllData(data)
                        .setTotalCount(citiesApiResponse.getMetadata().getTotalCount());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}
