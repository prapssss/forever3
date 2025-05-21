package com.forever3.controller;

import com.forever3.model.CartModel;
import com.forever3.config.DbConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/viewCart")
public class ViewCartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");
        
        System.out.println("ViewCart - Customer ID: " + customerId);

        if (customerId == null) {
            System.out.println("ViewCart - No customer ID found, redirecting to login");
            session.setAttribute("errorMessage", "Please login to view your cart");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try (Connection conn = DbConfig.getDbConnection()) {
            List<CartModel> cart = getCartItems(conn, customerId);
            System.out.println("ViewCart - Retrieved " + cart.size() + " items from cart");
            
            session.setAttribute("cart", cart); // Update session cart
            request.setAttribute("cart", cart);
            request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("ViewCart - Error: " + e.getMessage());
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error loading cart: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/viewCart");
        }
    }

    private List<CartModel> getCartItems(Connection conn, int customerId) throws SQLException {
        List<CartModel> cart = new ArrayList<>();
        String sql = "SELECT c.*, i.item_name, i.item_price, i.image_url " +
                    "FROM cart c " +
                    "JOIN item i ON c.item_id = i.item_id " +
                    "WHERE c.customer_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                CartModel item = new CartModel();
                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setItemPrice(rs.getDouble("item_price"));
                item.setQuantity(rs.getInt("quantity"));
                item.setImageUrl(rs.getString("image_url"));
                cart.add(item);
                System.out.println("ViewCart - Retrieved item: " + item.getItemName() + ", quantity: " + item.getQuantity());
            }
        }
        return cart;
    }
}
