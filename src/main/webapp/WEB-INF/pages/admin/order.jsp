<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Management</title>
    <link rel="stylesheet" href="${contextPath}/css/order.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <jsp:include page="nav.jsp" />

    <div class="admin-header">
        <h1><i class="fas fa-shopping-cart"></i> Order Management</h1>
    </div>

    <!-- Alert Messages -->
    <c:if test="${not empty sessionScope.errorMessage}">
        <div class="alert alert-error">
            ${sessionScope.errorMessage}
            <c:remove var="errorMessage" scope="session"/>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.successMessage}">
        <div class="alert alert-success">
            ${sessionScope.successMessage}
            <c:remove var="successMessage" scope="session"/>
        </div>
    </c:if>

    <div class="order-list">
        <c:if test="${empty orders}">
            <div class="no-orders">
                <i class="fas fa-box-open fa-3x"></i>
                <p>No orders found</p>
            </div>
        </c:if>

        <c:if test="${not empty orders}">
            <div class="list-header">
                <div class="header-item">Order ID</div>
                <div class="header-item">Product</div>
                <div class="header-item">Customer</div>
                <div class="header-item">Date</div>
                <div class="header-item">Total (NPR)</div>
                <div class="header-item">Status</div>
            </div>

            <c:forEach var="order" items="${orders}">
                <div class="order-item">
                    <div class="order-id">#${order.orderId}</div>
                    <div class="order-name">${order.productName}</div>
                    <div class="order-customer">${order.customerName}</div>
                    <div class="order-date">${order.orderDate}</div>
                    <div class="order-total">NPR ${order.totalPrice}</div>
                    <div class="status-${fn:toLowerCase(order.orderType)}">${order.orderType}</div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</body>
</html>