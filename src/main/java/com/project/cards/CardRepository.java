package com.project.cards;

import java.sql.*;

public class CardRepository implements AutoCloseable {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CardRepository() throws SQLException {
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/driving_policy", "root", "Kasrasanati1382");
        connection.setAutoCommit(false);
    }

    public void getInfoCard(CardEntity cardEntity) throws SQLException {
        preparedStatement = connection.prepareStatement("""
                select * from cards
                where card_no = ?
                """);
        preparedStatement.setInt(1,cardEntity.getCardNo());
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            cardEntity.setCardBalance(resultSet.getInt("balance"));
            cardEntity.setCardPass(resultSet.getInt("password"));
            cardEntity.setCardReal(resultSet.getString("card_real"));
        }
    }


    public void updateCardInfo(CardEntity cardEntity) throws SQLException{
        preparedStatement = connection.prepareStatement("""
                update cards
                set balance = ?
                where card_no = ?;
                """);
        preparedStatement.setInt(1,cardEntity.getCardBalance());
        preparedStatement.setInt(2,cardEntity.getCardNo());
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
