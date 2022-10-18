package com.city.explorer.country.graphql.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.Collections;
import java.util.List;

@DgsComponent
public class CountryDataFetcher {

    @DgsQuery
    public List<Object> countries(@InputArgument String nameFilter,
                                  @InputArgument Integer limit,
                                  @InputArgument Integer offset) {
        return Collections.emptyList();
    }

    @DgsQuery
    public Object country(@InputArgument String id) {
        return null;
    }

}
