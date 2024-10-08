package com.ra.dao.impl;

import com.ra.connectDB.Database;
import com.ra.dao.ProductDAO;
import com.ra.model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> getListProduct() {
        List<Product> products = new ArrayList<>();
        String query = "select * from products";
        try (Connection connection = Database.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setProducer(rs.getString("producer"));
                product.setDescription(rs.getString("description"));
                product.setStatus(rs.getBoolean("status"));
                products.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        String query = "insert into products (name,price,producer,description) values (?,?,?,?);";
        try (Connection connection = Database.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getProducer());
            preparedStatement.setString(4,product.getDescription());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false ;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        String query = "call proc_update_product(?,?,?,?,?,?);";
        try (Connection connection = Database.getConnect();
             CallableStatement statement = connection.prepareCall(query);
        ){
            statement.setString(1,product.getName());
            statement.setDouble(2,product.getPrice());
            statement.setString(3,product.getProducer());
            statement.setString(4,product.getDescription());
            statement.setBoolean(5,product.getStatus());
            statement.setInt(6,product.getId());
            statement.executeUpdate();
            return true ;
        }catch (SQLException e){
            e.printStackTrace();
            return false ;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        String query = "call proc_delete_product(?)";
        try (Connection connection = Database.getConnect();
             CallableStatement statement = connection.prepareCall(query);
        ){
            statement.setInt(1,id);
            statement.executeUpdate();
            return true ;
        }catch (SQLException e){
            e.printStackTrace();
            return false ;
        }
    }

    @Override
    public Product findProductById(int id) {
        String query = "select * from products where id = ? limit 1 ;";
        Product product = new Product();
        try (Connection connection = Database.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setProducer(rs.getString("producer"));
                product.setDescription(rs.getString("description"));
                product.setStatus(rs.getBoolean("status"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product ;
    }
}