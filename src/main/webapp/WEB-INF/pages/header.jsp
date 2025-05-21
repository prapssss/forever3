<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    HttpSession userSession = request.getSession(false);
    Integer customerId = (Integer) (userSession != null ? userSession.getAttribute("customerId") : null);
    String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);
    pageContext.setAttribute("currentUser", currentUser);
    pageContext.setAttribute("customerId", customerId);
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Beauty Store</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css" />
</head>
<body>
<header class="header">
  <div class="header-content">
 
    <!-- Logo -->
    <div class="logo">
      <a href="${contextPath}/">forever3</a>
    </div>
 
    <!-- Navigation -->
    <nav class="main-nav">
      <ul>
        <li><a href="${contextPath}/home">Home</a></li>
        <li><a href="${contextPath}/skincare">SkinCare</a></li>
        <li><a href="${contextPath}/makeup">MakeUp</a></li>
        <li><a href="${contextPath}/aboutus">AboutUs</a></li>
        <li><a href="${contextPath}/contactus">ContactUs</a></li>
        <c:if test="${not empty customerId}">
          <li><a href="${contextPath}/orderHistory">Order History</a></li>
        </c:if>
      </ul>
    </nav>
 
    <!-- Right Section -->
    <div class="header-right">
      <!-- Search -->
      <div class="search-container">
        <input type="text" class="search-input" placeholder="search" />
        <button class="search-button">
          <img src="${contextPath}/resources/images/system/search.jpg" class="icon-img" alt="Search" />
        </button>
      </div>
 
      <!-- Auth Buttons -->
      <div class="auth-button-container">
        <c:choose>
          <c:when test="${not empty customerId}">
            <form action="${pageContext.request.contextPath}/logout" method="post">
              <button type="submit" class="auth-button">Logout</button>
            </form>
          </c:when>
          <c:otherwise>
            <form action="${pageContext.request.contextPath}/login" method="get">
              <button type="submit" class="auth-button">Login</button>
            </form>
          </c:otherwise>
        </c:choose>
      </div>
 
      <!-- Cart -->
      <div class="icon-container">
        <a href="${contextPath}/viewCart" class="cart-link">
          <img src="${contextPath}/resources/images/system/cart.jpg" class="icon-img" alt="Cart" />
          <c:if test="${not empty sessionScope.cart}">
            <span class="cart-count">${sessionScope.cart.size()}</span>
          </c:if>
        </a>
      </div>
 
      <!-- Profile -->
      <c:if test="${not empty customerId}">
        <div class="icon-container">
          <a href="${contextPath}/profile">
            <img src="${contextPath}/resources/images/system/profile.jpg" class="icon-img" alt="Profile" />
          </a>
        </div>
      </c:if>
    </div>
 
  </div>
</header>
</body>
</html>