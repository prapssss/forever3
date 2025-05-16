<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${contextPath}/css/nav.css">

<div class="nav-panel">
    <div class="nav-header">
        <div class="nav-logo">Admin Panel</div>
       
    </div>

    <ul class="nav-menu">
        <li><a href="${contextPath}/dashboard" class="${activePage == 'dashboard' ? 'active' : ''}">Dashboard</a></li>
        <li><a href="${contextPath}/customers" class="${activePage == 'customers' ? 'active' : ''}">Customers</a></li>
        <li><a href="${contextPath}/order" class="${activePage == 'orders' ? 'active' : ''}">Orders</a></li>
        <li><a href="${contextPath}/productmanagement" class="${activePage == 'products' ? 'active' : ''}">Products</a></li>
        <li><a href="${contextPath}/logout">Logout</a></li>
        
    </ul>
</div>
