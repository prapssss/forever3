package com.forever3.controller.admin;

import com.forever3.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/deleteproduct" })
public class DeleteProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductService productService = new ProductService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                boolean deleted = productService.deleteProduct(id);
                if (deleted) {
                    System.out.println("Deleted product with ID: " + id);
                } else {
                    System.out.println("No product found to delete with ID: " + id);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid product ID: " + idParam);
            }
        }
        // Redirect back to the product management page after delete
        response.sendRedirect(request.getContextPath() + "/productmanagement");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
