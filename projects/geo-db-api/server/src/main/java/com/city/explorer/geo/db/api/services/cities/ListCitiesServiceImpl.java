package com.city.explorer.geo.db.api.services.cities;

import com.city.explorer.geo.db.adapter.cities.list.ListCitiesRequest;
import com.city.explorer.geo.db.adapter.cities.list.ListCitiesResponse;
import com.city.explorer.geo.db.adapter.cities.list.ListCitiesServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ListCitiesServiceImpl extends ListCitiesServiceGrpc.ListCitiesServiceImplBase {
    @Override
    public void listCities(ListCitiesRequest request, StreamObserver<ListCitiesResponse> responseObserver) {
        super.listCities(request, responseObserver);
    }
}
