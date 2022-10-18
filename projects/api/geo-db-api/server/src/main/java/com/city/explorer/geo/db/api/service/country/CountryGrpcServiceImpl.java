package com.city.explorer.geo.db.api.service.country;

import com.city.explorer.geo.db.api.country.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CountryGrpcServiceImpl extends CountryServiceGrpc.CountryServiceImplBase {

    private final CountryService countryService;

    public CountryGrpcServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void getCountry(GetCountryRequest request, StreamObserver<Country> responseObserver) {
        var country = countryService.getById(request.getId());

        responseObserver.onNext(country);
        responseObserver.onCompleted();
    }

    @Override
    public void listCountries(ListCountriesRequest request, StreamObserver<ListCountriesResponse> responseObserver) {
        var response = countryService.list(request.getLimit(), request.getOffset(), request.getNameFilter());

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
