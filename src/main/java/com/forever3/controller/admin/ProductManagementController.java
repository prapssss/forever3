package com.forever3.controller.admin;

import com.forever3.model.ProductModel;
import com.forever3.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = {"/productmanagement"})
public class ProductManagementController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<ProductModel> products = productService.getAllProducts();
        request.setAttribute("products", products);
        request.setAttribute("activePage", "productmanagement");
        request.getRequestDispatcher("/WEB-INF/pages/admin/productmanagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean deleted = productService.deleteProduct(id);
                if (!deleted) {
                    System.out.println("Product not found or could not be deleted for ID: " + id);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("productmanagement");
    }
}
