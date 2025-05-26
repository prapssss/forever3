package com.forever3.controller.admin;

import com.forever3.model.ProductModel;
import com.forever3.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
/**
 * @author Prapti Adhikari
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/productmanagement"})
public class ProductManagementController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Service layer for interacting with product data
    private ProductService productService = new ProductService();

    /**
     * Handles GET requests to show the product management page with a list of all products.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve all products from the service
        List<ProductModel> products = productService.getAllProducts();

        // Set products and active page for the view
        request.setAttribute("products", products);
        request.setAttribute("activePage", "productmanagement"); // Used for highlighting nav/menu

        // Forward the request to the JSP view
        request.getRequestDispatcher("/WEB-INF/pages/admin/productmanagement.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to perform actions like deleting a product.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the action to be performed (e.g., delete)
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            try {
                // Parse product ID from request
                int id = Integer.parseInt(request.getParameter("id"));

                // Attempt to delete the product via the service
                boolean deleted = productService.deleteProduct(id);

                // Log if deletion failed (e.g., product not found)
                if (!deleted) {
                    System.out.println("Product not found or could not be deleted for ID: " + id);
                }

            } catch (NumberFormatException e) {
                // Handle invalid ID format
                e.printStackTrace();
            }
        }

        // Redirect back to the product management page to refresh the list
        response.sendRedirect("productmanagement");
    }
}