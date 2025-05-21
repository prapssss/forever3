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

@WebServlet("/addToCart")
public class AddToCartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");
        
        System.out.println("AddToCart - Customer ID: " + customerId);
        
        if (customerId == null) {
            System.out.println("AddToCart - No customer ID found, redirecting to login");
            session.setAttribute("errorMessage", "Please login to add items to cart");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }

        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        System.out.println("AddToCart - Adding item: " + itemId + ", quantity: " + quantity);

        try (Connection conn = DbConfig.getDbConnection()) {
            conn.setAutoCommit(false);
            try {
                // Check if enough stock is available
                if (!checkStockAvailability(conn, itemId, quantity)) {
                    System.out.println("AddToCart - Not enough stock available");
                    session.setAttribute("errorMessage", "Not enough stock available");
                    response.sendRedirect(request.getHeader("referer"));
                    return;
                }

                // Update or insert cart item
                updateCartItem(conn, customerId, itemId, quantity);
                System.out.println("AddToCart - Cart item updated/inserted");

                // Update stock quantity
                updateStockQuantity(conn, itemId, quantity);
                System.out.println("AddToCart - Stock quantity updated");

                conn.commit();
                System.out.println("AddToCart - Transaction committed");

                // Update session cart
                List<CartModel> cart = getCartItems(conn, customerId);
                session.setAttribute("cart", cart);
                session.setAttribute("cartMessage", "Item added to cart successfully!");
                System.out.println("AddToCart - Cart updated in session, size: " + cart.size());

            } catch (Exception e) {
                System.out.println("AddToCart - Error in transaction: " + e.getMessage());
                e.printStackTrace();
                conn.rollback();
                throw e;
            }
        } catch (Exception e) {
            System.out.println("AddToCart - Error: " + e.getMessage());
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error adding item to cart: " + e.getMessage());
        }

        // Redirect to cart page
        System.out.println("AddToCart - Redirecting to cart page");
        response.sendRedirect(request.getContextPath() + "/viewCart");
    }

    private boolean checkStockAvailability(Connection conn, int itemId, int quantity) throws SQLException {
        String sql = "SELECT stock_quantity FROM item WHERE item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int availableStock = rs.getInt("stock_quantity");
                System.out.println("AddToCart - Available stock: " + availableStock + ", requested: " + quantity);
                return availableStock >= quantity;
            }
        }
        return false;
    }

    private void updateCartItem(Connection conn, int customerId, int itemId, int quantity) throws SQLException {
        // Check if item already in cart
        String checkSql = "SELECT quantity FROM cart WHERE customer_id = ? AND item_id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, customerId);
            checkStmt.setInt(2, itemId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Update existing cart item
                String updateSql = "UPDATE cart SET quantity = quantity + ? WHERE customer_id = ? AND item_id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, quantity);
                    updateStmt.setInt(2, customerId);
                    updateStmt.setInt(3, itemId);
                    int updated = updateStmt.executeUpdate();
                    System.out.println("AddToCart - Updated existing cart item, rows affected: " + updated);
                }
            } else {
                // Insert new cart item
                String insertSql = "INSERT INTO cart (customer_id, item_id, quantity) VALUES (?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, customerId);
                    insertStmt.setInt(2, itemId);
                    insertStmt.setInt(3, quantity);
                    int inserted = insertStmt.executeUpdate();
                    System.out.println("AddToCart - Inserted new cart item, rows affected: " + inserted);
                }
            }
        }
    }

    private void updateStockQuantity(Connection conn, int itemId, int quantity) throws SQLException {
        String sql = "UPDATE item SET stock_quantity = stock_quantity - ? WHERE item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, itemId);
            int updated = stmt.executeUpdate();
            System.out.println("AddToCart - Updated stock quantity, rows affected: " + updated);
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
                System.out.println("AddToCart - Retrieved cart item: " + item.getItemName() + ", quantity: " + item.getQuantity());
            }
        }
        return cart;
    }
}
