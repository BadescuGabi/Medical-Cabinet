package repository;

import config.DatabaseConfiguration;
import person.enums_and_salary.BloodGroup;
import person.enums_and_salary.Gender;
import person.type.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    // CallableStatement
    public void addPatient(  Patient patient) {
        String preparedSql = "{call addPatient(?,?,?,?,?,?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, patient.getName());
            cstmt.setInt(3, patient.getAge());
            cstmt.setString(4, patient.getGender().toString());
            cstmt.setString(5, patient.getBloodGroup().toString());
            cstmt.setDouble(6, patient.getHeight());
            cstmt.setDouble(7, patient.getWeight());
            cstmt.setInt(8, patient.getDonate());
            cstmt.registerOutParameter(1, Types.INTEGER); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added user with id:" + cstmt.getInt(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Patient> read() {
        String selectSql = "SELECT * FROM patients";
        List<Patient> patients = new ArrayList<>();
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                Gender gender=Gender.valueOf(resultSet.getString(4));
                BloodGroup bloodGroup =BloodGroup.valueOf( resultSet.getString(5));
                Double height= resultSet.getDouble(6);
                Double weight=resultSet.getDouble(7);
                int donate =resultSet.getInt(8);
                Patient p=new Patient(name,age,gender,id,bloodGroup,height,weight,donate);
                patients.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }


    // PreparedStatement - use when we have parameters
    public  Patient getPatientById(int id) {
        String selectSql = "SELECT * FROM patients WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToPatient(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updatePatient(String name, int age, Gender gender, int id, BloodGroup bloodGroup, double height, double weight, int donate) {
        String updateNameSql = "UPDATE patients SET name=?, age=?, gender=?, bloodGroup=?, height=?, weight=?," +
                "donate=? \n WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender.toString());
            preparedStatement.setString(4, bloodGroup.toString());
            preparedStatement.setDouble(5, height);
            preparedStatement.setDouble(6, weight);
            preparedStatement.setInt(7, donate);
            preparedStatement.setInt(8, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Patient mapToPatient(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Patient(resultSet.getString(2), resultSet.getInt(3),
                    Gender.valueOf(resultSet.getString(4)), resultSet.getInt(1), BloodGroup.valueOf(resultSet.getString(5))
                    , resultSet.getDouble(6), resultSet.getDouble(7),
                    resultSet.getInt(8));
        }
        return null;
    }

    public void delete(int id, Connection connection) throws SQLException {
        System.out.println(id);
        String sql = "DELETE FROM patients WHERE id=" + id;
        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);
    }
}
