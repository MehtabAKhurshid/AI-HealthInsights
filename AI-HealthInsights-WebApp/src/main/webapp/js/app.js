let allPatients = [];

fetch('/elderly-health-monitor/api/dashboard')
    .then(res => res.json())
    .then(data => {
        allPatients = data.patients;
        populatePatientSelect();
    })
    .catch(err => console.error('Error loading patients:', err));

function populatePatientSelect() {
    const select = document.getElementById('patientSelect');
    allPatients.forEach(p => {
        const option = document.createElement('option');
        option.value = p.id;
        option.textContent = `${p.name} (Age: ${p.age})`;
        select.appendChild(option);
    });
}

function loadPatientData() {
    const patientId = document.getElementById('patientSelect').value;
    if (!patientId) {
        document.getElementById('dashboard').style.display = 'none';
        return;
    }
    
    fetch(`/elderly-health-monitor/api/dashboard?patientId=${patientId}`)
        .then(res => res.json())
        .then(data => {
            const patient = data.patient;
            const insights = data.insights;
            
            document.getElementById('patientName').textContent = patient.name;
            document.getElementById('patientAge').textContent = `Age: ${patient.age}`;
            document.getElementById('alertsText').textContent = insights.alerts;
            document.getElementById('avgHeartRate').textContent = insights.avgHeartRate + ' bpm';
            document.getElementById('avgOxygen').textContent = insights.avgOxygen + ' %';
            document.getElementById('avgTemperature').textContent = insights.avgTemperature + ' °C';
            document.getElementById('avgSleepHours').textContent = insights.avgSleepHours + ' hrs';
            
            const tbody = document.getElementById('readingsBody');
            tbody.innerHTML = '';
            patient.readings.forEach(r => {
                const row = tbody.insertRow();
                row.innerHTML = `
                    <td>${new Date(r.timestamp).toLocaleString()}</td>
                    <td>${r.heartRate} bpm</td>
                    <td>${r.bloodPressure}</td>
                    <td>${r.temperature} °C</td>
                    <td>${r.oxygenLevel} %</td>
                    <td>${r.activityLevel}</td>
                `;
            });
            
            document.getElementById('dashboard').style.display = 'block';
        })
        .catch(err => console.error('Error loading patient data:', err));
}
