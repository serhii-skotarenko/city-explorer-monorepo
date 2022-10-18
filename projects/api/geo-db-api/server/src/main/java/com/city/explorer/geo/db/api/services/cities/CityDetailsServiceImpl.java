package com.city.explorer.geo.db.api.services.cities;

import com.city.explorer.geo.db.adapter.cities.details.CityDetails;
import com.city.explorer.geo.db.adapter.cities.details.CityDetailsRequest;
import com.city.explorer.geo.db.adapter.cities.details.CityDetailsServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CityDetailsServiceImpl extends CityDetailsServiceGrpc.CityDetailsServiceImplBase {
    @Override
    public void getCityDetails(CityDetailsRequest request, StreamObserver<CityDetails> responseObserver) {
        super.getCityDetails(request, responseObserver);
    }
}
