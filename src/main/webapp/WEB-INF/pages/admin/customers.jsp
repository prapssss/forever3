<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Customer Management</title>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/customers.css">
</head>
<body>
    <jsp:include page="/WEB-INF/pages/admin/nav.jsp" />
 
        
        <div class="admin-content">
            <div class="admin-header">
                <h1>Customer Management</h1>
                <div class="header-actions">
                    <button class="add-new-btn">+ Add New Customer</button>
                </div>
            </div>

            <div class="admin-filters">
                <div class="search-container">
                    <input type="text" placeholder="Search customers..." class="search-input">
                    <button class="search-btn">Search</button>
                </div>
                <div class="filter-container">
                    <select class="filter-select">
                        <option value="">All Customers</option>
                        <option value="active">Active</option>
                        <option value="inactive">Inactive</option>
                        <option value="new">New (Last 30 days)</option>
                    </select>
                    <button class="filter-btn">Filter</button>
                </div>
            </div>

            <div class="customer-list">
                <div class="list-header">
                    <div class="header-item customer-name-header">Customer Name</div>
                    <div class="header-item">Email</div>
                    <div class="header-item">Phone</div>
                    <div class="header-item">Orders</div>
                    <div class="header-item">Status</div>
                    <div class="header-item">Actions</div>
                </div>

                <!-- Customer 1 -->
                <div class="customer-item">
                    <div class="customer-name">
                        <div class="customer-avatar">JD</div>
                        <div>John Doe</div>
                    </div>
                    <div class="customer-email">johndoe@example.com</div>
                    <div class="customer-phone">+1 (555) 123-4567</div>
                    <div class="customer-orders">5</div>
                    <div class="customer-status">
                        <span class="status-badge active">Active</span>
                    </div>
                    <div class="customer-actions">
                        <a href="edit-customer.html?id=1" class="edit-btn">Edit</a>
                        <button class="delete-btn">Delete</button>
                    </div>
                </div>

                <!-- Customer 2 -->
                <div class="customer-item">
                    <div class="customer-name">
                        <div class="customer-avatar">JS</div>
                        <div>Jane Smith</div>
                    </div>
                    <div class="customer-email">janesmith@example.com</div>
                    <div class="customer-phone">+1 (555) 987-6543</div>
                    <div class="customer-orders">3</div>
                    <div class="customer-status">
                        <span class="status-badge active">Active</span>
                    </div>
                    <div class="customer-actions">
                        <a href="edit-customer.html?id=2" class="edit-btn">Edit</a>
                        <button class="delete-btn">Delete</button>
                    </div>
                </div>

                <!-- Customer 3 -->
                <div class="customer-item">
                    <div class="customer-name">
                        <div class="customer-avatar">RJ</div>
                        <div>Robert Johnson</div>
                    </div>
                    <div class="customer-email">robert@example.com</div>
                    <div class="customer-phone">+1 (555) 456-7890</div>
                    <div class="customer-orders">0</div>
                    <div class="customer-status">
                        <span class="status-badge inactive">Inactive</span>
                    </div>
                    <div class="customer-actions">
                        <a href="edit-customer.html?id=3" class="edit-btn">Edit</a>
                        <button class="delete-btn">Delete</button>
                    </div>
                </div>

                <!-- Customer 4 -->
                <div class="customer-item">
                    <div class="customer-name">
                        <div class="customer-avatar">MW</div>
                        <div>Maria Williams</div>
                    </div>
                    <div class="customer-email">maria@example.com</div>
                    <div class="customer-phone">+1 (555) 234-5678</div>
                    <div class="customer-orders">8</div>
                    <div class="customer-status">
                        <span class="status-badge active">Active</span>
                    </div>
                    <div class="customer-actions">
                        <a href="edit-customer.html?id=4" class="edit-btn">Edit</a>
                        <button class="delete-btn">Delete</button>
                    </div>
                </div>

                <!-- Customer 5 -->
                <div class="customer-item">
                    <div class="customer-name">
                        <div class="customer-avatar">DB</div>
                        <div>David Brown</div>
                    </div>
                    <div class="customer-email">david@example.com</div>
                    <div class="customer-phone">+1 (555) 876-5432</div>
                    <div class="customer-orders">2</div>
                    <div class="customer-status">
                        <span class="status-badge new">New</span>
                    </div>
                    <div class="customer-actions">
                        <a href="edit-customer.html?id=5" class="edit-btn">Edit</a>
                        <button class="delete-btn">Delete</button>
                    </div>
                </div>
            </div>

            <div class="pagination">
                <button class="pagination-btn" disabled>Previous</button>
                <div class="page-numbers">
                    <button class="page-number active">1</button>
                    <button class="page-number">2</button>
                    <button class="page-number">3</button>
                </div>
                <button class="pagination-btn">Next</button>
            </div>
        </div>
  

    <!-- Delete Confirmation Modal -->
    <div class="modal" id="deleteModal">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Confirm Deletion</h3>
                <button class="close-modal">&times;</button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete this customer? This action cannot be undone.</p>
            </div>
            <div class="modal-footer">
                <button class="cancel-btn">Cancel</button>
                <button class="confirm-delete-btn">Delete Customer</button>
            </div>
        </div>
    </div>
</body>
</html>