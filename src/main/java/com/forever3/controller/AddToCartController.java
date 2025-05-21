package com.forever3.controller;

import com.forever3.model.CartModel;
import com.forever3.model.ProductModel;
import com.forever3.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        ProductModel product = productService.getProductById(productId);
        if (product == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        HttpSession session = request.getSession();
        List<CartModel> cart = (List<CartModel>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;
        for (CartModel item : cart) {
            if (item.getItemId() == productId) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            CartModel cartItem = new CartModel();
            cartItem.setItemId(product.getItemId());
            cartItem.setItemName(product.getItemName());
            cartItem.setItemPrice(product.getItemPrice());
            cartItem.setQuantity(quantity);
            cartItem.setImageUrl(product.getImageUrl());
            cart.add(cartItem);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
}
