<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Contact Us</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/contactus.css">
  
</head>
<body>
 <%@ include file="header.jsp" %>
  <section class="contact-section">
    <div class="contact-header">
      <h1>Contact Us</h1>
      <p>
        Have questions, feedback, or simply want to connect? We would love to hear
        from you. Let us make your skin glow together.
      </p>
    </div>

    <div class="contact-form-wrapper">
      <form class="contact-form">
        <label for="email">Your Email Address</label>
        <input type="email" id="email" placeholder="you@example.com" required />

        <label for="subject">Subject</label>
        <input type="text" id="subject" placeholder="Subject" required />

        <label for="message">How can we help?</label>
        <textarea id="message" rows="5" placeholder="Type your message here..." required></textarea>

        <button type="submit">Send Message</button>
      </form>
    </div>
  </section>
   <%@ include file="footer.jsp" %>
</body>
</html>
  