<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Product</title>
</head>
<body style="font-family: Arial, sans-serif; background-color: #f5f3ef; margin: 0; padding: 0; display: flex; flex-direction: column; align-items: center; min-height: 100vh;">

<h1 style="color: #333; margin-top: 15px; margin-bottom: 15px; text-align: center; width: 100%; max-width: 550px; font-size: 1.8em;">Edit Product</h1>

<c:if test="${not empty errorMessage}">
    <div style="color: red; background-color: #ffe6e6; border: 1px solid #ffb3b3; padding: 8px; border-radius: 4px; margin-bottom: 10px; text-align: left; width: 100%; max-width: 530px; font-size: 0.9em;">${errorMessage}</div>
</c:if>
<jsp:include page="/WEB-INF/pages/admin/nav.jsp" />
<form action="editproduct" method="post" enctype="multipart/form-data" style="background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1); width: 100%; max-width: 550px; margin-top: 0; text-align: left; display: flex; flex-direction: column; gap: 8px;">
    <input type="hidden" name="itemId" value="${product.itemId}" />
    <input type="hidden" name="existingImage" value="${product.imageUrl}" />

    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 3px;">
        <label style="font-weight: bold; color: #555; font-size: 0.85em; flex-shrink: 0; min-width: 90px;">Product Name:</label>
        <input type="text" name="itemName" value="${product.itemName}" required style="flex-grow: 1; padding: 6px 8px; border: 1px solid #ddd; border-radius: 3px; font-size: 0.85em;" />
    </div>

    <div style="display: flex; align-items: flex-start; gap: 10px; margin-bottom: 3px;">
        <label style="font-weight: bold; color: #555; font-size: 0.85em; flex-shrink: 0; min-width: 90px;">Description:</label>
        <textarea name="description" required style="flex-grow: 1; padding: 6px 8px; border: 1px solid #ddd; border-radius: 3px; font-size: 0.85em; resize: vertical; min-height: 50px;"></textarea>
    </div>

    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 3px;">
        <label style="font-weight: bold; color: #555; font-size: 0.85em; flex-shrink: 0; min-width: 90px;">Category ID:</label>
        <input type="number" name="categoryId" value="${product.categoryId}" required style="flex-grow: 1; padding: 6px 8px; border: 1px solid #ddd; border-radius: 3px; font-size: 0.85em;" />
    </div>

    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 3px;">
        <label style="font-weight: bold; color: #555; font-size: 0.85em; flex-shrink: 0; min-width: 90px;">Price:</label>
        <input type="number" step="0.01" name="itemPrice" value="${product.itemPrice}" required style="flex-grow: 1; padding: 6px 8px; border: 1px solid #ddd; border-radius: 3px; font-size: 0.85em;" />
    </div>

    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 3px;">
        <label style="font-weight: bold; color: #555; font-size: 0.85em; flex-shrink: 0; min-width: 90px;">Stock Quantity:</label>
        <input type="number" name="stockQuantity" value="${product.stockQuantity}" required style="flex-grow: 1; padding: 6px 8px; border: 1px solid #ddd; border-radius: 3px; font-size: 0.85em;" />
    </div>

    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 3px;">
        <label style="font-weight: bold; color: #555; font-size: 0.85em; flex-shrink: 0; min-width: 90px;">Supplier ID:</label>
        <input type="number" name="supplierId" value="${product.supplierId}" required style="flex-grow: 1; padding: 6px 8px; border: 1px solid #ddd; border-radius: 3px; font-size: 0.85em;" />
    </div>

    

    <div style="display: flex; flex-direction: column; margin-bottom: 8px;">
        <label style="font-weight: bold; color: #555; margin-bottom: 3px; display: block; font-size: 0.85em;">Upload New Image (optional):</label>
        <input type="file" name="imageFile" accept="image/*" style="padding: 2px 0; margin-bottom: 5px; font-size: 0.85em;" />
    </div>

    <button type="submit" style="background-color: #c7958a; color:#000; padding: 8px 15px; border: 1px solid rgba(181, 159, 137, 0.5); border-radius: 4px; cursor: pointer; font-size: 0.95em; transition: background-color 0.3s ease; align-self: center; width: auto; min-width: 120px; margin-top: 5px;">Update Product</button>
</form>

<a href="productmanagement" style="color:#000; text-decoration: none; font-weight: bold; margin-top: 15px; display: inline-block; font-size: 0.9em;">Back to Product Management</a>

</body>
</html>