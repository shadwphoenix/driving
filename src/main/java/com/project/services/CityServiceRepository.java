package com.project.services;

import com.project.plates.PlatesEntity;
import com.project.road.RoadServiceEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityServiceRepository implements AutoCloseable {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CityServiceRepository() throws SQLException {
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/driving_policy", "root", "Kasrasanati1382");
        connection.setAutoCommit(false);
    }


    public List<CityServiceEntity> showCity() throws SQLException {
        preparedStatement = connection.prepareStatement("""
                select * from city_service
                """);
        resultSet = preparedStatement.executeQuery();
        List<CityServiceEntity> roadServiceEntities = new ArrayList<>();
        while (resultSet.next()){
            CityServiceEntity cityServiceEntity = new CityServiceEntity();
            cityServiceEntity.setServiceCode(resultSet.getInt("service_code"));
            cityServiceEntity.setServiceCost(resultSet.getInt("service_cost"));
            cityServiceEntity.setServiceDes(resultSet.getString("service_des"));
            roadServiceEntities.add(cityServiceEntity);
        }
        return roadServiceEntities;
    }

    public void getCityCost() throws SQLException{
        preparedStatement = connection.prepareStatement("""
                select service_cost from city_service
                where service_code = ?
                """);
        preparedStatement.setInt(1,CityServicesServ.getInstance().getCityCode());
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            CityServicesServ.getInstance().setCityCost(resultSet.getInt("service_cost"));
        }
    }

    public void insertCityService(CityServiceEntity cityServiceEntity) throws SQLException {
        preparedStatement = connection.prepareStatement("""
                insert into plates_has_city_service (plates_plate_no,plates_people_l_id,plates_cars_car_no,city_service_service_code)
                values(?,?,?,?);
                """);
        preparedStatement.setString(1,cityServiceEntity.getPlateNo());
        preparedStatement.setInt(2,cityServiceEntity.getPeopleId());
        preparedStatement.setInt(3,cityServiceEntity.getCarNo());
        preparedStatement.setInt(4,cityServiceEntity.getServiceCode());
        preparedStatement.executeUpdate();
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
