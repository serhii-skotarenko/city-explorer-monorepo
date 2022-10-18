package com.city.explorer.city.details.services;

import com.city.explorer.geo.db.adapter.cities.list.City;
import com.city.explorer.geo.db.adapter.cities.list.ListCitiesRequest;
import com.city.explorer.geo.db.adapter.cities.list.ListCitiesServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCitiesServiceImpl implements ListCitiesService {

    @GrpcClient("geo-db-grpc-api")
    private ListCitiesServiceGrpc.ListCitiesServiceBlockingStub listCitiesServiceBlockingStub;

    @Override
    public List<City> findCities(@NotNull String prefix) {
        var listCitiesRequest = ListCitiesRequest.newBuilder().setPrefix(prefix).build();
        return listCitiesServiceBlockingStub.listCities(listCitiesRequest).getDataList();
    }
}
