package com.city.explorer.city.details.services.dto;

import com.city.explorer.city.details.generated.types.CityDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CitiesApiResponse {
    private List<CityDetails> data;

    public List<CityDetails> getData() {
        return data;
    }

    public void setData(List<CityDetails> data) {
        this.data = data;
    }
}
