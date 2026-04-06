package com.health;

public class HealthReading {
    private String timestamp;
    private int heartRate;
    private String bloodPressure;
    private double temperature;
    private int oxygenLevel;
    private String activityLevel;
    private double sleepHours;

    public HealthReading(String timestamp, int heartRate, String bloodPressure, 
                         double temperature, int oxygenLevel, String activityLevel, double sleepHours) {
        this.timestamp = timestamp;
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.temperature = temperature;
        this.oxygenLevel = oxygenLevel;
        this.activityLevel = activityLevel;
        this.sleepHours = sleepHours;
    }

    public String getTimestamp() { return timestamp; }
    public int getHeartRate() { return heartRate; }
    public String getBloodPressure() { return bloodPressure; }
    public double getTemperature() { return temperature; }
    public int getOxygenLevel() { return oxygenLevel; }
    public String getActivityLevel() { return activityLevel; }
    public double getSleepHours() { return sleepHours; }
}