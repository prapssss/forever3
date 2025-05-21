package com.forever3.controller;

import com.forever3.config.DbConfig;
import com.forever3.model.ProductModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/makeup")
public class MakeupController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Correct category ID for Makeup (assuming 1 is Makeup)
    private static final int MAKEUP_CATEGORY_ID = 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<ProductModel> makeupProducts = new ArrayList<>();

        String sql = "SELECT i.item_id, i.item_name, i.description, i.category_id, c.category_name, " +
                     "i.item_price, i.stock_quantity, i.supplier_id, s.supplier_name, i.image_url " +
                     "FROM item i " +
                     "JOIN category c ON i.category_id = c.category_id " +
                     "JOIN supplier s ON i.supplier_id = s.supplier_id " +
                     "WHERE i.category_id = ?";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, MAKEUP_CATEGORY_ID);
            ResultSet rs = stmt.executeQuery();

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

                makeupProducts.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("productList", makeupProducts);
        request.setAttribute("activePage", "makeup");
        request.getRequestDispatcher("/WEB-INF/pages/makeup.jsp").forward(request, response);
    }
}
