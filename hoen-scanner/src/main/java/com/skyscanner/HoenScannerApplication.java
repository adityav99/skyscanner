package com.skyscanner;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class HoenScannerApplication extends Application<io.dropwizard.Configuration> {
    public static void main(String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public void run(io.dropwizard.Configuration configuration, Environment environment) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List<SearchResult> hotels = mapper.readValue(
            getClass().getResource("/hotels.json"),
            new TypeReference<List<SearchResult>>() {}
        );

        List<SearchResult> rentals = mapper.readValue(
            getClass().getResource("/rental_cars.json"),
            new TypeReference<List<SearchResult>>() {}
        );

        List<SearchResult> allResults = new ArrayList<>();
        allResults.addAll(hotels);
        allResults.addAll(rentals);

        environment.jersey().register(new SearchResource(allResults));
    }
}