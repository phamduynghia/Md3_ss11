package com.ra.controller;

import com.ra.dao.ProductDAO;
import com.ra.dao.impl.ProductDAOImpl;
import com.ra.model.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Bai1" , value = "/bai1")
public class Bai1Servlet extends HttpServlet {
    ProductDAO productDAO = new ProductDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String parameter = req.getParameter("action");
        if(parameter == null){
            parameter = "" ;
        }
        switch (parameter){
            case "update" : {
                int id = Integer.parseInt(req.getParameter("id"));
                Product product = productDAO.findProductById(id);
                req.setAttribute("product",product);
                req.getRequestDispatcher("/views/bai1_update.jsp").forward(req,resp);
                break;
            }
            case "create" : {
                req.getRequestDispatcher("/views/bai1_create.jsp").forward(req,resp);
                break;
            }
            case "delete" : {
                int id = Integer.parseInt(req.getParameter("id"));
                productDAO.deleteProduct(id);
                showProduct(req,resp);
            }
            default: {
                showProduct(req, resp);
            }
        }

    }

    private void showProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDAO.getListProduct();
        req.setAttribute("products" , products);
        req.getRequestDispatcher("/views/bai1_display.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        if (action == null){
            action = "" ;
        }
        switch (action){
            case "create" : {
                Product product = new Product();
                product.setName(req.getParameter("name"));
                product.setPrice(Double.parseDouble(req.getParameter("price")));
                product.setProducer(req.getParameter("producer"));
                product.setDescription(req.getParameter("description"));
                productDAO.addProduct(product);
                showProduct(req, resp);
                break;
            }
            case "update" : {
                int id = Integer.parseInt(req.getParameter("id"));
                Product product = new Product();
                product.setId(id);
                product.setName(req.getParameter("name"));
                product.setPrice(Double.parseDouble(req.getParameter("price")));
                product.setProducer(req.getParameter("producer"));
                product.setDescription(req.getParameter("description"));
                product.setStatus(Boolean.parseBoolean(req.getParameter("status")));
                boolean rs = productDAO.updateProduct(product);
                if(rs){
                    showProduct(req,resp);
                }else {
                    req.setAttribute("error","update error !");
                    req.getRequestDispatcher("views/bai1_update.jsp").forward(req,resp);
                }
                break;
            }
            default: {
                doGet(req,resp);

            }
        }
    }
}