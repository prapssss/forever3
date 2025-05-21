<%@ page import="java.util.List" %>
<%@ page import="com.forever3.model.CartModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CartModel> cart = (List<CartModel>) session.getAttribute("cart");
    double total = 0;
%>

<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border: 1px solid #ccc; text-align: center; }
        th { background-color: #f2f2f2; }
        .total { text-align: right; font-weight: bold; margin-top: 20px; }
        .btn { padding: 8px 15px; background-color: #28a745; color: white; border: none; cursor: pointer; }
        .btn:hover { background-color: #218838; }
        .btn-danger { background-color: #dc3545; }
        .btn-danger:hover { background-color: #c82333; }
    </style>
</head>
<body>

<h2>Your Shopping Cart</h2>

<% if (cart == null || cart.isEmpty()) { %>
    <p>Your cart is empty.</p>
<% } else { %>
    <table>
        <tr>
            <th>Image</th>
            <th>Product</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
            <th>Action</th>
        </tr>

        <% for (CartModel item : cart) {
            double subtotal = item.getItemPrice() * item.getQuantity();
            total += subtotal;
        %>
            <tr>
                <td><img src="<%= item.getImageUrl() %>" width="50" height="50"/></td>
                <td><%= item.getItemName() %></td>
                <td>$<%= item.getItemPrice() %></td>
                <td><%= item.getQuantity() %></td>
                <td>$<%= String.format("%.2f", subtotal) %></td>
                <td>
                    <form action="remove-from-cart" method="post" style="margin: 0;">
                        <input type="hidden" name="productId" value="<%= item.getItemId() %>"/>
                        <button type="submit" class="btn btn-danger">Remove</button>
                    </form>
                </td>
            </tr>
        <% } %>
    </table>

    <div class="total">
        Total: $<%= String.format("%.2f", total) %>
    </div>

    <br/>
    <form action="checkout" method="post">
        <button type="submit" class="btn">Proceed to Checkout</button>
    </form>
<% } %>

</body>
</html>
