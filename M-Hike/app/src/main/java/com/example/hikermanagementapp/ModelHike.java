package com.example.hikermanagementapp;

public class ModelHike {

    private String id,name,location,parking,date,length,level,description,cost,weather;

    public ModelHike(String id, String name, String location, String parking,String date,String length,String level,String description,String cost,String weather){
        this.id=id;
        this.name=name;
        this.location=location;
        this.parking=parking;
        this.date=date;
        this.length=length;
        this.level=level;
        this.description=description;
        this.cost=cost;
        this.weather=weather;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setMemberQuan(String memberQuan) {
        this.cost = cost;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String type) {
        this.weather = weather;
    }
}
