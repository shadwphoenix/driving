package com.project.road;

import com.project.plates.PlatesEntity;
import com.project.services.CityServiceEntity;
import com.project.services.CityServicesServ;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoadServiceRepository implements AutoCloseable {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public RoadServiceRepository() throws SQLException {
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/driving_policy", "root", "Kasrasanati1382");
        connection.setAutoCommit(false);
    }

    public List<RoadServiceEntity> showRoadServices() throws SQLException {
        preparedStatement = connection.prepareStatement("""
                select * from road_service
                """);
        resultSet = preparedStatement.executeQuery();
        List<RoadServiceEntity> roadServiceEntities = new ArrayList<>();
        while (resultSet.next()){
            RoadServiceEntity roadService = new RoadServiceEntity();
            roadService.setRoadCode(resultSet.getInt("road_code"));
            roadService.setRoadCost(resultSet.getInt("road_cost"));
            roadService.setDesRoad(resultSet.getString("road_des"));
            roadServiceEntities.add(roadService);
        }
        return roadServiceEntities;
    }

    public void getRoadCost() throws SQLException{
        preparedStatement = connection.prepareStatement("""
                select road_cost from road_service
                where road_code = ?
                """);
        preparedStatement.setInt(1, RoadServiceService.getInstance().getRoadCode());
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            RoadServiceService.getInstance().setRoadCost(resultSet.getInt("service_cost"));
        }
    }

    public void insertRoadService(RoadServiceEntity roadServiceEntity) throws SQLException {
        preparedStatement = connection.prepareStatement("""
                insert into plates_has_road_service(plates_plate_no,plates_people_l_id,plates_cars_car_no,road_service_road_code)
                values(?,?,?,?);
                """);
        preparedStatement.setString(1,roadServiceEntity.getPlateNo());
        preparedStatement.setInt(2,roadServiceEntity.getPeopleId());
        preparedStatement.setInt(3,roadServiceEntity.getCarNumber());
        preparedStatement.setInt(4,roadServiceEntity.getRoadCode());
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
