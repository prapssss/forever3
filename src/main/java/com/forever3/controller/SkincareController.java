package com.forever3.controller;

import com.forever3.model.ProductModel;
import com.forever3.config.DbConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = { "/skincare" })
public class SkincareController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SkincareController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ProductModel> productList = getSkincareProducts();
        request.setAttribute("productList", productList);
        request.setAttribute("activePage", "skincare");
        request.getRequestDispatcher("/WEB-INF/pages/skincare.jsp").forward(request, response);
    }

    private List<ProductModel> getSkincareProducts() {
        List<ProductModel> products = new ArrayList<>();
        String sql = "SELECT i.item_id, i.item_name, i.description, i.category_id, c.category_name, " +
                     "i.item_price, i.stock_quantity, i.supplier_id, s.supplier_name, i.image_url " +
                     "FROM item i " +
                     "JOIN category c ON i.category_id = c.category_id " +
                     "JOIN supplier s ON i.supplier_id = s.supplier_id " +
                     "WHERE i.category_id = 2";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setItemId(rs.getInt("item_id"));
                product.setItemName(rs.getString("item_name"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setItemPrice(rs.getDouble("item_price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setSupplierId(rs.getInt("supplier_id"));
                product.setSupplierName(rs.getString("supplier_name"));
                product.setImageUrl(rs.getString("image_url"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
