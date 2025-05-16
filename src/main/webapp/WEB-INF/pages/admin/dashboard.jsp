<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>
<jsp:include page="nav.jsp" />
    
    <div class="main-content">
        <div class="page-header">
            <h1 class="welcome-message">Welcome, Admin!</h1>
        </div>

        <div class="dashboard-cards">
            <div class="card">
                <h3 class="card-title">Total Customers</h3>
                <div class="card-value">248</div>
                
            </div>
            <div class="card">
                <h3 class="card-title">Total Orders</h3>
                <div class="card-value">897</div>

            </div>
            <div class="card">
                <h3 class="card-title">Total Products</h3>
                <div class="card-value">54</div>
                
            </div>
        </div>

        <h2 class="section-title">Recent Customers</h2>
        <div class="table-container">
            <table class="customers-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Customer Name</th>
                        <th>Email</th>
                        
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>John Doe</td>
                        <td>john.doe@example.com</td>
                        
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Jane Smith</td>
                        <td>jane.smith@example.com</td>
                        
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Robert Johnson</td>
                        <td>robert.j@example.com</td>
                        
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>Emily Wilson</td>
                        <td>emily.w@example.com</td>
                        
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>Michael Brown</td>
                        <td>michael.b@example.com</td>
                       
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>