<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Skin Care - Forever3</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/skincare.css">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
</head>
<body>
  <jsp:include page="header.jsp" />
  <main>
    <section class="product-page">
      <h1>Skin Care Products</h1>
      <div class="product-grid">
        <c:forEach var="product" items="${productList}">
          <c:if test="${product.category eq 'Skincare'}">
            <div class="product-card">
              <img src="${pageContext.request.contextPath}/${product.image}" alt="${product.name}">
              <h3>${product.name}</h3>
              <p class="product-price">$${product.price}</p>
              <form method="post" action="${pageContext.request.contextPath}/addToCart">
                <input type="hidden" name="productId" value="${product.id}" />
                <input type="number" name="quantity" value="1" min="1" />
                <button class="add-to-cart-btn">Add to Cart</button>
              </form>
            </div>
          </c:if>
        </c:forEach>
      </div>
    </section>
  </main>
  <jsp:include page="footer.jsp" />
</body>
</html>
