<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Bill - Forever3</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bill.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <div class="bill-container">
            <div class="bill-header">
                <h1>Order Bill</h1>
                <div class="bill-info">
                    <p><strong>Bill Date:</strong> <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm"/></p>
                    <p><strong>Order ID:</strong> ${order.orderId}</p>
                </div>
            </div>

            <div class="bill-sections">
                <div class="section">
                    <h2>Customer Details</h2>
                    <div class="details">
                        <p><strong>Name:</strong> ${payment.customerName}</p>
                        <p><strong>Phone:</strong> ${payment.mobileNumber}</p>
                        <p><strong>Address:</strong> ${payment.deliveryLocation}</p>
                    </div>
                </div>

                <div class="section">
                    <h2>Order Items</h2>
                    <table class="items-table">
                        <thead>
                            <tr>
                                <th>Item</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Subtotal</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${orderItems}">
                                <tr>
                                    <td>${item.itemName}</td>
                                    <td>${item.quantity}</td>
                                    <td>NPR ${item.price}</td>
                                    <td>NPR ${item.price * item.quantity}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="3" class="total-label">Total Amount:</td>
                                <td class="total-amount">NPR ${order.totalPrice}</td>
                            </tr>
                        </tfoot>
                    </table>
                </div>

                <div class="section">
                    <h2>Payment Details</h2>
                    <div class="details">
                        <p><strong>Payment Method:</strong> ${payment.paymentMethod}</p>
                        <p><strong>Payment Status:</strong> ${payment.paymentStatus}</p>
                        <p><strong>Payment Date:</strong> <fmt:formatDate value="${payment.paymentDate}" pattern="dd/MM/yyyy HH:mm"/></p>
                    </div>
                </div>
            </div>

            <div class="bill-footer">
                <p class="thank-you">Thank you for shopping with us!</p>
                <div class="actions">
                    <button onclick="window.print()" class="print-button">Print Bill</button>
                    <a href="${pageContext.request.contextPath}/home" class="continue-shopping">Continue Shopping</a>
                </div>
            </div>
        </div>
    </main>
    <jsp:include page="footer.jsp" />
</body>
</html> 