<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Details</title>
    <link rel="stylesheet" href="${contextPath}/css/viewcustomer.css">
</head>
<body>
     <jsp:include page="/WEB-INF/pages/admin/nav.jsp" />
    <div class="customer-container">
        <div class="customer-header">
            <h2>Customer Details</h2>
        </div>
        
        <!-- Add image if available -->
        <c:if test="${not empty customer.imageUrl}">
            <div class="image-container">
                <img src="${contextPath}${customer.imageUrl}" alt="${customer.firstName} ${customer.lastName}" class="customer-image">
            </div>
        </c:if>
        
        <div class="customer-details">
            <p><strong>ID:</strong> ${customer.id}</p>
            <p><strong>Name:</strong> ${customer.firstName} ${customer.lastName}</p>
            <p><strong>Username:</strong> ${customer.userName}</p>
            <p><strong>Email:</strong> ${customer.email}</p>
            <p><strong>Phone:</strong> ${customer.number}</p>
            <p><strong>Role:</strong> ${customer.role}</p>
            <c:if test="${not empty customer.imageUrl}">
                <p><strong>Image URL:</strong> ${customer.imageUrl}</p>
            </c:if>
        </div>
        
        <div class="action-buttons">
            <a href="${contextPath}/customers" class="back-button">Back to Customers</a>
        </div>
    </div>
</body>
</html>