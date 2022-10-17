package com.city.explorer.geo.db.adapter.server.models.countries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDetailsResponse {
    private CountryDetails data;

    public CountryDetails getData() {
        return data;
    }

    public void setData(CountryDetails data) {
        this.data = data;
    }

    public static class CountryDetails {
        private String code;
        private String name;
        private String capital;
        private String callingCode;
        private String flagImageUri;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCapital() {
            return capital;
        }

        public void setCapital(String capital) {
            this.capital = capital;
        }

        public String getCallingCode() {
            return callingCode;
        }

        public void setCallingCode(String callingCode) {
            this.callingCode = callingCode;
        }

        public String getFlagImageUri() {
            return flagImageUri;
        }

        public void setFlagImageUri(String flagImageUri) {
            this.flagImageUri = flagImageUri;
        }
    }
}
