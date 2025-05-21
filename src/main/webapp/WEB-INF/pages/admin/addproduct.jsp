<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Product</title>

    <style>
        @charset "UTF-8";

        /* Global reset and base font */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        /* Body and overall page layout */
        body {
            background-color: #f5f3ef; /* NEW BACKGROUND COLOR */
            margin: 0;
            padding: 15px;

            /* Flexbox for centering content (h1, form, link) */
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
            min-height: 100vh;
        }

        /* Page Title */
        h1 {
            color: #333;
            margin-bottom: 15px;
            text-align: center;
            width: 100%;
        }

        /* Form Container */
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
            box-sizing: border-box;
            margin-top: 15px;
            margin-bottom: 15px;
        }

        /* Labels for form fields */
        label {
            display: block;
            margin-bottom: 4px;
            color: #555;
            font-weight: bold;
            font-size: 0.95em;
        }

        /* Common styles for text inputs, number inputs, and textareas */
        input[type="text"],
        input[type="number"],
        textarea {
            width: calc(100% - 16px);
            padding: 7px;
            margin-bottom: 9px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }

        /* Specific style for textarea */
        textarea {
            resize: vertical;
            min-height: 55px;
        }

        /* File input field */
        input[type="file"] {
            margin-bottom: 9px;
            width: 100%;
            font-size: 14px;
        }

        /* Submit button */
        button[type="submit"] {
            background-color:#c7958a; /* NEW BUTTON COLOR */
            color: #333; /* Adjusted for better contrast with the new button color */
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 15px;
            transition: background-color 0.3s ease;
            width: 100%;
            margin-top: 5px;
        }

        button[type="submit"]:hover {
            background-color: #c7958a; /* Slightly darker on hover */
        }

        /* Error Message display */
        .errorMessage {
            color: red;
            background-color: #ffe0e0;
            border: 1px solid red;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 4px;
            text-align: center;
            width: 100%;
            max-width: 450px;
            box-sizing: border-box;
            font-size: 14px;
        }

        /* Back link */
        a {
            display: block;
            margin-top: 15px;
            text-align: center;
            color: #000; /* You might want to adjust this if it clashes with the new scheme */
            text-decoration: none;
            font-size: 15px;
            width: 100%;
            max-width: 450px;
            box-sizing: border-box;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>

</head>
<body>
    <%-- Include the navigation bar --%>
    <jsp:include page="/WEB-INF/pages/admin/nav.jsp" />

    <h1>Add New Product</h1>

    <c:if test="${not empty errorMessage}">
        <div class="errorMessage">${errorMessage}</div>
    </c:if>

    <form action="addproduct" method="post" enctype="multipart/form-data">
        <label>Product Name:</label>
        <input type="text" name="itemName" required />

        <label>Description:</label>
        <textarea name="description" required></textarea>

        <label>Category ID:</label>
        <input type="number" name="categoryId" required />

        <label>Price:</label>
        <input type="number" step="0.01" name="itemPrice" required />

        <label>Stock Quantity:</label>
        <input type="number" name="stockQuantity" required />

        <label>Supplier ID:</label>
        <input type="number" name="supplierId" required />

        <label>Upload Image:</label>
        <input type="file" name="imageFile" accept="image/*" />

        <button type="submit">Add Product</button>
    </form>

    <a href="productmanagement">Back to Product Management</a>
</body>
</html>