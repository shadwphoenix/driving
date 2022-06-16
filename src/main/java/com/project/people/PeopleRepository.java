package com.project.people;

import java.sql.*;

public class PeopleRepository implements AutoCloseable{

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PeopleRepository() throws SQLException {
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/driving_policy", "root", "Kasrasanati1382");
        connection.setAutoCommit(false);
    }

    public void findUser(PeopleEntity peopleEntity) throws SQLException {
        preparedStatement = connection.prepareStatement("""
                select * from people
                where l_id = ?
                """);
        preparedStatement.setInt(1,peopleEntity.getLicenseId());
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            peopleEntity.setFirstName(resultSet.getString("f_name"));
            peopleEntity.setLastName(resultSet.getString("l_name"));
            peopleEntity.setBirthday(resultSet.getDate("birth_date"));
            peopleEntity.setSex(resultSet.getString("sex"));
            peopleEntity.setLastLogin(resultSet.getTimestamp("last_login"));
        }

    }

    /*public void updateLogin(PeopleEntity peopleEntity) throws SQLException{
        preparedStatement = connection.prepareStatement("""
                u
                """)
    }
*/

    public void newUser(PeopleEntity peopleEntity) throws SQLException{
        preparedStatement = connection.prepareStatement("""
                """);
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
