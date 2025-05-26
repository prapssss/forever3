<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Your Shopping Cart</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css" />
</head>
<body>
  <jsp:include page="header.jsp" />
  <main>
    <h1>Your Shopping Cart</h1>
    <c:choose>
      <c:when test="${empty cart}">
        <p>Your cart is empty.</p>
      </c:when>
      <c:otherwise>
        <table border="1" cellpadding="8" cellspacing="0">
          <thead>
            <tr>
              <th>Image</th>
              <th>Product Name</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Subtotal</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="item" items="${cart}">
              <tr>
                <td>
                  <img src="${pageContext.request.contextPath}/${item.imageUrl}" alt="${item.itemName}" width="80" />
                </td>
                <td>${item.itemName}</td>
                <td>NPR ${item.itemPrice}</td>
                <td>${item.quantity}</td>
                <td>NPR ${item.itemPrice * item.quantity}</td>
                <td>
                  <form action="${pageContext.request.contextPath}/removeFromCart" method="post">
                    <input type="hidden" name="itemId" value="${item.itemId}" />
                    <button type="submit">Remove</button>
                  </form>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <form action="${pageContext.request.contextPath}/checkout" method="post">
          <button type="submit">Proceed to Checkout</button>
        </form>
      </c:otherwise>
    </c:choose>
  </main>
  <jsp:include page="footer.jsp" />
</body>
</html>
