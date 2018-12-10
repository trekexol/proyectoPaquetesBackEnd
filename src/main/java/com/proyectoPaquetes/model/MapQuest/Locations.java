package com.proyectoPaquetes.model.MapQuest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Locations implements Serializable {
        private List<LocationsData> locations;


    public List<LocationsData> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationsData> locations) {
        this.locations = locations;
    }
}
