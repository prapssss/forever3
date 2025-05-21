package com.forever3.controller.admin;

import com.forever3.model.ProductModel;
import com.forever3.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addstock")
public class AddStockController extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int itemId = Integer.parseInt(request.getParameter("id"));
            ProductModel product = productService.getProductById(itemId);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/pages/admin/addstock.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            String itemIdStr = request.getParameter("itemId");
            String stockStr = request.getParameter("additionalStock");

            System.out.println("Received itemId: " + itemIdStr);
            System.out.println("Received additionalStock: " + stockStr);

            int itemId = Integer.parseInt(itemIdStr);
            int additionalStock = Integer.parseInt(stockStr);

            if (additionalStock <= 0) {
                ProductModel product = productService.getProductById(itemId);
                request.setAttribute("product", product);
                request.setAttribute("errorMessage", "Stock must be greater than zero.");
                request.getRequestDispatcher("/WEB-INF/pages/admin/addstock.jsp").forward(request, response);
                return;
            }

            boolean updated = productService.addStock(itemId, additionalStock);
            if (updated) {
                response.sendRedirect("productmanagement");
            } else {
                ProductModel product = productService.getProductById(itemId);
                request.setAttribute("product", product);
                request.setAttribute("errorMessage", "Failed to update stock.");
                request.getRequestDispatcher("/WEB-INF/pages/admin/addstock.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid input or server error.");
            request.getRequestDispatcher("/WEB-INF/pages/admin/addstock.jsp").forward(request, response);
        }
    }
}
