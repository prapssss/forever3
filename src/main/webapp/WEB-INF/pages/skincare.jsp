<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Skin Care - Forever3</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/skincare.css" />
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet" />
</head>
<body>
  <jsp:include page="header.jsp" />
  <main>
    <section class="product-page">
      <h1>Skin Care Products</h1>
      <c:if test="${not empty sessionScope.cartMessage}">
        <div class="success-message">
          ${sessionScope.cartMessage}
        </div>
        <c:remove var="cartMessage" scope="session"/>
      </c:if>
      <div class="product-grid">
        <c:forEach var="product" items="${productList}">
          <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/images/system/${product.imageUrl}" alt="${product.itemName}" />
            <h3>${product.itemName}</h3>
            <p class="product-price">$${product.itemPrice}</p>
            <form method="post" action="${pageContext.request.contextPath}/addToCart">
              <input type="hidden" name="itemId" value="${product.itemId}" />
              <input type="number" name="quantity" value="1" min="1" />
              <button class="add-to-cart-btn">Add to Cart</button>
            </form>
          </div>
        </c:forEach>
      </div>
    </section>
  </main>
  <jsp:include page="footer.jsp" />
</body>
</html>
