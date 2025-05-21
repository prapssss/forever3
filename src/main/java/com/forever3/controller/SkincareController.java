package com.forever3.controller;

import com.forever3.model.CartModel;
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

/**
 * Servlet implementation class ContactUsController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/skincare" })
public class SkincareController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkincareController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
        List<CartModel> productList = getSkincareProducts();
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/WEB-INF/pages/skincare.jsp").forward(request, response);
	}

    private List<CartModel> getSkincareProducts() {
        List<CartModel> products = new ArrayList<>();
        String sql = "SELECT * FROM item WHERE category_id = 2";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CartModel product = new CartModel();
                product.setItemId(rs.getInt("item_id"));
                product.setItemName(rs.getString("item_name"));
                product.setItemPrice(rs.getDouble("item_price"));
                product.setImageUrl(rs.getString("image_url"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}