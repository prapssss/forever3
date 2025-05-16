<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Login | Forever3</title>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
  <div class="container">
    <div class="form-section">
      <h2>Welcome Back</h2>
      <p>Please enter your details</p>
      <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="username">Username</label>
        <input type="text" name="username" id="username" placeholder="Enter your username" />

        <label for="password">Password</label>
        <input type="password" name="password" id="password" placeholder="Enter your password" />


        <button type="submit" class="sign-in-btn">Sign in</button>
      </form>

      <div class="signup">
        Don't have an account? <a href="${pageContext.request.contextPath}/register">Sign up</a>
      </div>
    </div>

    <div class="image-section">
      <img src="${pageContext.request.contextPath}/resources/images/system/loginnewimage.jpg" alt="Skincare Visual" />
    </div>
    <!-- Display error message if available -->
		<c:if test="${not empty error}">
			<p class="error-message">${error}</p>
		</c:if>

		<!-- Display success message if available -->
		<c:if test="${not empty success}">
			<p class="success-message">${success}</p>
		</c:if>
  </div>
</body>
</html>
