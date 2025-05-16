<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Order Management</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/order.css">
</head>
<body>
<jsp:include page="nav.jsp" />
    <div class="admin-content">
            <div class="admin-header">
                <h1>Order Management</h1>
                <div class="header-actions">
                    <button class="add-new-btn">+ Add New Order</button>
                </div>
            </div>

            <div class="admin-filters">
                <div class="search-container">
                    <input type="text" placeholder="Search orders..." class="search-input">
                    <button class="search-btn">Search</button>
                </div>
                <div class="filter-container">
                    <select class="filter-select">
                        <option value="">All Types</option>
                        <option value="online">Online</option>
                        <option value="in-store">In-store</option>
                    </select>
                    <button class="filter-btn">Filter</button>
                </div>
            </div>

            <div class="order-list">
                <div class="list-header">
                    <div class="header-item">Order ID</div>
                    <div class="header-item">Order Name</div>
                    <div class="header-item">Customer</div>
                    <div class="header-item">Date</div>
                    <div class="header-item">Total Price</div>
                    <div class="header-item">Order Type</div>
                </div>

                <!-- Order 1 -->
                <div class="order-item">
                    <div class="order-id">ORD001</div>
                    <div class="order-name">Face Wash Pack</div>
                    <div class="order-customer">John Doe</div>
                    <div class="order-date">2025-05-10</div>
                    <div class="order-total">$45.00</div>
                    <div class="order-type">Online</div>
                </div>

                <!-- Order 2 -->
                <div class="order-item">
                    <div class="order-id">ORD002</div>
                    <div class="order-name">Lipstick Set</div>
                    <div class="order-customer">Jane Smith</div>
                    <div class="order-date">2025-05-11</div>
                    <div class="order-total">$60.00</div>
                    <div class="order-type">In-store</div>
                </div>

                <!-- Order 3 -->
                <div class="order-item">
                    <div class="order-id">ORD003</div>
                    <div class="order-name">Night Cream</div>
                    <div class="order-customer">Maria Williams</div>
                    <div class="order-date">2025-05-12</div>
                    <div class="order-total">$35.00</div>
                    <div class="order-type">Online</div>
                </div>
            </div>

            <div class="pagination">
                <button class="pagination-btn" disabled>Previous</button>
                <div class="page-numbers">
                    <button class="page-number active">1</button>
                    <button class="page-number">2</button>
                    <button class="page-number">3</button>
                </div>
                <button class="pagination-btn">Next</button>
            </div>
        </div>
   
</body>
</html>
