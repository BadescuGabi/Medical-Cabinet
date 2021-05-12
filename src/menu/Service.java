package menu;

import appointment.actions.BloodTransfusion;
import appointment.actions.MedicalExamination;
import appointment.actions.Ultrasound;
import appointment.actions.Vaccine;
import person.enums_and_salary.BloodGroup;
import person.enums_and_salary.Gender;
import person.enums_and_salary.Specialization;
import person.type.Doctor;
import person.type.Nurse;
import person.type.Patient;
import static medical_office.MedicalOffice.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public final class Service {

    private static Service service;
    public static FileWriter fileWriter;

    private Service() {
    }

    public static Service getInstance() {
        if (service == null) {
            service = new Service();
        }

        return service;
    }

    public static <T> ArrayList<T> readFromCSV(String option, String path) throws IOException, ParseException {
        BufferedReader csvReader = new BufferedReader(new FileReader(path));
        String row;
        ArrayList<T> obj = new ArrayList<>();

        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if (option.equalsIgnoreCase("doctor") || option.equalsIgnoreCase("patient") || option.equalsIgnoreCase("nurse")) {
                String name = data[0];
                int age = Integer.parseInt(data[1]);
                Gender gender = Gender.valueOf(data[2].toUpperCase());
                if (option.equalsIgnoreCase("doctor")) {
                    Integer exp = Integer.parseInt(data[3]);
                    Specialization spec = Specialization.valueOf(data[4]);
                    int id = Integer.parseInt(data[5]);
                    Doctor doctor = new Doctor(name, age, gender, id, exp, spec);
                    obj.add((T) doctor);
                }
                if (option.equalsIgnoreCase("patient")) {
                    BloodGroup bloodGroup = BloodGroup.valueOf(data[3].toUpperCase());
                    double height = Double.parseDouble(data[4]);
                    double weight = Double.parseDouble(data[5]);
                    int donate = Integer.parseInt(data[6]);
                    int id = Integer.parseInt(data[7]);
                    Patient patient = new Patient(name, age, gender, id, bloodGroup, height, weight, donate);
                    obj.add((T) patient);
                }
                if (option.equalsIgnoreCase("nurse")) {
                    int exp = Integer.parseInt(data[3]);
                    int id = Integer.parseInt(data[4]);
                    Nurse nurse = new Nurse(name, age, gender, id, exp);
                    obj.add((T) nurse);
                }
            }
            if (option.equalsIgnoreCase("bloodtransfusion") || option.equalsIgnoreCase("medicalexamination")
                    || option.equalsIgnoreCase("ultrasound") || option.equalsIgnoreCase("vaccine")) {
                String date = data[0];
                int id = Integer.parseInt(data[1]);
                Patient patientF = null;
                for (Patient patient : patients)
                    if (patient.getId() == id)
                        patientF = new Patient(patient.getName(),patient.getAge(),patient.getGender(),patient.getId(),patient.getBloodGroup(),patient.getHeight(),patient.getWeight(),patient.getDonate());
                id = Integer.parseInt(data[2]);
                Doctor doctorF = null;
                for (Doctor doctor : doctors)
                    if (doctor.getId() == id)
                        doctorF = doctor;
                if (option.equalsIgnoreCase("bloodtransfusion")) {
                    int duration = Integer.parseInt(data[3]);
                    id = Integer.parseInt(data[4]);
                    Nurse nurseF = null;
                    for (Nurse nurse : nurses)
                        if (nurse.getId() == id)
                            nurseF = nurse;
                    BloodTransfusion bloodTransfusion = new BloodTransfusion(date, patientF, doctorF, duration, nurseF);
                    obj.add((T) bloodTransfusion);
                }
                if (option.equalsIgnoreCase("medicalexamination")) {
                    int duration = Integer.parseInt(data[4]);
                    int price = Integer.parseInt(data[3]);
                    String description = data[5];
                    MedicalExamination medicalExamination = new MedicalExamination(date, patientF, doctorF, price, duration, description);
                    obj.add((T) medicalExamination);
                }
                if (option.equalsIgnoreCase("ultrasound")) {
                    int duration = Integer.parseInt(data[4]);
                    int price = Integer.parseInt(data[3]);
                    boolean referral = Boolean.parseBoolean(data[5]);
                    Ultrasound ultrasound = new Ultrasound(date, patientF, doctorF, price, duration, referral);
                    obj.add((T) ultrasound);
                }
                if (option.equalsIgnoreCase("vaccine")) {
                    Double covidAntibody = Double.parseDouble(data[3]);
                    Vaccine vaccine = new Vaccine(date, patientF, doctorF, covidAntibody);
                    obj.add((T) vaccine);
                }
            }
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        writeToAudit(timestamp.toString(), "Read");
        csvReader.close();
        return obj;
    }

    public static <T> void writeToCSV(T object, String path) throws IOException {
        File file = new File(path);
        if (file.isFile()) {
            if (file.length() <= 1) {
//                FileWriter csvWriter = new FileWriter(path, true);
//                if (object.getClass().getSimpleName().toLowerCase() == "doctor")
//                    csvWriter.append("Name.age,gender,specialization,experience,id\n");
//                if (object.getClass().getSimpleName().toLowerCase() == "patient")
//                    csvWriter.append("Name.age,gender,bloodgroup,height,weight,donate,id\n");
//                if (object.getClass().getSimpleName().toLowerCase() == "nurse")
//                    csvWriter.append("Name.age,gender,experience,id\n");
//                if (object.getClass().getSimpleName().toLowerCase() == "bloodtransfusion")
//                    csvWriter.append("date,patient_id,doctor_id,duration,nurse_id\n");
//                if (object.getClass().getSimpleName().toLowerCase() == "medicalexamination")
//                    csvWriter.append("date,patient_id,doctor_id,price,duration,description\n");
//                if (object.getClass().getSimpleName().toLowerCase() == "ultrasound")
//                    csvWriter.append("date,patient_id,doctor_id,price,duration,referral\n");
//                if (object.getClass().getSimpleName().toLowerCase() == "vaccine")
//                    csvWriter.append("date,patient_id,doctor_id,covidantibody\n");
//                csvWriter.close();
            }
            FileWriter csvWriter = new FileWriter(path, true);
            if (object.getClass().getSimpleName().equalsIgnoreCase("doctor")) {
                Doctor d = (Doctor) object;
                csvWriter.append(d.getName());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getAge()));
                csvWriter.append(",");
                csvWriter.append(d.getGender().toString());
                csvWriter.append(",");
                csvWriter.append(d.getSpec().toString());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getExp()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getId()));
                csvWriter.append("\n");
            }
            if (object.getClass().getSimpleName().equalsIgnoreCase("patient")) {
                Patient d = (Patient) object;
                csvWriter.append(d.getName());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getAge()));
                csvWriter.append(",");
                csvWriter.append(d.getGender().toString());
                csvWriter.append(",");
                csvWriter.append(d.getBloodGroup().toString());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getHeight()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getWeight()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getDonate()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getId()));
                csvWriter.append("\n");
            }
            if (object.getClass().getSimpleName().equalsIgnoreCase("nurse")) {
                Nurse d = (Nurse) object;
                csvWriter.append(d.getName());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getAge()));
                csvWriter.append(",");
                csvWriter.append(d.getGender().toString());
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getExp()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getId()));
                csvWriter.append("\n");
            }
            if (object.getClass().getSimpleName().equalsIgnoreCase("bloodtransfusion")) {
                BloodTransfusion d = (BloodTransfusion) object;
                csvWriter.append(String.valueOf(d.getDate()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getPatient().getId()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getDoctor().getId()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getDuration()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getNurse().getId()));
                csvWriter.append("\n");
            }
            if (object.getClass().getSimpleName().equalsIgnoreCase("medicalexamination")) {
                MedicalExamination d = (MedicalExamination) object;
                csvWriter.append(String.valueOf(d.getDate()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getPatient().getId()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getDoctor().getId()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getPrice()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getDuration()));
                csvWriter.append(",");
                csvWriter.append(d.getDescription());
                csvWriter.append("\n");
            }
            if (object.getClass().getSimpleName().equalsIgnoreCase("ultrasound")) {
                Ultrasound d = (Ultrasound) object;
                csvWriter.append(String.valueOf(d.getDate()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getPatient().getId()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getDoctor().getId()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getPrice()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getDuration()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.isReferral()));
                csvWriter.append("\n");
            }
            if (object.getClass().getSimpleName().equalsIgnoreCase("vaccine")) {
                Vaccine d = (Vaccine) object;
                csvWriter.append(String.valueOf(d.getDate()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getPatient().getId()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getDoctor().getId()));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(d.getCovidAntibody()));
                csvWriter.append("\n");
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            writeToAudit(timestamp.toString(),"Write");
            csvWriter.close();
        }
    }
    public static void writeToAudit(String timestamp, String option) throws IOException{
        File file = new File("src/audit.csv");
        if (file.isFile()) {
            if (file.length() <= 1) {
                FileWriter csvWriter = new FileWriter("src/audit.csv", true);
                csvWriter.append("Timestamp,action name\n");
                csvWriter.close();
            }
            FileWriter csvWriter = new FileWriter("src/audit.csv", true);
            csvWriter.append(timestamp  + "," + option + "\n");
            csvWriter.close();
        }
}}