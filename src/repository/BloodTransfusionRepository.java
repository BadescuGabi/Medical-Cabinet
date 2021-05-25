package repository;

import appointment.actions.BloodTransfusion;
import config.DatabaseConfiguration;
import person.enums_and_salary.Gender;
import person.enums_and_salary.Specialization;
import person.type.Doctor;
import person.type.Nurse;
import person.type.Patient;


import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BloodTransfusionRepository {

    public void addBloodTransfusion(BloodTransfusion bloodTransfusion) {
        String preparedSql = "{call addBloodTransfusion(?,?,?,?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, bloodTransfusion.getDate());
            cstmt.setInt(3, bloodTransfusion.getPatientId());
            cstmt.setInt(4, bloodTransfusion.getDoctorId());
            cstmt.setInt(5, bloodTransfusion.getDuration());
            cstmt.setInt(6, bloodTransfusion.getNurseId());
            cstmt.registerOutParameter(1, Types.INTEGER); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added user with id:" + cstmt.getInt(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<BloodTransfusion> read() {
        String selectSql = "SELECT * FROM appointments";
        PatientRepository patientRepository = new PatientRepository();
        DoctorRepository doctorRepository = new DoctorRepository();
        NurseRepository nurseRepository = new NurseRepository();
        List<BloodTransfusion> bloodTransfusions = new ArrayList<>();
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String date = resultSet.getString(2);
                int patientId = resultSet.getInt(3);
                int doctorId = resultSet.getInt(4);
                Patient patient = patientRepository.getPatientById(patientId);
                Doctor doctor = doctorRepository.getDoctorById(doctorId);
                int duration = resultSet.getInt(5);
                int nurseId = resultSet.getInt(6);
                Nurse nurse = nurseRepository.getNurseById(nurseId);
                BloodTransfusion bloodTransfusion = new BloodTransfusion(date, patientId, doctorId, duration, nurseId, id);
                bloodTransfusions.add(bloodTransfusion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloodTransfusions;
    }

    public BloodTransfusion getBloodTransfusionById(int id) {
        String selectSql = "SELECT * FROM appointments WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToBloodTransfusion(resultSet);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateBloodTransfusion(String date,int patientId,int doctorId,int duration,int nurseId,int id) {
        String updateNameSql = "UPDATE appointments SET date1=?, patientId=?, doctorId=?, duration=?, nurseId=?" +
                " WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, patientId);
            preparedStatement.setInt(3, doctorId);
            preparedStatement.setInt(4, duration);
            preparedStatement.setInt(5, nurseId);
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private BloodTransfusion mapToBloodTransfusion(ResultSet resultSet) throws SQLException, ParseException {
        if (resultSet.next()) {
            return new BloodTransfusion(resultSet.getString(2), resultSet.getInt(3),
                    resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),resultSet.getInt(1));
        }
        return null;
    }


    public void delete(int id, Connection connection) throws SQLException {
        System.out.println(id);
        String sql = "DELETE FROM appointments WHERE id=" + id;
        Statement statement = connection.createStatement();

        int rows = statement.executeUpdate(sql);
    }
}
