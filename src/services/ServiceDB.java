package services;


import appointment.actions.BloodTransfusion;
import config.DataSetup;
import config.DatabaseConfiguration;
import medical_office.MedicalOffice;
import person.enums_and_salary.BloodGroup;
import person.enums_and_salary.Gender;
import person.enums_and_salary.Specialization;
import person.type.Doctor;
import person.type.Nurse;
import person.type.Patient;
import repository.PatientRepository;
import repository.NurseRepository;
import repository.DoctorRepository;
import repository.BloodTransfusionRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import static medical_office.MedicalOffice.*;

public class ServiceDB {
    private static ServiceDB service;

    private ServiceDB() {
    }

    public static ServiceDB getInstance() {
        if (service == null) {
            service = new ServiceDB();
        }

        return service;
    }

    public static void service() throws ParseException, IOException, SQLException {
        MedicalOffice.initializeMap();
        Connection databaseConfiguration = DatabaseConfiguration.getDatabaseConnection();
        DataSetup setUpData = new DataSetup();
        setUpData.setUp();
        PatientRepository patientRepository = new PatientRepository();
        DoctorRepository doctorRepository = new DoctorRepository();
        NurseRepository nurseRepository = new NurseRepository();
        BloodTransfusionRepository bloodTransfusionRepository = new BloodTransfusionRepository();
        String option = "";
        ServiceDB service = ServiceDB.getInstance();
        Scanner in = new Scanner(System.in);
        while (!option.equals("0")) {
            System.out.println("            ========Database options========\n");
            System.out.println("****Adding methods****");
            System.out.println("1:  Add patient");
            System.out.println("2:  Add doctor");
            System.out.println("3:  Add nurse");
            System.out.println("4:  Add appointment\n");
            System.out.println("****reading methods****");
            System.out.println("5:  Read patients");
            System.out.println("6:  Read doctors");
            System.out.println("7:  Read nurses");
            System.out.println("8:  Read appointments\n");
            System.out.println("****Display methods****");
            System.out.println("9:  Display patients");
            System.out.println("10: Display doctors");
            System.out.println("11: Display nurses");
            System.out.println("12: Display appointments\n");
            System.out.println("****Display methods (by id)****");
            System.out.println("13: Display patients");
            System.out.println("14: Display doctors");
            System.out.println("15: Display nurses");
            System.out.println("16: Display appointments\n");
            System.out.println("****Update methods****");
            System.out.println("17: Update patients");
            System.out.println("18: Update doctors");
            System.out.println("19: Update nurses");
            System.out.println("20: Update appointments\n");
            System.out.println("****Delete methods****");
            System.out.println("21: Delete patient");
            System.out.println("22: Delete doctor");
            System.out.println("23: Delete nurse");
            System.out.println("24: Delete appointment");
            System.out.println("0:  Exit");
            option = in.nextLine();
            if (option.equals("0"))
                break;
            if (option.equals("1")) {
                Patient p = new Patient();
                patientRepository.addPatient(p);
                setUpData.displayPatient();
            }
            if (option.equals("2")) {
                Doctor p = new Doctor();
                doctorRepository.addDoctor(p);
                setUpData.displayDoctor();
            }
            if (option.equals("3")) {
                Nurse p = new Nurse();
                nurseRepository.addNurse(p);
                setUpData.displayNurse();
            }
            if (option.equals("4")) {
                System.out.println("\nDate: ");
                String date = in.nextLine();
                System.out.println("\nId patient: ");
                int patientId = in.nextInt();
                System.out.println("\nId doctor: ");
                int doctorId = in.nextInt();
                System.out.println("\nDuration: ");
                int duration=in.nextInt();
                System.out.println("\nId nurse: ");
                int nurseId = in.nextInt();
                BloodTransfusion p = new BloodTransfusion(date,patientId,doctorId,duration,nurseId,0);
                bloodTransfusionRepository.addBloodTransfusion(p);
                setUpData.displayAppointment();
            }

            if (option.equals("5"))
                patients = patientRepository.read();
            if (option.equals("6"))
                doctors = doctorRepository.read();
            if (option.equals("7"))
                nurses = nurseRepository.read();
            if (option.equals("8"))
                bloodTransfusions = bloodTransfusionRepository.read();

            if (option.equals("9"))
                setUpData.displayPatient();
            if (option.equals("10"))
                setUpData.displayDoctor();
            if (option.equals("11"))
                setUpData.displayNurse();
            if (option.equals("12"))
                setUpData.displayAppointment();

            if (option.equals("13")) {
                System.out.println("Cautare patient dupa id-ul: ");
                int op = in.nextInt();
                Patient p=patientRepository.getPatientById(op);
                System.out.println(p.toString());
            }
            if (option.equals("14")) {
                System.out.println("Cautare nurse dupa id-ul: ");
                int op = in.nextInt();
                Nurse p=nurseRepository.getNurseById(op);
                System.out.println(p.toString());
            }
            if (option.equals("15")) {
                System.out.println("Cautare doctor dupa id-ul: ");
                int op = in.nextInt();
                Doctor p=doctorRepository.getDoctorById(op);
                System.out.println(p.toString());
            }
            if (option.equals("16")) {
                System.out.println("Cautare appointment dupa id-ul: ");
                int op = in.nextInt();
                BloodTransfusion p= bloodTransfusionRepository.getBloodTransfusionById(op);
                System.out.println(p.toString());
            }

            if (option.equals("17")){
                System.out.println("Id patient:");
                int id=in.nextInt();
                System.out.println("Nume:");
                String name = in.next();
                System.out.println("\nAge: ");
                int age = in.nextInt();
                System.out.println("\nGender (MALE/FEMALE/OTHER):");
                String s = in.next();
                Gender gender = Gender.valueOf(s.toUpperCase());
                System.out.println("\nBlood group (ZERO/A/B/AB):");
                s = in.next();
                BloodGroup bloodGroup = BloodGroup.valueOf(s.toUpperCase());
                System.out.println("\nHeight (cm):");
                Double height = in.nextDouble();
                System.out.println("\nWeight (kg):");
                Double weight = in.nextDouble();
                System.out.println("Number of donations");
                int donate=in.nextInt();
                patientRepository.updatePatient(name,age,gender,id,bloodGroup,height,weight,donate);
            }
            if (option.equals("18")){
                System.out.println("Id doctor:");
                int id=in.nextInt();
                System.out.println("\nNume: ");
                String name = in.next();
                System.out.println("\nAge: ");
                int age = in.nextInt();
                System.out.println("\nGender (MALE/FEMALE/OTHER): ");
                String s = in.next();
                Gender gender = Gender.valueOf(s.toUpperCase());
                System.out.println("\nExperience (years): ");
                int exp = in.nextInt();
                System.out.println("\nSpecialization (RADIOLOGIST/BLOOD_ANALYSIS/CONSULTANT");
                s = in.next();
                Specialization spec = Specialization.valueOf(s.toUpperCase());
                doctorRepository.updateDoctor(name,age,gender,id,exp,spec);
            }
            if (option.equals("19")){
                System.out.println("Id nurse:");
                int id=in.nextInt();
                System.out.println("\nNume: ");
                String name = in.next();
                System.out.println("\nAge: ");
                int age = in.nextInt();
                System.out.println("\nGender (MALE/FEMALE/OTHER): ");
                String s = in.next();
                Gender gender = Gender.valueOf(s.toUpperCase());
                System.out.println("\nExperience (years): ");
                int exp = in.nextInt();
                nurseRepository.updateNurse(name,age,gender,id,exp);
            }
            if (option.equals("20")){
                System.out.println("Id appointment:");
                int id=in.nextInt();
                System.out.println("\nDate: ");
                String date = in.next();
                System.out.println("\nId patient: ");
                int patientId = in.nextInt();
                System.out.println("\nId doctor: ");
                int doctorId = in.nextInt();
                System.out.println("\nDuration: ");
                int duration=in.nextInt();
                System.out.println("\nId nurse: ");
                int nurseId = in.nextInt();
                bloodTransfusionRepository.updateBloodTransfusion(date,patientId,doctorId,duration,nurseId,id);
            }
            if (option.equals("21")) {
                System.out.println("Patient id: ");
                int op = in.nextInt();
                patientRepository.delete(op,databaseConfiguration);
            }
            if (option.equals("22")) {
                System.out.println("Doctor id: ");
                int op = in.nextInt();
                doctorRepository.delete(op,databaseConfiguration);
            }
            if (option.equals("23")) {
                System.out.println("Nurse id: ");
                int op = in.nextInt();
                nurseRepository.delete(op,databaseConfiguration);
            }
            if (option.equals("24")) {
                System.out.println("Appointment id: ");
                int op = in.nextInt();
                bloodTransfusionRepository.delete(op,databaseConfiguration);
            }
            if (option.equals("0"))
                break;
        }
    }
}