<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/dashboard.css">
</head>
<body>
<jsp:include page="nav.jsp" />

<div class="main-content">
    <div class="page-header">
        <h1 class="welcome-message">Welcome, Admin!</h1>
    </div>

    <div class="dashboard-cards">
        <div class="card">
            <h3 class="card-title">Total Customers</h3>
            <div class="card-value">${totalCustomers}</div>
        </div>
        <div class="card">
            <h3 class="card-title">Total Orders</h3>
            <div class="card-value">${totalOrders}</div>
        </div>
        <div class="card">
            <h3 class="card-title">Total Products</h3>
            <div class="card-value">${totalProducts}</div>
        </div>
    </div>


    <h2 class="section-title">Recent Customers</h2>
    <div class="table-container">
        <table class="customers-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Customer Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.firstName} ${customer.lastName}</td>
                        <td>${customer.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
