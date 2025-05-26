package com.forever3.controller.admin;

import com.forever3.model.ProductModel;
import com.forever3.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
/**
 * @author Prapti Adhikari
 */
@WebServlet("/addstock")
public class AddStockController extends HttpServlet {
	// Service layer to interact with product data
    private ProductService productService = new ProductService();

    /**
     * Handles GET requests: Shows the add stock form with existing product details.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Parse product ID from request
            int itemId = Integer.parseInt(request.getParameter("id"));

            // Fetch product details using the ID
            ProductModel product = productService.getProductById(itemId);

            // Set product object as a request attribute and forward to JSP
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/pages/admin/addstock.jsp").forward(request, response);

        } catch (Exception e) {
            // Log error and return 400 Bad Request for invalid or missing product ID
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID.");
        }
    }

    /**
     * Handles POST requests: Updates stock quantity for a product.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Set request encoding to handle UTF-8 characters
            request.setCharacterEncoding("UTF-8");

            // Retrieve form parameters for item ID and additional stock
            String itemIdStr = request.getParameter("itemId");
            String stockStr = request.getParameter("additionalStock");

            // Log received values for debugging
            System.out.println("Received itemId: " + itemIdStr);
            System.out.println("Received additionalStock: " + stockStr);

            // Convert parameters to integers
            int itemId = Integer.parseInt(itemIdStr);
            int additionalStock = Integer.parseInt(stockStr);

            // Validate that the additional stock is greater than zero
            if (additionalStock <= 0) {
                ProductModel product = productService.getProductById(itemId);
                request.setAttribute("product", product);
                request.setAttribute("errorMessage", "Stock must be greater than zero.");
                request.getRequestDispatcher("/WEB-INF/pages/admin/addstock.jsp").forward(request, response);
                return;
            }

            // Attempt to update stock through the service layer
            boolean updated = productService.addStock(itemId, additionalStock);

            if (updated) {
                // If successful, redirect to the product management page
                response.sendRedirect("productmanagement");
            } else {
                // If update fails, reload form with error message
                ProductModel product = productService.getProductById(itemId);
                request.setAttribute("product", product);
                request.setAttribute("errorMessage", "Failed to update stock.");
                request.getRequestDispatcher("/WEB-INF/pages/admin/addstock.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // Handle any errors during parsing or service interaction
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid input or server error.");
            request.getRequestDispatcher("/WEB-INF/pages/admin/addstock.jsp").forward(request, response);
        }
    }
}