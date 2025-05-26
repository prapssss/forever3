<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile - Forever3</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <main>
        <div class="profile-container">
            <h1>My Profile</h1>
            
            <!-- Display success/error messages -->
            <c:if test="${not empty sessionScope.successMessage}">
                <div class="success-message">${sessionScope.successMessage}</div>
                <c:remove var="successMessage" scope="session"/>
            </c:if>
            <c:if test="${not empty sessionScope.errorMessage}">
                <div class="error-message">${sessionScope.errorMessage}</div>
                <c:remove var="errorMessage" scope="session"/>
            </c:if>
            
            <div class="profile-content">
                <div class="profile-image">
                   <img src="${pageContext.request.contextPath}/resources/images/customer/${user.imageUrl}" alt="Profile Picture" id="profilePic">
                    <div class="image-upload">
                        <label for="profileImage" class="upload-btn">Change Photo</label>
                        <input type="file" id="profileImage" name="profileImage" accept="image/*" style="display: none;">
                    </div>
                </div>

                <div class="profile-details">
                    <form id="profileForm" action="${pageContext.request.contextPath}/profile/update" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="firstName">First Name</label>
                            <input type="text" id="firstName" name="first_name" value="${user.firstName}" disabled>
                        </div>

                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <input type="text" id="lastName" name="last_name" value="${user.lastName}" disabled>
                        </div>

                        <div class="form-group">
                            <label for="userName">Username</label>
                            <input type="text" id="userName" name="user_name" value="${user.userName}" disabled>
                        </div>

                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" value="${user.email}" disabled>
                        </div>

                        <div class="form-group">
                            <label for="number">Phone Number</label>
                            <input type="tel" id="number" name="number" value="${user.number}" disabled>
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" id="password" name="password" placeholder="Enter new password" disabled>
                        </div>

                        <!-- Hidden input to track selected image file -->
                        <input type="file" id="hiddenProfileImage" name="profileImage" accept="image/*" style="display: none;">

                        <div class="button-group">
                            <button type="button" id="editBtn" class="edit-btn">Edit Profile</button>
                            <button type="submit" id="saveBtn" class="save-btn" style="display: none;">Save Changes</button>
                            <button type="button" id="cancelBtn" class="cancel-btn" style="display: none;">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="footer.jsp" />

    <script>
        let originalValues = {};
        let selectedFile = null;

        function enableEditing() {
            const inputs = document.querySelectorAll('#profileForm input:not([name="user_name"])');
            
            // Store original values
            inputs.forEach(input => {
                if (input.type !== 'file' && input.type !== 'password') {
                    originalValues[input.name] = input.value;
                }
            });
            
            inputs.forEach(input => input.disabled = false);
            
            document.getElementById('editBtn').style.display = 'none';
            document.getElementById('saveBtn').style.display = 'inline-block';
            document.getElementById('cancelBtn').style.display = 'inline-block';
        }

        function disableEditing() {
            const inputs = document.querySelectorAll('#profileForm input');
            
            // Restore original values
            Object.keys(originalValues).forEach(name => {
                const input = document.querySelector(`input[name="${name}"]`);
                if (input) {
                    input.value = originalValues[name];
                }
            });
            
            // Reset password field
            document.getElementById('password').value = '';
            
            // Reset image if changed
            if (selectedFile) {
                document.getElementById('profilePic').src = "${pageContext.request.contextPath}${user.imageUrl}";
                selectedFile = null;
                document.getElementById('profileImage').value = '';
                document.getElementById('hiddenProfileImage').value = '';
            }
            
            inputs.forEach(input => input.disabled = true);
            
            document.getElementById('editBtn').style.display = 'inline-block';
            document.getElementById('saveBtn').style.display = 'none';
            document.getElementById('cancelBtn').style.display = 'none';
        }

        document.getElementById('editBtn').addEventListener('click', enableEditing);
        document.getElementById('cancelBtn').addEventListener('click', disableEditing);

        // Handle image preview and file selection
        document.getElementById('profileImage').addEventListener('change', function(e) {
            const file = e.target.files[0];
            if (file) {
                selectedFile = file;
                const reader = new FileReader();
                reader.onload = function(e) {
                    document.getElementById('profilePic').src = e.target.result;
                }
                reader.readAsDataURL(file);
                
                // Copy the file to the hidden input in the form
                const hiddenInput = document.getElementById('hiddenProfileImage');
                const dataTransfer = new DataTransfer();
                dataTransfer.items.add(file);
                hiddenInput.files = dataTransfer.files;
            }
        });

        // Form submission handler
        document.getElementById('profileForm').addEventListener('submit', function(e) {
            // Ensure the selected file is included in the form submission
            if (selectedFile) {
                const hiddenInput = document.getElementById('hiddenProfileImage');
                const dataTransfer = new DataTransfer();
                dataTransfer.items.add(selectedFile);
                hiddenInput.files = dataTransfer.files;
            }
        });
    </script>
</body>
</html>