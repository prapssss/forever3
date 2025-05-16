<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Register Your Account</title>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>
  <div class="container">
    <div class="form-section">
      <h2>REGISTER YOUR ACCOUNT</h2>
      		<!-- Display error message if available -->
		<c:if test="${not empty error}">
			<p class="error-message">${error}</p>
		</c:if>

		<!-- Display success message if available -->
		<c:if test="${not empty success}">
			<p class="success-message">${success}</p>
		</c:if>
      <div class="form-content">
        <div class="form-fields">
          <form action="${pageContext.request.contextPath}/register" method="post"  enctype="multipart/form-data">
            <!-- First Name + Last Name -->
            <div class="row">
              <div class="form-group">
                <label for="firstname">First Name</label>
                <input type="text" id="firstname" name="firstname"/>
              </div>
              <div class="form-group">
                <label for="lastname">Last Name</label>
                <input type="text" id="lastname" name="lastname"/>
              </div>
            </div>

            <!-- Username + Phone -->
            <div class="row">
              <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username"/>
              </div>
              <div class="form-group">
                <label for="number">Phone Number</label>
                <input type="tel" id="number" name="number"/>
              </div>
            </div>

            <!-- Email -->
            <div class="row">
              <div class="form-group half-width">
                <label for="email">Email Address</label>
                <input type="email" id="email" name="email"/>
              </div>
            </div>

            <!-- Password + Retype Password -->
            <div class="row">
              <div class="form-group">
                <label for="password">Password*</label>
                <input type="password" id="password" name="password"/>
              </div>
              <div class="form-group">
                <label for="retype-password">Retype Password*</label>
                <input type="password" id="retype-password" name="retype-password"/>
              </div>
            </div>

            <!-- Profile Picture -->
            <div class="row">
              <div class="form-group half-width">
                <label for="image">Profile Picture</label>
                <input type="file" id="image" name="image"/>
              </div>
            </div>

            <button type="submit">Register A New Account</button>
          </form>

          <a href="${pageContext.request.contextPath}/login" class="login-button">Login If You Already Have An Account</a>
        </div>
      </div>
    </div>

    <!-- Right-side Registration Image -->
    <div class="image-section">
      <img src="${pageContext.request.contextPath}/resources/images/system/register.jpg" alt="Registration Image" />
    </div>
  </div>
</body>
</html>
