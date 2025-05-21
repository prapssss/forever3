package com.forever3.controller.admin;

import com.forever3.config.DbConfig;
import com.forever3.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/viewcustomer")
public class ViewCustomerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/customers");
            return;
        }

        int customerId = Integer.parseInt(idParam);
        UserModel customer = null;

        try (Connection conn = DbConfig.getDbConnection()) {
            String sql = "SELECT * FROM customer WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new UserModel(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("user_name"),
                    rs.getString("email"),
                    rs.getString("number"),
                    rs.getString("password"),
                    rs.getString("image_url"),
                    rs.getString("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (customer == null) {
            response.sendRedirect(request.getContextPath() + "/customers");
            return;
        }

        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/WEB-INF/pages/admin/viewcustomer.jsp").forward(request, response);
    }
}
