package com.forever3.controller.admin;

import com.forever3.model.OrderModel;
import com.forever3.service.OrderService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling admin order management functionality.
 * Displays a list of all orders.
 * 
 * Author: Prapti Adhikari
 */
@WebServlet("/order")
public class OrderController extends HttpServlet {
    private OrderService orderService;

    /**
     * Initializes the servlet and sets up the OrderService instance.
     */
    @Override
    public void init() {
        orderService = new OrderService(); // Initialize service to handle order operations
    }

    /**
     * Handles GET requests by loading and displaying all orders.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadOrders(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadOrders(request, response);
    }

    /**
     * Loads all orders from the database and forwards the data to the view.
     */
    private void loadOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve all orders from the service
        List<OrderModel> orders = orderService.getAllOrders();

        // Set orders and current page attribute for view rendering
        request.setAttribute("orders", orders);
        request.setAttribute("activePage", "orders"); // Used to highlight active nav/menu

        // Forward request to the JSP page for display
        request.getRequestDispatcher("/WEB-INF/pages/admin/order.jsp").forward(request, response);
    }
}
