package com.project.police;

import com.project.people.PeopleRepository;

import java.sql.*;

public class PoliceRepository implements AutoCloseable{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PoliceRepository() throws SQLException{
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/driving_policy", "root", "Kasrasanati1382");
        connection.setAutoCommit(false);
    }

    public void findPolice(PoliceEntity policeEntity) throws SQLException{
        preparedStatement = connection.prepareStatement("""
                select * from police
                where police_id = ?
                """);
        preparedStatement.setInt(1,policeEntity.getPoliceId());
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            policeEntity.setPoliceFirstName(resultSet.getString("f_name"));
            policeEntity.setPoliceLastName(resultSet.getString("l_name"));
            policeEntity.setPolicePass(resultSet.getString("pass"));
            policeEntity.setLastLoginDate(resultSet.getDate("last_login"));
        }
    }

   /* public void updateLastLoginPolice(){

    }
    */

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
