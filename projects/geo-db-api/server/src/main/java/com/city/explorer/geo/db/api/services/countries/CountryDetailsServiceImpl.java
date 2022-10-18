package com.city.explorer.geo.db.api.services.countries;

import com.city.explorer.geo.db.adapter.countries.details.CountryDetails;
import com.city.explorer.geo.db.adapter.countries.details.CountryDetailsRequest;
import com.city.explorer.geo.db.adapter.countries.details.CountryDetailsServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CountryDetailsServiceImpl extends CountryDetailsServiceGrpc.CountryDetailsServiceImplBase {
    @Override
    public void getCountryDetails(CountryDetailsRequest request, StreamObserver<CountryDetails> responseObserver) {
        super.getCountryDetails(request, responseObserver);
    }
}
