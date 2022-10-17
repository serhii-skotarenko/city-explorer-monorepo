package com.city.explorer.city.details.services;

import com.city.explorer.geo.db.adapter.cities.details.CityDetails;
import com.city.explorer.geo.db.adapter.cities.details.CityDetailsRequest;
import com.city.explorer.geo.db.adapter.cities.details.CityDetailsServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CityDetailsServiceImpl implements CityDetailsService {

    @GrpcClient("geo-db-grpc-api")
    private CityDetailsServiceGrpc.CityDetailsServiceBlockingStub cityDetailsServiceBlockingStub;

    @Override
    public CityDetails getForCity(@NonNull String cityId) {
        var cityDetailsRequest = CityDetailsRequest.newBuilder().setId(cityId).build();
        return cityDetailsServiceBlockingStub.getCityDetails(cityDetailsRequest);
    }
}
