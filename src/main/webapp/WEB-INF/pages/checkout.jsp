<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Checkout</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css" />
</head>
<body>
  <jsp:include page="header.jsp" />
  <main>
    <h1>Checkout</h1>

    <c:if test="${not empty sessionScope.errorMessage}">
        <div class="error-message">
            ${sessionScope.errorMessage}
        </div>
        <c:remove var="errorMessage" scope="session"/>
    </c:if>

    <c:choose>
      <c:when test="${empty sessionScope.orderId}">
        <p>No order found.</p>
        <a href="${pageContext.request.contextPath}/viewCart">Go back to cart</a>
      </c:when>
      <c:otherwise>
        <div class="checkout-container">
          <div class="order-summary">
            <h2>Order Summary</h2>
            <div class="order-details">
              <p><strong>Order ID:</strong> ${sessionScope.orderId}</p>
              <p><strong>Total Amount:</strong> NPR ${sessionScope.totalAmount}</p>
            </div>
          </div>
          
          <form action="${pageContext.request.contextPath}/payment" method="get">
            <button type="submit" class="checkout-button">Proceed to Payment</button>
          </form>
        </div>
      </c:otherwise>
    </c:choose>
  </main>
  <jsp:include page="footer.jsp" />
</body>
</html>
