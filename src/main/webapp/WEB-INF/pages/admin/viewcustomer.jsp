<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .customer-status {
            padding: 0.25rem 0.5rem;
            border-radius: 0.25rem;
            font-size: 0.875rem;
            font-weight: 500;
        }
        .status-active {
            background-color: #d4edda;
            color: #155724;
        }
        .status-inactive {
            background-color: #f8d7da;
            color: #721c24;
        }
    </style>
</head>
<body>
    <div class="container py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Customer Details</h2>
            <div>
                <a href="${contextPath}/customers" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Back to Customers
                </a>
                <a href="${contextPath}/customers/edit?id=${customer.customerId}" class="btn btn-primary">
                    <i class="fas fa-edit"></i> Edit Customer
                </a>
            </div>
        </div>

        <div class="row">
            <!-- Customer Information -->
            <div class="col-md-6 mb-4">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title mb-0">Customer Information</h5>
                    </div>
                    <div class="card-body">
                        <div class="row mb-3">
                            <div class="col-sm-4 fw-bold">Customer ID:</div>
                            <div class="col-sm-8">#${customer.customerId}</div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4 fw-bold">Name:</div>
                            <div class="col-sm-8">${customer.customerName}</div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4 fw-bold">Email:</div>
                            <div class="col-sm-8">${customer.email}</div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4 fw-bold">Phone:</div>
                            <div class="col-sm-8">${customer.phoneNo}</div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4 fw-bold">Gender:</div>
                            <div class="col-sm-8">${customer.gender}</div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4 fw-bold">Status:</div>
                            <div class="col-sm-8">
                                <span class="customer-status status-${customer.active ? 'active' : 'inactive'}">
                                    ${customer.active ? 'Active' : 'Inactive'}
                                </span>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4 fw-bold">Member Since:</div>
                            <div class="col-sm-8">
                                <fmt:formatDate value="${customer.createdAt}" pattern="yyyy-MM-dd"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Address Information -->
            <div class="col-md-6 mb-4">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title mb-0">Address Information</h5>
                    </div>
                    <div class="card-body">
                        <div class="row mb-3">
                            <div class="col-sm-4 fw-bold">Address:</div>
                            <div class="col-sm-8">${customer.address}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Recent Orders -->
        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h5 class="card-title mb-0">Recent Orders</h5>
                <a href="${contextPath}/customers/orders?id=${customer.customerId}" class="btn btn-sm btn-primary">
                    View All Orders
                </a>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Date</th>
                                <th>Total Amount</th>
                                <th>Payment Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${orders}" var="order">
                                <tr>
                                    <td>#${order.orderId}</td>
                                    <td>
                                        <fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd"/>
                                    </td>
                                    <td>
                                        <fmt:formatNumber value="${order.totalOrderAmount}" type="currency" currencySymbol="$"/>
                                    </td>
                                    <td>
                                        <span class="order-status status-${order.paymentStatus.toLowerCase()}">
                                            ${order.paymentStatus}
                                        </span>
                                    </td>
                                    <td>
                                        <a href="${contextPath}/orders/view?id=${order.orderId}" 
                                           class="btn btn-sm btn-info">
                                            <i class="fas fa-eye"></i> View
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty orders}">
                                <tr>
                                    <td colspan="5" class="text-center">No orders found</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Delete Customer -->
        <div class="card mt-4">
            <div class="card-body">
                <div class="d-flex justify-content-end">
                    <button type="button" class="btn btn-danger" onclick="confirmDelete()">
                        <i class="fas fa-trash"></i> Delete Customer
                    </button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function confirmDelete() {
            if (confirm('Are you sure you want to delete this customer? This action cannot be undone.')) {
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = '${contextPath}/customers/delete';
                
                const input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'id';
                input.value = '${customer.customerId}';
                
                form.appendChild(input);
                document.body.appendChild(form);
                form.submit();
            }
        }
    </script>
</body>
</html>