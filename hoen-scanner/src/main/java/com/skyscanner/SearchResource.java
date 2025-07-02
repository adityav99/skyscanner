package com.skyscanner;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResource {
    private final List<SearchResult> searchResults;

    public SearchResource(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }

    @POST
    public List<SearchResult> searchCity(Search search) {
        String queryCity = search.getCity().toLowerCase();
        return searchResults.stream()
            .filter(r -> r.getCity().toLowerCase().equals(queryCity))
            .collect(Collectors.toList());
    }
}