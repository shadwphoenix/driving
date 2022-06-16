package com.project.accident;

import com.project.cars.CarEntity;
import com.project.police.PoliceEntity;
import com.project.violation.ViolationEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccidentRepository implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public AccidentRepository() throws SQLException {
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/driving_policy", "root", "Kasrasanati1382");
        connection.setAutoCommit(false);
    }

    public void insertAccidents(AccidentEntity accidentEntity) throws SQLException {
        preparedStatement = connection.prepareStatement("""
                insert into accident (police_police_id,cars_car_no,accident_description,accident_time)
                values (?,?,?,?)
                """);
        preparedStatement.setInt(1,accidentEntity.getAccidentPoliceId());
        preparedStatement.setInt(2,accidentEntity.getCarNo());
        preparedStatement.setString(3,accidentEntity.getAccidentDes());
        Timestamp timestamp = new Timestamp(accidentEntity.getAccidentDate().getTime());
        preparedStatement.setTimestamp(4,timestamp);
        preparedStatement.executeUpdate();
    }

    public List<AccidentEntity> showAccidents(AccidentEntity accidentEntity) throws SQLException{
        preparedStatement = connection.prepareStatement("""
                select * from accident
                where cars_car_no = ?;
                """);
        preparedStatement.setInt(1,accidentEntity.getCarNo());
        resultSet = preparedStatement.executeQuery();
        List<AccidentEntity> accidentEntities = new ArrayList<>();
        while (resultSet.next()){
            AccidentEntity accident = new AccidentEntity();
            accident.setAccidentId(resultSet.getInt("accident_Id"));
            accident.setAccidentDes(resultSet.getString("accident_description"));
            accident.setAccidentDate(resultSet.getTimestamp("accident_time"));
            accident.setAccidentPoliceId(resultSet.getInt("police_police_id"));
            accidentEntities.add(accident);
        }
        return accidentEntities;
    }

    public boolean checkCarNo(AccidentEntity accidentEntity) throws SQLException {
        preparedStatement = connection.prepareStatement("""
                select * from cars
                where car_no = ?
                """);
        preparedStatement.setInt(1,accidentEntity.getCarNo());
        resultSet = preparedStatement.executeQuery();
        if(!resultSet.isBeforeFirst()){
            System.out.println("no data");
            return false;
        }
        return true;
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
