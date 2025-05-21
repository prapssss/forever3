package com.forever3.service;

import com.forever3.config.DbConfig;
import com.forever3.model.OrderModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
	private static final String SELECT_ALL_ORDERS = 
		    "SELECT o.order_id, i.item_name AS product_name, " +
		    "CONCAT(c.first_name, ' ', c.last_name) AS customer_name, " +
		    "o.order_date, o.total_price, o.payment_status AS order_type " +
		    "FROM orders o " +
		    "JOIN order_items oi ON o.order_id = oi.order_id " +
		    "JOIN item i ON oi.item_id = i.item_id " +
		    "JOIN customer c ON o.customer_id = c.id";



    public List<OrderModel> getAllOrders() {
        List<OrderModel> orders = new ArrayList<>();

        try (Connection connection = DbConfig.getDbConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORDERS);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                OrderModel order = new OrderModel();
                order.setOrderId(rs.getInt("order_id"));
                order.setProductName(rs.getString("product_name"));
                order.setCustomerName(rs.getString("customer_name"));
                order.setOrderDate(rs.getString("order_date"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setOrderType(rs.getString("order_type"));

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }
}
