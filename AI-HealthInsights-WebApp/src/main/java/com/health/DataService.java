package com.health;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DataService {
    
    public List<Patient> loadPatientData(String filePath) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JsonObject jsonObject = JsonParser.parseString(jsonContent).getAsJsonObject();
        JsonArray patientsArray = jsonObject.getAsJsonArray("patients");
        
        List<Patient> patients = new ArrayList<>();
        for (JsonElement element : patientsArray) {
            JsonObject patientObj = element.getAsJsonObject();
            String id = patientObj.get("id").getAsString();
            String name = patientObj.get("name").getAsString();
            int age = patientObj.get("age").getAsInt();
            
            List<HealthReading> readings = new ArrayList<>();
            JsonArray readingsArray = patientObj.getAsJsonArray("readings");
            for (JsonElement readingElement : readingsArray) {
                JsonObject reading = readingElement.getAsJsonObject();
                readings.add(new HealthReading(
                    reading.get("timestamp").getAsString(),
                    reading.get("heartRate").getAsInt(),
                    reading.get("bloodPressure").getAsString(),
                    reading.get("temperature").getAsDouble(),
                    reading.get("oxygenLevel").getAsInt(),
                    reading.get("activityLevel").getAsString(),
                    reading.get("sleepHours").getAsDouble()
                ));
            }
            patients.add(new Patient(id, name, age, readings));
        }
        return patients;
    }
}