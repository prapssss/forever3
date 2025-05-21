<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>
    <link rel="stylesheet" href="${contextPath}/css/productmanagement.css">
</head>
<body>
<jsp:include page="nav.jsp" />

<div class="container">
    <div class="header">
        <h2>Product Management</h2>
       <form action="addproduct" method="get">
    <button class="new-product-btn" type="submit">+ Add Product</button>
</form>

    </div>

    <div class="table-container">
        <table class="products-table">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Price</th>
                    <th>Stock</th>
                    <th>Supplier</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.itemId}</td>
                        <td>${product.itemName}</td>
                        <td>${product.categoryName}</td>
                        <td>NPR. ${product.itemPrice}</td>
                        <td>${product.stockQuantity}</td>
                        <td>${product.supplierName}</td>
                        <td>
                            <form action="editproduct" method="get" style="display:inline;">
                                <input type="hidden" name="id" value="${product.itemId}">
                                <button class="action-btn edit-btn" type="submit">Edit</button>
                            </form>

                            <form action="addstock" method="get" style="display:inline;">
                                <input type="hidden" name="id" value="${product.itemId}">
                                <button class="action-btn stock-btn" type="submit">Add Stock</button>
                            </form>

                            <!-- Delete Form - uses POST to productmanagement servlet -->
                            <form action="productmanagement" method="post" style="display:inline;" onsubmit="return confirm('Delete this product?');">
                                <input type="hidden" name="id" value="${product.itemId}">
                                <input type="hidden" name="action" value="delete">
                                <button class="action-btn delete-btn" type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
