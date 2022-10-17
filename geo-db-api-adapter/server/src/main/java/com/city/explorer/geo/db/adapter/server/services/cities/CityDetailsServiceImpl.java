package com.city.explorer.geo.db.adapter.server.services.cities;

import com.city.explorer.geo.db.adapter.cities.details.CityDetails;
import com.city.explorer.geo.db.adapter.cities.details.CityDetailsRequest;
import com.city.explorer.geo.db.adapter.cities.details.CityDetailsServiceGrpc;
import com.city.explorer.geo.db.adapter.server.services.GeoDbApiClient;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CityDetailsServiceImpl extends CityDetailsServiceGrpc.CityDetailsServiceImplBase {

    private final GeoDbApiClient apiClient;

    public CityDetailsServiceImpl(GeoDbApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public void getCityDetails(CityDetailsRequest request, StreamObserver<CityDetails> responseObserver) {
        var cityDetails = apiClient.getCityDetails(request.getId()).getData();
        var response = CityDetails.newBuilder()
                .setId(cityDetails.getId())
                .setCountry(cityDetails.getCountry())
                .setCountryCode(cityDetails.getCountryCode())
                .setRegion(cityDetails.getRegion())
                .setLatitude(cityDetails.getLatitude().floatValue())
                .setLongitude(cityDetails.getLongitude().floatValue())
                .setElevationMeters(cityDetails.getElevationMeters())
                .setPopulation(cityDetails.getPopulation())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
