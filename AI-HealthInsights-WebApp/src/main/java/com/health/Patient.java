package com.health;

import java.util.List;

public class Patient {
    private String id;
    private String name;
    private int age;
    private List<HealthReading> readings;

    public Patient(String id, String name, int age, List<HealthReading> readings) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.readings = readings;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public List<HealthReading> getReadings() { return readings; }
}