<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History - Forever3</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>
<body style="font-family: 'Inter', sans-serif; margin: 0; padding: 0; background-color: #f8f9fa; color: #333;">
    <jsp:include page="header.jsp" />
    <main style="max-width: 1200px; margin: 2rem auto; padding: 0 1rem;">
        <div style="background-color: white; border-radius: 12px; box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); padding: 2rem;">
            <h1 style="color: #2c3e50; margin: 0 0 2rem 0; font-size: 2rem; text-align: center;">Order History</h1>

            <c:if test="${empty orders}">
                <div style="text-align: center; padding: 3rem 0;">
                    <p style="color: #666; margin-bottom: 1.5rem;">You haven't placed any orders yet.</p>
                    <a href="${pageContext.request.contextPath}/home" style="display: inline-block; padding: 0.8rem 1.5rem; background-color: #2c3e50; color: white; text-decoration: none; border-radius: 5px;">Start Shopping</a>
                </div>
            </c:if>

            <c:forEach var="order" items="${orders}">
                <div style="background-color: #fff; border: 1px solid #eee; border-radius: 8px; margin-bottom: 2rem; overflow: hidden;">
                    <div style="display: flex; justify-content: space-between; align-items: center; padding: 1.5rem; background-color: #f8f9fa; border-bottom: 1px solid #eee;">
                        <div>
                            <h2 style="margin: 0; color: #2c3e50; font-size: 1.2rem;">Order #${order.orderId}</h2>
                            <p style="color: #666; margin: 0.5rem 0 0 0; font-size: 0.9rem;">
                                <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm"/>
                            </p>
                        </div>
                        <div>
                            <span style="padding: 0.5rem 1rem; border-radius: 20px; font-size: 0.9rem; font-weight: 500; 
                                background-color: ${order.paymentStatus == 'Paid' ? '#d4edda' : '#fff3cd'}; 
                                color: ${order.paymentStatus == 'Paid' ? '#155724' : '#856404'};">
                                ${order.paymentStatus}
                            </span>
                        </div>
                    </div>

                    <div style="padding: 1.5rem;">
                        <c:forEach var="item" items="${order.items}">
                            <div style="display: flex; align-items: center; padding: 1rem 0; border-bottom: 1px solid #eee;">
                                <img src="${pageContext.request.contextPath}/resources/images/system/${item.imageUrl}" 
                                     alt="${item.itemName}" 
                                     style="width: 60px; height: 60px; object-fit: cover; border-radius: 8px; border: 1px solid #ddd; margin-right: 1rem;" />
                                <div>
                                    <h3 style="margin: 0 0 0.5rem 0; color: #2c3e50; font-size: 1rem;">${item.itemName}</h3>
                                    <p style="color: #666; margin: 0.25rem 0; font-size: 0.9rem;">Quantity: ${item.quantity}</p>
                                    <p style="color: #2c3e50; font-weight: 500; margin: 0.25rem 0;">NPR ${item.price}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div style="display: flex; justify-content: space-between; align-items: center; padding: 1.5rem; background-color: #f8f9fa; border-top: 1px solid #eee;">
                        <div>
                            <p style="margin: 0.5rem 0; color: #666; font-size: 0.9rem;"><strong style="color: #2c3e50;">Payment Method:</strong> ${order.paymentMethod}</p>
                            <p style="margin: 0.5rem 0; color: #666; font-size: 0.9rem;"><strong style="color: #2c3e50;">Payment Date:</strong> <fmt:formatDate value="${order.paymentDate}" pattern="dd/MM/yyyy HH:mm"/></p>
                        </div>
                        <div>
                            <p style="margin: 0; color: #2c3e50; font-size: 1.1rem;"><strong>Total Amount:</strong> NPR ${order.totalPrice}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
    <jsp:include page="footer.jsp" />
</body>
</html>
