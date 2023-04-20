package dtos;

import entities.City;

import java.io.Serializable;
import java.util.Objects;

public class CityDTO {
    private String name;

    public CityDTO() {
    }

    public CityDTO(City city) {
        this.name = city.getName();
    }

    public CityDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ")";
    }
}
