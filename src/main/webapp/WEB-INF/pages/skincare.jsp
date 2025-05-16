<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/The Rice toner.jpg" alt="Rice Tonner">
          <h3>Rice Tonner</h3>
          <p class="product-price">$45.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
 
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/mixsoon.jpg" alt="mixoon Hyaluronic Acid Serum">
          <h3>mixoon Hyaluronic Acid Serum</h3>
          <p class="product-price">$40.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
 
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/Beauty of Joseon SPF 50.jpg" alt="Beauty of Joseon Sunscreen">
          <h3>Beauty of Joseon Sunscreen</h3>
          <p class="product-price">$30.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
 
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/Centella Tonning Tonner.jpg" alt="Centella Tonning Tonner">
          <h3>Centella Tonning Tonner</h3>
          <p class="product-price">$55.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
 
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/Centella Light Cleansing Oil.jpg" alt="Centella Cleansing Oil">
          <h3>Centella Cleansing Oil</h3>
          <p class="product-price">$35.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
 
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/seoul 1988 serum.jpg" alt="Seoul 1988 Serum">
          <h3>Seoul 1988 Serum</h3>
          <p class="product-price">$50.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
 
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/Korean Eye Cream.jpg" alt="Revive Eye Serum">
          <h3>Revive Eye Serum</h3>
          <p class="product-price">$25.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
 
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/Ordinary Serum.jpg" alt="The Ordinary Hyaluronic Acid">
          <h3>The Ordinary Hyaluronic Acid</h3>
          <p class="product-price">$15.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
      </div>
    </section>
  </main>
 
  <jsp:include page="footer.jsp" />
 
</body>
</html>