<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment - Forever3</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/payment.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">
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

            <div class="shipping-details">
                <h2>Shipping Details</h2>
                <div class="shipping-info">
                    <p><strong>Name:</strong> ${sessionScope.shippingName}</p>
                    <p><strong>Address:</strong> ${sessionScope.shippingAddress}</p>
                    <p><strong>City:</strong> ${sessionScope.shippingCity}</p>
                    <p><strong>Postal Code:</strong> ${sessionScope.shippingPostalCode}</p>
                    <p><strong>Phone:</strong> ${sessionScope.shippingPhone}</p>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/payment" method="post" class="payment-form">
                <input type="hidden" name="orderId" value="${orderId}">
                <input type="hidden" name="amount" value="${totalAmount}">
                
                <div class="form-group">
                    <label for="customerName">Full Name</label>
                    <input type="text" id="customerName" name="customerName" required 
                           placeholder="Enter your full name">
                </div>

                <div class="form-group">
                    <label for="location">Delivery Location</label>
                    <input type="text" id="location" name="location" required 
                           placeholder="Enter your delivery location">
                </div>

                <div class="form-group">
                    <label>Select Payment Method</label>
                    <div class="payment-methods-container">
                        <div class="payment-method-option">
                            <input type="radio" id="esewa" name="paymentMethod" value="eSewa" required>
                            <label for="esewa" class="payment-method-label">
                                <div class="payment-method-content">
                                    <div class="payment-method-icon">eSewa</div>
                                    <div class="payment-method-text">Pay with eSewa</div>
                                </div>
                            </label>
                        </div>
                        <div class="payment-method-option">
                            <input type="radio" id="khalti" name="paymentMethod" value="Khalti" required>
                            <label for="khalti" class="payment-method-label">
                                <div class="payment-method-content">
                                    <div class="payment-method-icon">Khalti</div>
                                    <div class="payment-method-text">Pay with Khalti</div>
                                </div>
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="mobileNumber">Mobile Number</label>
                    <input type="tel" id="mobileNumber" name="mobileNumber" required 
                           maxlength="10" placeholder="98XXXXXXXX"
                           pattern="[0-9]{10}">
                </div>

                <button type="submit" class="pay-button">Pay NPR ${totalAmount}</button>
            </form>
        </div>
    </main>
    <jsp:include page="footer.jsp" />
</body>
</html> 