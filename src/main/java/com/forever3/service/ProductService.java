package com.forever3.service;

import com.forever3.model.ProductModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/forever3";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = ""; // Change if needed

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Add new product
    public void addProduct(ProductModel product) throws SQLException {
        String sql = "INSERT INTO item (item_name, description, category_id, item_price, stock_quantity, supplier_id, image_url) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getItemName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getCategoryId());
            stmt.setDouble(4, product.getItemPrice());
            stmt.setInt(5, product.getStockQuantity());
            stmt.setInt(6, product.getSupplierId());
            stmt.setString(7, product.getImageUrl());
            stmt.executeUpdate();
        }
    }

    // Delete product by ID
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM item WHERE item_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add stock to a product
    public boolean addStock(int itemId, int additionalStock) throws SQLException {
        String sql = "UPDATE item SET stock_quantity = stock_quantity + ? WHERE item_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, additionalStock);
            ps.setInt(2, itemId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Get all products
    public List<ProductModel> getAllProducts() {
        List<ProductModel> products = new ArrayList<>();
        String sql = "SELECT i.item_id, i.item_name, i.description, i.category_id, c.category_name, "
                   + "i.item_price, i.stock_quantity, i.supplier_id, s.supplier_name, i.image_url "
                   + "FROM item i "
                   + "JOIN category c ON i.category_id = c.category_id "
                   + "JOIN supplier s ON i.supplier_id = s.supplier_id";

        try (Connection conn = getConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // Get single product by ID
    public ProductModel getProductById(int id) {
        ProductModel product = null;
        String sql = "SELECT * FROM item WHERE item_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new ProductModel();
                product.setItemId(rs.getInt("item_id"));
                product.setItemName(rs.getString("item_name"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setItemPrice(rs.getDouble("item_price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setSupplierId(rs.getInt("supplier_id"));
                product.setImageUrl(rs.getString("image_url"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    // Update existing product
    public void updateProduct(ProductModel product) throws SQLException {
        String sql = "UPDATE item SET item_name = ?, description = ?, category_id = ?, item_price = ?, "
                   + "stock_quantity = ?, supplier_id = ?, image_url = ? WHERE item_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getItemName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getCategoryId());
            stmt.setDouble(4, product.getItemPrice());
            stmt.setInt(5, product.getStockQuantity());
            stmt.setInt(6, product.getSupplierId());
            stmt.setString(7, product.getImageUrl());
            stmt.setInt(8, product.getItemId());

            stmt.executeUpdate();
        }
    }

    // Get products by category ID (for skincare, makeup, etc.)
    public List<ProductModel> getProductsByCategoryId(int categoryId) {
        List<ProductModel> products = new ArrayList<>();
        String sql = "SELECT i.item_id, i.item_name, i.description, i.category_id, c.category_name, "
                   + "i.item_price, i.stock_quantity, i.supplier_id, s.supplier_name, i.image_url "
                   + "FROM item i "
                   + "JOIN category c ON i.category_id = c.category_id "
                   + "JOIN supplier s ON i.supplier_id = s.supplier_id "
                   + "WHERE i.category_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, categoryId);

            try (ResultSet rs = stmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
