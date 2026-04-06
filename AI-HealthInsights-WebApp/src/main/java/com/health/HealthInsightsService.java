package com.health;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class HealthInsightsService {
    
    public Map<String, Object> generateInsights(List<HealthReading> readings) {
        Map<String, Object> insights = new HashMap<>();
        
        if (readings.isEmpty()) return insights;
        
        double avgHeartRate = readings.stream().mapToInt(HealthReading::getHeartRate).average().orElse(0);
        double avgOxygen = readings.stream().mapToInt(HealthReading::getOxygenLevel).average().orElse(0);
        double avgTemp = readings.stream().mapToDouble(HealthReading::getTemperature).average().orElse(0);
        double avgSleep = readings.stream().mapToDouble(HealthReading::getSleepHours).average().orElse(0);
        
        insights.put("avgHeartRate", Math.round(avgHeartRate));
        insights.put("avgOxygen", Math.round(avgOxygen));
        insights.put("avgTemperature", Math.round(avgTemp * 10) / 10.0);
        insights.put("avgSleepHours", Math.round(avgSleep * 10) / 10.0);
        
        StringBuilder alerts = new StringBuilder();
        if (avgHeartRate > 100) alerts.append("⚠️ High average heart rate. ");
        if (avgOxygen < 95) alerts.append("⚠️ Low oxygen levels. ");
        if (avgTemp > 37.5) alerts.append("⚠️ Elevated temperature. ");
        if (avgSleep < 6) alerts.append("⚠️ Insufficient sleep. ");
        
        insights.put("alerts", alerts.length() > 0 ? alerts.toString() : "✓ All metrics normal");
        
        return insights;
    }
}