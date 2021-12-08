package ru.denfad.project1.models;


import java.util.ArrayList;
import java.util.List;

public class Building {


    private Integer id;
    private String address;
    private List<Entrance> entrances = new ArrayList<>();

    public Building(Integer id, String address, List<Entrance> entrances) {
        this.id = id;
        this.address = address;
        this.entrances = entrances;
    }

    public Building() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Entrance> getEntrances() {
        return entrances;
    }

    public void setEntrances(List<Entrance> entrances) {
        this.entrances = entrances;
    }

    private void addEntrance(Entrance e) {
        entrances.add(e);
    }
}
