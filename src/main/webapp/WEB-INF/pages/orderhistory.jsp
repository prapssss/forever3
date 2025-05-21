<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History - Forever3</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderHistory.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <div class="order-history-container">
            <h1>Order History</h1>
            
            <c:if test="${empty orders}">
                <div class="no-orders">
                    <p>You haven't placed any orders yet.</p>
                    <a href="${pageContext.request.contextPath}/home" class="continue-shopping">Start Shopping</a>
                </div>
            </c:if>

            <c:forEach var="order" items="${orders}">
                <div class="order-card">
                    <div class="order-header">
                        <div class="order-info">
                            <h2>Order #${order.orderId}</h2>
                            <p class="order-date">
                                <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm"/>
                            </p>
                        </div>
                        <div class="order-status">
                            <span class="status-badge ${order.paymentStatus == 'Paid' ? 'paid' : 'pending'}">
                                ${order.paymentStatus}
                            </span>
                        </div>
                    </div>

                    <div class="order-items">
                        <c:forEach var="item" items="${order.items}">
                            <div class="order-item">
                                <img src="${pageContext.request.contextPath}${item.imageUrl}" alt="${item.itemName}" class="item-image">
                                <div class="item-details">
                                    <h3>${item.itemName}</h3>
                                    <p class="item-quantity">Quantity: ${item.quantity}</p>
                                    <p class="item-price">NPR ${item.price}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="order-footer">
                        <div class="payment-info">
                            <p><strong>Payment Method:</strong> ${order.paymentMethod}</p>
                            <p><strong>Payment Date:</strong> 
                                <fmt:formatDate value="${order.paymentDate}" pattern="dd/MM/yyyy HH:mm"/>
                            </p>
                        </div>
                        <div class="order-total">
                            <p><strong>Total Amount:</strong> NPR ${order.totalPrice}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
    <jsp:include page="footer.jsp" />
</body>
</html> 