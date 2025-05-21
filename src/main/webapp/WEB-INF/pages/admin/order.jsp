<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css" />
</head>
<body>
<jsp:include page="nav.jsp" />

<div class="admin-header">
    <h1>Order Management</h1>
</div>

<div class="order-list">
    <div class="list-header">
        <div class="header-item">Order ID</div>
        <div class="header-item">Product</div>
        <div class="header-item">Customer</div>
        <div class="header-item">Date</div>
        <div class="header-item">Total (£)</div>
        <div class="header-item">Payment Status</div>
    </div>

    <c:forEach var="order" items="${orders}">
        <div class="order-item">
            <div class="order-id">${order.orderId}</div>
            <div class="order-name">${order.productName}</div>
            <div class="order-customer">${order.customerName}</div>
            <div class="order-date">${order.orderDate}</div>
            <div class="order-total">£${order.totalPrice}</div>
            <div class="order-type">${order.PaymentStatus}</div>
        </div>
    </c:forEach>
</div>
</body>
</html>