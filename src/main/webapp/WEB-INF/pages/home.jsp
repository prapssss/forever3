<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Forever3</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
 
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@600&family=Open+Sans&display=swap" rel="stylesheet">
</head>
<body class="home-page">

  <!-- Include Header -->
  <jsp:include page="header.jsp" />
 
  <!-- Hero Section -->
  <section class="hero">
    <div class="hero-content">
      <h1>Glow with Confidence</h1>
      <p>Pure skincare. Flawless beauty. Forever 3.</p> 
    </div>
  </section>
 
  <!-- Categories Section -->
  <section class="section">
    <h2 class="section-title">Shop by Category</h2>
    <div class="card-grid">
      <div class="card">
        <img src="${pageContext.request.contextPath}/resources/images/system/homeskin.jpg" alt="Skin Image">
        <h3>Skincare</h3>
      </div>
      <div class="card">
        <img src="${pageContext.request.contextPath}/resources/images/system/homemakeup.jpg" alt="Makeup Image">
        <h3>Makeup</h3>
      </div>
    </div>
  </section>
 
  <!-- Best Sellers Section -->
  <section class="section alt">
    <h2 class="section-title">Best Sellers</h2>
    <div class="card-grid">
      <div class="product-card">
        <img src="${pageContext.request.contextPath}/resources/images/system/rarebeautyblush.jpg" alt="Rare Beauty Blush">
        <h4>Rare Beauty Blush</h4>
        <p>Rs 5000.00</p>
      </div>
      <div class="product-card">
        <img src="${pageContext.request.contextPath}/resources/images/system/diorfoundation.jpg" alt="Dior Radiant Foundation">
        <h4>Matte Perfection Foundation</h4>
        <p>Rs 6119.00</p>
      </div>
      <div class="product-card">
        <img src="${pageContext.request.contextPath}/resources/images/system/rarebeautymascara.jpg" alt="Rare Beauty Mascara">
        <h4>Rare Beauty Mascara</h4>
        <p>Rs 4487.00</p>
      </div>
    </div>
  </section>
 <!-- Display error message if available -->
	<c:if test="${not empty error}">
		<p class="error-message">${error}</p>
	</c:if>

	<!-- Display success message if available -->
	<c:if test="${not empty success}">
		<p class="success-message">${success}</p>
	</c:if>
  <!-- Include Footer -->
  <jsp:include page="footer.jsp" />
</body>
</html>