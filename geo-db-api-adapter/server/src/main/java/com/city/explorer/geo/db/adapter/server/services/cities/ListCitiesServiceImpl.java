package com.city.explorer.geo.db.adapter.server.services.cities;

import com.city.explorer.geo.db.adapter.cities.list.City;
import com.city.explorer.geo.db.adapter.cities.list.ListCitiesRequest;
import com.city.explorer.geo.db.adapter.cities.list.ListCitiesResponse;
import com.city.explorer.geo.db.adapter.cities.list.ListCitiesServiceGrpc;
import com.city.explorer.geo.db.adapter.server.services.GeoDbApiClient;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ListCitiesServiceImpl extends ListCitiesServiceGrpc.ListCitiesServiceImplBase {

    private final GeoDbApiClient apiClient;

    public ListCitiesServiceImpl(GeoDbApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public void listCities(ListCitiesRequest request, StreamObserver<ListCitiesResponse> responseObserver) {
        var citiesListResponse = apiClient.listCitiesByPrefix(request.getPrefix());
        var allData = citiesListResponse.getData().stream().map(city -> City.newBuilder()
                        .setId(city.getId())
                        .setName(city.getName())
                        .build())
                .toList();

        var response = ListCitiesResponse.newBuilder()
                .addAllData(allData)
                .setTotalCount(citiesListResponse.getMetadata().getTotalCount())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
