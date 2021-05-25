package repository;

import config.DatabaseConfiguration;
import person.enums_and_salary.BloodGroup;
import person.enums_and_salary.Gender;
import person.enums_and_salary.Specialization;
import person.type.Nurse;
import person.type.Nurse;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseRepository {

    public void addNurse(Nurse nurse) {
        String preparedSql = "{call addNurse(?,?,?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, nurse.getName());
            cstmt.setInt(3, nurse.getAge());
            cstmt.setString(4, nurse.getGender().toString());
            cstmt.setInt(5,nurse.getExp());
            cstmt.registerOutParameter(1, Types.INTEGER); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added user with id:" + cstmt.getInt(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Nurse> read() {
        String selectSql = "SELECT * FROM nurses";
        List<Nurse> nurses = new ArrayList<>();
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
                Nurse p=new Nurse(name,age,gender,id,exp);
                nurses.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurses;
    }

    public Nurse getNurseById(int id) {
        String selectSql = "SELECT * FROM nurses WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToNurse(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateNurse(String name, int age, Gender gender, int id, int exp) {
        String updateNameSql = "UPDATE nurses SET name=?, age=?, gender=?, experience=?" +
                " WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3,gender.toString());
            preparedStatement.setInt(4,exp);
            preparedStatement.setInt(5,id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Nurse mapToNurse(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Nurse(resultSet.getString(2), resultSet.getInt(3),
                    Gender.valueOf(resultSet.getString(4)), resultSet.getInt(1),
                    resultSet.getInt(5));
        }
        return null;
    }


    public void delete(int id, Connection connection) throws SQLException {
        System.out.println(id);
        String sql = "DELETE FROM nurses WHERE id=" + id;
        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);
    }
}
