<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management System</title>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productmanagement.css">
</head>
<body>
	<jsp:include page="nav.jsp" />
    <div class="container">
        <div class="header">
            <h2>Product Management</h2> <br> <br> 
            <p>View and manage your customer orders</p> 
                        <button class="new-order-btn">+ New Order</button>
        </div>

        <div class="filters">
            <div class="search-container">
                <input type="text" placeholder="Search orders..." class="search-input">
                <span class="search-icon">🔍</span>
            </div>
            
            <div class="filter-options">
                <select class="filter-select">
                    <option value="">Payment Status</option>
                    <option value="paid">Paid</option>
                    <option value="pending">Pending</option>
                    <option value="failed">Failed</option>
                </select>
                
                <select class="filter-select">
                    <option value="">Date Range</option>
                    <option value="today">Today</option>
                    <option value="week">This Week</option>
                    <option value="month">This Month</option>
                    <option value="year">This Year</option>
                </select>
                
                <button class="filter-btn">More Filters</button>
                <button class="export-btn">Export</button>
            </div>
        </div>

        <div class="table-container">
            <table class="orders-table">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Total Amount</th>
                        <th>Customer ID</th>
                        <th>Payment Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>ORD-2023-001</td>
                        <td>2023-05-15</td>
                        <td>$156.99</td>
                        <td>CUST-1001</td>
                        <td><span class="status-badge paid">Paid</span></td>
                        <td>
                            <div class="actions">
                                <button class="action-btn">View</button>
                                <button class="action-btn">Edit</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>ORD-2023-002</td>
                        <td>2023-05-16</td>
                        <td>$89.50</td>
                        <td>CUST-1042</td>
                        <td><span class="status-badge pending">Pending</span></td>
                        <td>
                            <div class="actions">
                                <button class="action-btn">View</button>
                                <button class="action-btn">Edit</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>ORD-2023-003</td>
                        <td>2023-05-16</td>
                        <td>$245.00</td>
                        <td>CUST-1023</td>
                        <td><span class="status-badge paid">Paid</span></td>
                        <td>
                            <div class="actions">
                                <button class="action-btn">View</button>
                                <button class="action-btn">Edit</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>ORD-2023-004</td>
                        <td>2023-05-17</td>
                        <td>$32.75</td>
                        <td>CUST-1056</td>
                        <td><span class="status-badge failed">Failed</span></td>
                        <td>
                            <div class="actions">
                                <button class="action-btn">View</button>
                                <button class="action-btn">Edit</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>ORD-2023-005</td>
                        <td>2023-05-18</td>
                        <td>$178.25</td>
                        <td>CUST-1001</td>
                        <td><span class="status-badge paid">Paid</span></td>
                        <td>
                            <div class="actions">
                                <button class="action-btn">View</button>
                                <button class="action-btn">Edit</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>ORD-2023-006</td>
                        <td>2023-05-19</td>
                        <td>$67.50</td>
                        <td>CUST-1078</td>
                        <td><span class="status-badge pending">Pending</span></td>
                        <td>
                            <div class="actions">
                                <button class="action-btn">View</button>
                                <button class="action-btn">Edit</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>ORD-2023-007</td>
                        <td>2023-05-20</td>
                        <td>$124.99</td>
                        <td>CUST-1042</td>
                        <td><span class="status-badge paid">Paid</span></td>
                        <td>
                            <div class="actions">
                                <button class="action-btn">View</button>
                                <button class="action-btn">Edit</button>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="pagination">
            <div class="pagination-info">
                Showing <span>1</span> to <span>7</span> of <span>100</span> orders
            </div>
            <div class="pagination-controls">
                <button class="pagination-btn" disabled>Previous</button>
                <button class="pagination-btn">Next</button>
            </div>
        </div>
    </div>
</body>
</html>