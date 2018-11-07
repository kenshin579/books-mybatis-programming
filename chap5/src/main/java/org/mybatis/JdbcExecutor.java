package org.mybatis;

import org.mybatis.domain.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcExecutor {
    private static final Logger log = LoggerFactory.getLogger(JdbcExecutor.class);

    public static void main(String[] args) {
        int shopNo = 1;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "mybatis", "mybatis$");

            preparedStatement = connection.prepareStatement("SELECT * FROM SHOP WHERE SHOP_NO = ?");

            preparedStatement.setInt(1, shopNo);

            resultSet = preparedStatement.executeQuery();

            Shop shop = null;
            while (resultSet.next()) {
                shop = new Shop();
                shop.setShopNo(resultSet.getInt("SHOP_NO"));
                shop.setShopName(resultSet.getString("SHOP_NAME"));
                shop.setShopLocation(resultSet.getString("SHOP_LOCATION"));
                shop.setShopStatus(resultSet.getString("SHOP_STATUS"));
                log.debug(shop.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
