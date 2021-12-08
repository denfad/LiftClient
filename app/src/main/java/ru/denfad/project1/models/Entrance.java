package ru.denfad.project1.models;

import java.util.List;

public class Entrance {


    private Integer id;
    private String label;
    private Integer floors;
    private List<Elevator> elevators;

    public Entrance(Integer id, String label, Integer floors, List<Elevator> elevators) {
        this.id = id;
        this.label = label;
        this.floors = floors;
        this.elevators = elevators;
    }

    public Entrance() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getFloors() {
        return floors;
    }

    public void setFloors(Integer floors) {
        this.floors = floors;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public void setElevators(List<Elevator> elevators) {
        this.elevators = elevators;
    }
}
