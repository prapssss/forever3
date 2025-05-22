<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Footer Example</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
  <footer class="footer">
    <div class="container">
      <div class="footer-content">
        <!-- Brand section -->
        <div>
          <div class="footer-logo">
            <h2>forever3</h2>
          </div>
          <p>Your destination for premium makeup and skincare products that enhance your natural beauty.</p>
        </div>
       
        
        <!-- Contact section -->
        <div>
         <a href="${pageContext.request.contextPath}/contactus">ContactUs</a>
          <div>
            <div class="contact-item">
              <span>Durbarmarg, Kathmandu</span>
            </div>
            <div class="contact-item">
              <span>9800458356</span>
            </div>
            <div class="contact-item">
              <span>support@forever3.com</span>
            </div>
          </div>
        </div>
      </div>
      
      <!-- Footer bottom -->
      <div class="footer-bottom">
        <p>&copy; <script>document.write(new Date().getFullYear())</script> BeautyEssence. All rights reserved.</p>
      </div>
    </div>
  </footer>
</body>
</html>