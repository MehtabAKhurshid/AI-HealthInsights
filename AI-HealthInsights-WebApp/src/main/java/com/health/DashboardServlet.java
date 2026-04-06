package com.health;

import com.google.gson.Gson;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class DashboardServlet extends HttpServlet {
    private DataService dataService = new DataService();
    private HealthInsightsService insightsService = new HealthInsightsService();
    private List<Patient> patients;
    
    @Override
    public void init() throws ServletException {
        try {
            String dataPath = getClass().getClassLoader().getResource("iot_health_data.json").getPath();
            patients = dataService.loadPatientData(dataPath);
        } catch (Exception e) {
            e.printStackTrace();
            patients = new ArrayList<>();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        String patientId = req.getParameter("patientId");
        
        Map<String, Object> response = new HashMap<>();
        if (patientId != null && !patientId.isEmpty()) {
            for (Patient p : patients) {
                if (p.getId().equals(patientId)) {
                    response.put("patient", p);
                    response.put("insights", insightsService.generateInsights(p.getReadings()));
                    break;
                }
            }
        } else {
            response.put("patients", patients);
        }
        resp.getWriter().write(new Gson().toJson(response));
    }
}
