<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin - Customer Management</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/customers.css" />
</head>
<body>

    <!-- Navigation sidebar included from nav.jsp -->
    <jsp:include page="/WEB-INF/pages/admin/nav.jsp" />

    <!-- Admin Header -->
    <div class="admin-header">
        <h1>Customer Management</h1>
    </div>

    <!-- Customer List -->
    <div class="customer-list">
        <div class="list-header">
            <div class="header-item customer-name-header">Customer Name</div>
            <div class="header-item">Email</div>
            <div class="header-item">Phone</div>
            <div class="header-item">Orders</div>
            <div class="header-item">Payment Status</div>
            <div class="header-item">Actions</div>
        </div>

        <c:forEach var="customer" items="${customers}">
            <div class="customer-item">
                <div class="customer-name">
                    <div class="customer-avatar">
                        ${fn:toUpperCase(fn:substring(customer.firstName, 0, 1))}${fn:toUpperCase(fn:substring(customer.lastName, 0, 1))}
                    </div>
                    <div>${customer.firstName} ${customer.lastName}</div>
                </div>

                <div class="customer-email">${customer.email}</div>
                <div class="customer-phone">${customer.number}</div>
                <div class="customer-orders">0</div> <!-- Placeholder, update when order linking is added -->
                <div class="customer-status paid">Paid</div> <!-- Placeholder, can be dynamic in the future -->

                <div class="customer-actions">
                    <!-- View Button -->
                    <a href="${contextPath}/viewcustomer?id=${customer.id}">
                        <button class="view-order-btn">View</button>
                    </a>
                    <!-- Delete Button -->
                    <form action="${contextPath}/deletecustomer" method="post"
                        onsubmit="return confirm('Are you sure you want to delete this customer?');">
                        <input type="hidden" name="customerId" value="${customer.id}" />
                        <button type="submit" class="delete-btn">Delete</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>

</body>
</html>