package repository;

import config.DatabaseConfiguration;
import person.enums_and_salary.BloodGroup;
import person.enums_and_salary.Gender;
import person.enums_and_salary.Specialization;
import person.type.Doctor;
import person.type.Doctor;
import person.type.Patient;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {
    public void addDoctor(Doctor doctor) {
        String preparedSql = "{call addDoctor(?,?,?,?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, doctor.getName());
            cstmt.setInt(3, doctor.getAge());
            cstmt.setString(4, doctor.getGender().toString());
            cstmt.setInt(5,doctor.getExp());
            cstmt.setString(6,doctor.getSpec().toString());
            cstmt.registerOutParameter(1, Types.INTEGER); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added user with id:" + cstmt.getInt(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Doctor> read() {
        String selectSql = "SELECT * FROM doctors";
        List<Doctor> doctors = new ArrayList<>();
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                Gender gender=Gender.valueOf(resultSet.getString(4));
                int exp=resultSet.getInt(5);
                Specialization spec=Specialization.valueOf(resultSet.getString(6));
                Doctor p=new Doctor(name,age,gender,id,exp,spec);
                doctors.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
    
    public Doctor getDoctorById(int id) {
        String selectSql = "SELECT * FROM doctors WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDoctor(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateDoctor(String name, int age, Gender gender, int id, int exp, Specialization spec ) {
        String updateNameSql = "UPDATE doctors SET name=?, age=?, gender=?, experience=?, specialization=?" +
                " WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3,gender.toString());
            preparedStatement.setInt(4,exp);
            preparedStatement.setString(5,spec.toString());
            preparedStatement.setInt(6,id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Doctor mapToDoctor(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Doctor(resultSet.getString(2), resultSet.getInt(3),
                    Gender.valueOf(resultSet.getString(4)), resultSet.getInt(1), resultSet.getInt(5),
                    Specialization.valueOf(resultSet.getString(6)));
        }
        return null;
    }

    public void delete(int id, Connection connection) throws SQLException {
        System.out.println(id);
        String sql = "DELETE FROM doctors WHERE id=" + id;
        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);
    }
}
