
package config;



import repository.RepositoryHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSetup {

    public void setUp() {
        String createTablePatient = "CREATE TABLE IF NOT EXISTS patients" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30)," +
                "age double,"+ "gender varchar(30)," + "bloodGroup varchar(30),"
                +"height double,"+"weight double,"+"donate int)";
        String createTableDoctor = "CREATE TABLE IF NOT EXISTS doctors" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30)," +
                "age double,"+ "gender varchar(30)," +"experience int,"+"specialization varchar(30))";
        String createTableNurse =  "CREATE TABLE IF NOT EXISTS nurses" +
                "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30)," +
                "age double,"+ "gender varchar(30)," +"experience int)";
        String createTableAppointment = "CREATE TABLE IF NOT EXISTS appointments" +
                "(id int PRIMARY KEY AUTO_INCREMENT, date1 varchar(30),PatientId int ,"
                +"DoctorId int, "+"duration int,"+
                "NurseId int,FOREIGN KEY (PatientId) REFERENCES patients(id),FOREIGN KEY (DoctorId) REFERENCES doctors(id), FOREIGN KEY (NurseId) REFERENCES nurses(id))";
                

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            repositoryHelper.executeSql(databaseConnection, createTableAppointment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            repositoryHelper.executeSql(databaseConnection, createTablePatient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            repositoryHelper.executeSql(databaseConnection, createTableNurse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            repositoryHelper.executeSql(databaseConnection, createTableDoctor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void displayPatient() {
        String selectSql = "SELECT * FROM patients";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getInt(1));
                System.out.println("Name:" + resultSet.getString(2));
                System.out.println("Age:" + resultSet.getInt(3));
                System.out.println("Gender:" + resultSet.getString(4));
                System.out.println("BloodGroup:" + resultSet.getString(5));
                System.out.println("Height:" + resultSet.getDouble(6));
                System.out.println("Weight:" + resultSet.getDouble(7));
                System.out.println("Donate:" + resultSet.getInt(8));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayDoctor() {
        String selectSql = "SELECT * FROM doctors";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getInt(1));
                System.out.println("Name:" + resultSet.getString(2));
                System.out.println("Age:" + resultSet.getDouble(3));
                System.out.println("Gender:" + resultSet.getString(4));
                System.out.println("Experience:" + resultSet.getInt(5));
                System.out.println("Specialization" + resultSet.getString(6));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayNurse() {
        String selectSql = "SELECT * FROM nurses";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getInt(1));
                System.out.println("Name:" + resultSet.getString(2));
                System.out.println("Age:" + resultSet.getDouble(3));
                System.out.println("Gender:" + resultSet.getString(4));
                System.out.println("Experience:" + resultSet.getInt(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAppointment() {
        String selectSql = "SELECT * FROM appointments";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getInt(1));
                System.out.println("Date:" + resultSet.getString(2));
                System.out.println("PatientId:" + resultSet.getInt(3));
                System.out.println("DoctorId:" + resultSet.getInt(4));
                System.out.println("Duration:" + resultSet.getInt(5));
                System.out.println("NurseId:" + resultSet.getInt(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}