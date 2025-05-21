<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment - Forever3</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/payment.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    <main>
        <div class="payment-container">
            <h1>Payment Details</h1>
            
            <c:if test="${not empty sessionScope.errorMessage}">
                <div class="error-message">
                    ${sessionScope.errorMessage}
                </div>
                <c:remove var="errorMessage" scope="session"/>
            </c:if>

            <div class="order-summary">
                <h2>Order Summary</h2>
                <p>Total Amount: NPR ${totalAmount}</p>
            </div>

            <form action="${pageContext.request.contextPath}/payment" method="post" class="payment-form">
                <input type="hidden" name="orderId" value="${orderId}">
                <input type="hidden" name="amount" value="${totalAmount}">
                
                <div class="form-group">
                    <label for="paymentMethod">Select Payment Method</label>
                    <select name="paymentMethod" id="paymentMethod" required>
                        <option value="">Choose Payment Method</option>
                        <option value="eSewa">eSewa</option>
                        <option value="Khalti">Khalti</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="mobileNumber">Mobile Number</label>
                    <input type="text" id="mobileNumber" name="mobileNumber" required 
                           pattern="[0-9]{10}" maxlength="10" placeholder="98XXXXXXXX"
                           title="Please enter your 10-digit mobile number">
                </div>

                <button type="submit" class="pay-button">Pay NPR ${totalAmount}</button>
            </form>
        </div>
    </main>
    <jsp:include page="footer.jsp" />

    <script>
        // Format mobile number
        document.getElementById('mobileNumber').addEventListener('input', function(e) {
            let value = e.target.value.replace(/\D/g, '');
            e.target.value = value;
        });
    </script>
</body>
</html> 