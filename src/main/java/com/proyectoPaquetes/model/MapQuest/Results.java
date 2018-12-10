package com.proyectoPaquetes.model.MapQuest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Results implements Serializable {
        private List<Locations> results;


    public List<Locations> getResults() {
        return results;
    }

    public void setResults(List<Locations> results) {
        this.results = results;
    }
}
