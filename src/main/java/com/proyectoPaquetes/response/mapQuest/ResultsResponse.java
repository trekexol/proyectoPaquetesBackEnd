package com.proyectoPaquetes.response.mapQuest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class ResultsResponse {

    private List<MapQuestResponse> results;


    public List<MapQuestResponse> getResults() {
        return results;
    }

    public void setResults(List<MapQuestResponse> results) {
        this.results = results;
    }
}
