package com.forever3.controller.admin;

import com.forever3.model.OrderModel;
import com.forever3.service.OrderService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init() {
        orderService = new OrderService();
    }

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

    private void loadOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<OrderModel> orders = orderService.getAllOrders();
        request.setAttribute("orders", orders);
        request.setAttribute("activePage", "orders");
        request.getRequestDispatcher("/WEB-INF/pages/admin/order.jsp").forward(request, response);
    }
}
