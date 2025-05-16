<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile - Forever3</title>
    <link rel="stylesheet" href="css/profile.css">
</head>
<body>
 
<div class="profile-container">
    <div class="profile-image">
        <img src="<c:url value='/getImage?username=${customer.username}' />" alt="Profile Picture" id="profilePic">
    </div>
    <div class="profile-details">
        <form id="profileForm" action="updateProfile" method="post" enctype="multipart/form-data">
            <input type="hidden" name="username" value="${customer.username}" />
 
            <input type="text" name="firstName" value="${customer.firstName}" disabled>
            <input type="text" name="lastName" value="${customer.lastName}" disabled>
            <input type="email" name="email" value="${customer.email}" disabled>
            <input type="text" name="phone" value="${customer.phone}" disabled>
            <input type="text" name="address" value="${customer.address}" disabled>
 
            <!-- Profile Picture Upload -->
            <input type="file" name="profileImage" disabled>
 
            <div class="button-group">
                <button type="button" id="editBtn" onclick="enableEditing()">Edit Profile</button>
                <button type="submit" id="saveBtn" style="display: none;">Save Changes</button>
            </div>
        </form>
    </div>
</div>
 
<script>
    function enableEditing() {
        const inputs = document.querySelectorAll('#profileForm input');
        inputs.forEach(input => input.disabled = false);
 
        // Show Save Changes button
        document.getElementById('saveBtn').style.display = 'inline-block';
 
        // Hide Edit button
        document.getElementById('editBtn').style.display = 'none';
    }
</script>
 
</body>
</html>