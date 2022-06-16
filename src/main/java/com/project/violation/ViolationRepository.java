package com.project.violation;

import com.project.cards.CardEntity;
import com.project.cards.CardRepository;
import com.project.people.PeopleEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViolationRepository implements AutoCloseable{

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    //  connects to violations
    public ViolationRepository() throws SQLException {
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/driving_policy", "root", "Kasrasanati1382");
        connection.setAutoCommit(false);
    }

    //  inserts into database with prepared statement
    public void insertViolation(ViolationEntity violationEntity) throws SQLException{
        preparedStatement = connection.prepareStatement("""
                insert into violations (violation_id,violation_des,violation_point,police_police_id,
                plate_plate_no ,people_l_id, violation_charge ,violation_time ,violation_active )
                values(?,?,?,?,?,?,?,?,1);
                """);
        preparedStatement.setInt(1,violationEntity.getViolationId());
        preparedStatement.setString(2,violationEntity.getViolationDes());
        preparedStatement.setInt(3,violationEntity.getViolationPoint());
        preparedStatement.setInt(4,violationEntity.getPoliceIdViolation());
        preparedStatement.setString(5,violationEntity.getPlateNoViolation());
        preparedStatement.setInt(6,violationEntity.getPeopleIdViolation());
        preparedStatement.setInt(7,violationEntity.getViolationCharge());
        Timestamp timestamp = new Timestamp(violationEntity.getViolationDate().getTime());
        preparedStatement.setTimestamp(8,timestamp);
        preparedStatement.executeUpdate();
    }

    //  stores all the needed rows in a list of Entities
    public List<ViolationEntity> showViolations(ViolationEntity violationEntity) throws SQLException{
        preparedStatement = connection.prepareStatement("""
                select * from violations
                where plate_plate_no = ?
                and violation_active = 1;""");
        preparedStatement.setString(1,violationEntity.getPlateNoViolation());
        resultSet = preparedStatement.executeQuery();
        List<ViolationEntity> violationList = new ArrayList<>();
        while (resultSet.next()){
            ViolationEntity violation = new ViolationEntity();
            violation.setViolationId(resultSet.getInt("violation_id"));
            violation.setViolationDes(resultSet.getString("violation_des"));
            violation.setViolationPoint(resultSet.getInt("violation_point"));
            violation.setPoliceIdViolation(resultSet.getInt("police_police_id"));
            violation.setPeopleIdViolation(resultSet.getInt("people_l_id"));
            violation.setViolationCharge(resultSet.getInt("violation_charge"));
            violation.setViolationDate(resultSet.getTimestamp("violation_time"));
            violationList.add(violation);
        }
        return violationList;
    }

    //  adds up the violation_point colum using sql queries
    public void showLicensePoints(ViolationEntity violationEntity) throws SQLException{
        preparedStatement = connection.prepareStatement("""
                select SUM(violation_point)
                from violations
                where violations.people_l_id = ? and violations.violation_active = 1;""");

        preparedStatement.setString(1,String.valueOf(violationEntity.getPeopleIdViolation()));
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            violationEntity.setViolationPoint(resultSet.getInt(1));
        }
    }
    //  get the charge for disabling a violation
    public void getViolationCharge(ViolationEntity violationEntity) throws SQLException{
        preparedStatement = connection.prepareStatement("""
                select violation_charge from violations
                where violation_id = ?;
                """);
        preparedStatement.setInt(1,violationEntity.getViolationId());
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            violationEntity.setViolationCharge(resultSet.getInt("violation_charge"));
        }
    }
    //  disables a violation after getting the payment
    public void clearViolation(ViolationEntity violationEntity) throws SQLException{
        getViolationCharge(violationEntity);
        preparedStatement = connection.prepareStatement("""
            update violations
            set violation_active = 0
            where violation_id = ?;
                """);
            preparedStatement.setInt(1,violationEntity.getViolationId());
            preparedStatement.executeUpdate();
    }

    //checks to see if a plate is valid
    public boolean checkPlate(ViolationEntity violationEntity) throws SQLException {
        preparedStatement = connection.prepareStatement("""
                select * from plates
                where plate_no = ?
                """);
        preparedStatement.setString(1,violationEntity.getPlateNoViolation());
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String plate = resultSet.getString("plate_no");
            if(plate != null){
                return true;
            }
        }
        return false;
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    //  close the connection
    @Override
    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}
