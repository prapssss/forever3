<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Makeup - Forever3</title>
  <!-- Link to CSS using contextPath -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/makeup.css">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
</head>
<body>
  <!-- Include Header -->
  <jsp:include page="header.jsp" />
  <main>
    <section class="product-page">
      <h1>Makeup Products</h1>
      <div class="product-grid">
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/Easterlaurier Foundation.jpg" alt="Double Wear
          Stay-in-Place Foundation">
          <h3>Double Wear
            Stay-in-Place Foundation</h3>
          <p class="product-price">$45.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/Dior Bronzer.jpg" alt="dior">
          <h3>Christian Dior Forever Couture Luminizer</h3>
          <p class="product-price">$40.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/fenty beauty lip pink.jpg" alt="Fenty Beauty Lip Pink">
          <h3>Fenty Beauty Gloss Bomb</h3>
          <p class="product-price">$30.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/diorfoundation.jpg" alt="Dior Forever Skin Glow 24h Wear Radiant Foundation Spf 20">
          <h3> Dior Forever Radiant Foundation </h3>
          <p class="product-price">$55.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
        <!-- Additional Products for the second row -->
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/Mac Lipstick.jpg"alt="MAC Lipstick- Relentlessly Red">
          <h3>MAC Lipstick- Relentlessly Red</h3>
          <p class="product-price">$35.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/fenty beauty lip bomb.jpg" alt="Fenty Beauty Gloss Bomb">
          <h3>Fenty Beauty Gloss Bomb</h3>
          <p class="product-price">$50.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/rarebeautymascara.jpg">
          <h3>Rare Beauty Mascara</h3>
          <p class="product-price">$25.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
        <div class="product-card">
          <img src="${pageContext.request.contextPath}/resources/images/system/Rhode - espresso.jpg" alt="Rhode Peptide Lip Tint">
          <h3>Rhode Peptide Lip Tint</h3>
          <p class="product-price">$15.00</p>
          <button class="add-to-cart-btn">Add to Cart</button>
        </div>
      </div>
    </section>
  </main>
   <!-- Include Footer -->
  <jsp:include page="footer.jsp" />
</body>
</html>
 