<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${contextPath}/css/nav.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

<div class="nav-panel">
    <div class="nav-header">
        <div class="nav-logo">
            <i class="fas fa-store"></i>
            <span>Admin Panel</span>
        </div>
    </div>

    <ul class="nav-menu">
        <li>
            <a href="${contextPath}/dashboard" class="${activePage == 'dashboard' ? 'active' : ''}">
                <i class="fas fa-chart-line"></i>
                <span class="menu-text">Dashboard</span>
            </a>
        </li>
        <li>
            <a href="${contextPath}/customers" class="${activePage == 'customers' ? 'active' : ''}">
                <i class="fas fa-users"></i>
                <span class="menu-text">Customers</span>
            </a>
        </li>
        <li>
            <a href="${contextPath}/order" class="${activePage == 'orders' ? 'active' : ''}">
                <i class="fas fa-shopping-cart"></i>
                <span class="menu-text">Orders</span>
            </a>
        </li>
        <li>
            <a href="${contextPath}/productmanagement" class="${activePage == 'products' ? 'active' : ''}">
                <i class="fas fa-box"></i>
                <span class="menu-text">Products</span>
            </a>
        </li>
        <li>
            <a href="${contextPath}/logout">
                <i class="fas fa-sign-out-alt"></i>
                <span class="menu-text">Logout</span>
            </a>
        </li>
    </ul>
</div>
