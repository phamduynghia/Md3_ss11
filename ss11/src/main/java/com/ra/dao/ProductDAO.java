package com.ra.dao;

import com.ra.model.entity.Product;

import java.util.List;

public interface ProductDAO {
    public List<Product> getListProduct();
    public boolean addProduct(Product product);
    public boolean updateProduct(Product product);
    public boolean deleteProduct(int id);
    public Product findProductById(int id);
}