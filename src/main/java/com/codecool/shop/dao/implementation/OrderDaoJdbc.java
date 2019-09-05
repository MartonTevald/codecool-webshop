package com.codecool.shop.dao.implementation;


import com.codecool.shop.database.DatabaseConnection;
import com.codecool.shop.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class OrderDaoJdbc extends DatabaseConnection {

    private ProductCategoryDaoJdbc prodCatJdbc = new ProductCategoryDaoJdbc();
    private SupplierDaoJdbc suppJdbc = new SupplierDaoJdbc();


    public void addOrder(int id, String status) {
        String query = "INSERT INTO \"order\" (user_id,status)" +
                "VALUES (?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, status);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrderItem(int prod_id, int quantity, int order_id) {
        String query = "INSERT INTO order_details (prod_id, quantity, order_id)" +
                "VALUES (?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, prod_id);
            statement.setInt(2, quantity);
            statement.setInt(3, order_id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean isThereAnOpenOrder(String status, int id) {
        String query = "SELECT * FROM \"order\" " +
                "WHERE status = ? AND user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateOrderStatus(int id, String status, String actualStatus) {
        String query = "UPDATE \"order\"" +
                "SET status = ?" +
                "WHERE user_id = ? AND  status = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status);
            statement.setInt(2, id);
            statement.setString(3, actualStatus);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void remove(int id) {
        //TODO

    }


    public HashMap<Product, Integer> getAllOrderItems(int orderID) {

        HashMap<Product, Integer> orders = new HashMap<>();

        String query = "SELECT product.id, name, description, price, supplier_id, prodcat_id,quantity FROM product " +
                "JOIN order_details on product.id = order_details.prod_id" +
                " JOIN \"order\" ON order_details.order_id = \"order\".id " +
                "WHERE \"order\".id = ? ";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderID);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Product product;
                product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        "USD",
                        resultSet.getString("description"),
                        prodCatJdbc.find(resultSet.getInt("prodcat_id")),
                        suppJdbc.find(resultSet.getInt("supplier_id")));

                int quantity = resultSet.getInt("quantity");

                orders.put(product, quantity);
                return orders;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getOrderIdByUserId(int user_id, String status) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement("" +
                    "SELECT id FROM \"order\" WHERE user_id = ? AND status = ?");
            stmt.setInt(1, user_id);
            stmt.setString(2, status);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int getOrderIdByStatusAndUserID(int userId, String status) {

        String query = "SELECT id FROM \"order\" WHERE  user_id = ? AND status = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setString(2, status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}