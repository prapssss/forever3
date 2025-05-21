<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>Order Confirmation</title></head>
<body>
<h1>Thank you for your order!</h1>
<p>Your order has been placed successfully.</p>
<p>Order ID: ${param.orderId}</p>
<a href="${pageContext.request.contextPath}/home">Continue Shopping</a>
</body>
</html>
