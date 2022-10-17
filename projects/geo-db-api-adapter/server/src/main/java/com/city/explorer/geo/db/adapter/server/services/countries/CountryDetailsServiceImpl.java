package com.city.explorer.geo.db.adapter.server.services.countries;

import com.city.explorer.geo.db.adapter.countries.details.CountryDetails;
import com.city.explorer.geo.db.adapter.countries.details.CountryDetailsRequest;
import com.city.explorer.geo.db.adapter.countries.details.CountryDetailsServiceGrpc;
import com.city.explorer.geo.db.adapter.server.services.GeoDbApiClient;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CountryDetailsServiceImpl extends CountryDetailsServiceGrpc.CountryDetailsServiceImplBase {

    private final GeoDbApiClient apiClient;

    public CountryDetailsServiceImpl(GeoDbApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public void getCountryDetails(CountryDetailsRequest request, StreamObserver<CountryDetails> responseObserver) {
        var countryDetails = apiClient.getCountryDetails(request.getId()).getData();
        var response = CountryDetails.newBuilder()
                .setCode(countryDetails.getCode())
                .setName(countryDetails.getName())
                .setCapital(countryDetails.getCapital())
                .setCallingCode(countryDetails.getCallingCode())
                .setFlagImageUri(countryDetails.getFlagImageUri())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
