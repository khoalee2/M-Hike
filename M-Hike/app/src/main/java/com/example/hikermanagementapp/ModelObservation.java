package com.example.hikermanagementapp;

public class ModelObservation {
    private String id,name, time, comment,hikeId;

    public ModelObservation(String id, String name, String time, String comment, String hikeId) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.comment = comment;
        this.hikeId = hikeId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setCommen(String comment) {
        this.comment = comment;
    }

    public String getHikeId() {
        return hikeId;
    }

    public void setHikeId(String hikeId) {
        this.hikeId = hikeId;
    }
}
