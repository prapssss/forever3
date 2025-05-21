<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Stock</title>
    <link rel="stylesheet" href="${contextPath}/css/addstock.css">
</head>
<body>
<jsp:include page="nav.jsp" />

<div class="container">
    <h2>Add Stock</h2>

    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>

    <form action="${contextPath}/addstock" method="post">
        <input type="hidden" name="itemId" value="${product.itemId}" />

        <p>Product Name: <strong>${product.itemName}</strong></p>
        <p>Current Stock: <strong>${product.stockQuantity}</strong></p>

        <label for="additionalStock">Additional Stock:</label>
        <input type="number" name="additionalStock" id="additionalStock" min="1" required />

        <button type="submit">Update Stock</button>
    </form>

    <a href="${contextPath}/productmanagement">Back to Product Management</a>
</div>
</body>
</html>
