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

    <c:choose>
      <c:when test="${empty cart}">
        <p>Your cart is empty.</p>
        <a href="${pageContext.request.contextPath}/home">Go back to shopping</a>
      </c:when>
      <c:otherwise>
        <table border="1" cellpadding="8" cellspacing="0">
          <thead>
            <tr>
              <th>Product</th>
              <th>Quantity</th>
              <th>Price (each)</th>
              <th>Subtotal</th>
            </tr>
          </thead>
          <tbody>
            <c:set var="total" value="0" />
            <c:forEach var="item" items="${cart}">
              <tr>
                <td>${item.itemName}</td>
                <td>${item.quantity}</td>
                <td>$${item.itemPrice}</td>
                <td>
                  <c:set var="subtotal" value="${item.itemPrice * item.quantity}" />
                  $${subtotal}
                  <c:set var="total" value="${total + subtotal}" />
                </td>
              </tr>
            </c:forEach>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="3" style="text-align:right;"><strong>Total:</strong></td>
              <td><strong>$${total}</strong></td>
            </tr>
          </tfoot>
        </table>

        <form action="${pageContext.request.contextPath}/checkout" method="post">
          <!-- You can add payment or shipping info inputs here if needed -->
          <button type="submit">Confirm Purchase</button>
        </form>
      </c:otherwise>
    </c:choose>
  </main>
  <jsp:include page="footer.jsp" />
</body>
</html>
