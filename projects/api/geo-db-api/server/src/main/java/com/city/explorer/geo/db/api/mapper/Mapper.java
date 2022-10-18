package com.city.explorer.geo.db.api.mapper;

import org.springframework.lang.NonNull;

import static java.util.Optional.ofNullable;

public final class Mapper {
    private Mapper() {
        // util class
    }

    public static class Country {
        private Country() {
            // util class
        }

        public static com.city.explorer.geo.db.api.country.Country toProto(
                @NonNull com.city.explorer.geo.db.api.model.Country entity) {
            return com.city.explorer.geo.db.api.country.Country.newBuilder()
                    .setId(entity.getId())
                    .setName(entity.getName())
                    .setArea(ofNullable(entity.getArea()).orElse(0L))
                    .setCapital(ofNullable(entity.getCapital()).orElse(""))
                    .setCurrencyIso(ofNullable(entity.getCurrencyIso()).orElse(""))
                    .setCurrencyName(ofNullable(entity.getCurrencyName()).orElse(""))
                    .setFlagImageUri(ofNullable(entity.getFlagImageUri()).orElse(""))
                    .setPopulation(ofNullable(entity.getPopulation()).orElse(0L))
                    .setPhoneCode(ofNullable(entity.getPhoneCode()).orElse(""))
                    .build();
        }
    }
}
