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

@WebServlet("/removeFromCart")
public class RemoveFromCartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        try (Connection conn = DbConfig.getDbConnection()) {
            conn.setAutoCommit(false);
            try {
                // Get the quantity before removing
                int quantity = getCartItemQuantity(conn, customerId, itemId);

                // Remove from cart
                removeFromCart(conn, customerId, itemId);

                // Restore stock
                restoreStock(conn, itemId, quantity);

                conn.commit();

                // Update session cart
                List<CartModel> cart = getCartItems(conn, customerId);
                session.setAttribute("cart", cart);
                session.setAttribute("cartMessage", "Item removed from cart successfully!");

            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error removing item from cart");
        }

        response.sendRedirect(request.getHeader("referer"));
    }

    private int getCartItemQuantity(Connection conn, int customerId, int itemId) throws SQLException {
        String sql = "SELECT quantity FROM cart WHERE customer_id = ? AND item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
        }
        return 0;
    }

    private void removeFromCart(Connection conn, int customerId, int itemId) throws SQLException {
        String sql = "DELETE FROM cart WHERE customer_id = ? AND item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, itemId);
            stmt.executeUpdate();
        }
    }

    private void restoreStock(Connection conn, int itemId, int quantity) throws SQLException {
        String sql = "UPDATE item SET stock_quantity = stock_quantity + ? WHERE item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, itemId);
            stmt.executeUpdate();
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
            }
        }
        return cart;
    }
}
