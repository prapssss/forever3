<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Edit Customer</title>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/customers.css">
</head>
<body>
	<jsp:include page="nav.jsp" />
   <div class="admin-content">
            <div class="admin-header">
                <h1>Edit Customer</h1>
                <div class="header-actions">
                    <a href="admin-customers.html" class="back-btn">← Back to Customers</a>
                </div>
            </div>

            <div class="edit-customer-container">
                <div class="customer-profile-header">
                    <div class="customer-avatar large">JD</div>
                    <div class="customer-profile-info">
                        <h2>John Doe</h2>
                        <p>Customer since: January 15, 2023</p>
                        <p>Customer ID: CUST-1001</p>
                    </div>
                    <div class="customer-status-toggle">
                        <label class="switch">
                            <input type="checkbox" checked>
                            <span class="slider round"></span>
                        </label>
                        <span class="status-label">Active</span>
                    </div>
                </div>

                <div class="edit-form-container">
                    <form class="edit-customer-form">
                        <div class="form-tabs">
                            <button type="button" class="form-tab active" data-tab="personal">Personal Information</button>
                            <button type="button" class="form-tab" data-tab="address">Address</button>
                            <button type="button" class="form-tab" data-tab="orders">Orders</button>
                            <button type="button" class="form-tab" data-tab="notes">Notes</button>
                        </div>

                        <div class="form-tab-content active" id="personal">
                            <div class="form-section">
                                <h3>Personal Information</h3>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="firstName">First Name*</label>
                                        <input type="text" id="firstName" value="John" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="lastName">Last Name*</label>
                                        <input type="text" id="lastName" value="Doe" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="email">Email Address*</label>
                                        <input type="email" id="email" value="johndoe@example.com" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone">Phone Number*</label>
                                        <input type="tel" id="phone" value="+1 (555) 123-4567" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="birthdate">Date of Birth</label>
                                        <input type="date" id="birthdate" value="1985-06-15">
                                    </div>
                                    <div class="form-group">
                                        <label for="customerGroup">Customer Group</label>
                                        <select id="customerGroup">
                                            <option value="regular" selected>Regular</option>
                                            <option value="vip">VIP</option>
                                            <option value="wholesale">Wholesale</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-section">
                                <h3>Account Information</h3>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="username">Username</label>
                                        <input type="text" id="username" value="johndoe123">
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input type="password" id="password" value="********">
                                        <button type="button" class="reset-password-btn">Reset Password</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-tab-content" id="address">
                            <div class="form-section">
                                <h3>Billing Address</h3>
                                <div class="form-group full-width">
                                    <label for="billingAddress">Street Address*</label>
                                    <input type="text" id="billingAddress" value="123 Main Street, Apt 4B">
                                </div>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="billingCity">City*</label>
                                        <input type="text" id="billingCity" value="New York">
                                    </div>
                                    <div class="form-group">
                                        <label for="billingState">State/Province*</label>
                                        <input type="text" id="billingState" value="NY">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="billingZip">ZIP/Postal Code*</label>
                                        <input type="text" id="billingZip" value="10001">
                                    </div>
                                    <div class="form-group">
                                        <label for="billingCountry">Country*</label>
                                        <select id="billingCountry">
                                            <option value="US" selected>United States</option>
                                            <option value="CA">Canada</option>
                                            <option value="UK">United Kingdom</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-section">
                                <div class="section-header">
                                    <h3>Shipping Address</h3>
                                    <div class="form-group checkbox-group">
                                        <input type="checkbox" id="sameAsBilling" checked>
                                        <label for="sameAsBilling">Same as billing address</label>
                                    </div>
                                </div>
                                <div class="shipping-address-fields" style="display: none;">
                                    <div class="form-group full-width">
                                        <label for="shippingAddress">Street Address*</label>
                                        <input type="text" id="shippingAddress" value="123 Main Street, Apt 4B">
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group">
                                            <label for="shippingCity">City*</label>
                                            <input type="text" id="shippingCity" value="New York">
                                        </div>
                                        <div class="form-group">
                                            <label for="shippingState">State/Province*</label>
                                            <input type="text" id="shippingState" value="NY">
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group">
                                            <label for="shippingZip">ZIP/Postal Code*</label>
                                            <input type="text" id="shippingZip" value="10001">
                                        </div>
                                        <div class="form-group">
                                            <label for="shippingCountry">Country*</label>
                                            <select id="shippingCountry">
                                                <option value="US" selected>United States</option>
                                                <option value="CA">Canada</option>
                                                <option value="UK">United Kingdom</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-tab-content" id="orders">
                            <div class="form-section">
                                <h3>Order History</h3>
                                <div class="order-history-table">
                                    <div class="order-history-header">
                                        <div class="order-header-item">Order ID</div>
                                        <div class="order-header-item">Date</div>
                                        <div class="order-header-item">Total</div>
                                        <div class="order-header-item">Status</div>
                                        <div class="order-header-item">Actions</div>
                                    </div>
                                    <div class="order-history-item">
                                        <div class="order-item-id">ORD-2023-001</div>
                                        <div class="order-item-date">May 15, 2023</div>
                                        <div class="order-item-total">$156.99</div>
                                        <div class="order-item-status">
                                            <span class="status-badge delivered">Delivered</span>
                                        </div>
                                        <div class="order-item-actions">
                                            <button class="view-order-btn">View</button>
                                        </div>
                                    </div>
                                    <div class="order-history-item">
                                        <div class="order-item-id">ORD-2023-002</div>
                                        <div class="order-item-date">June 22, 2023</div>
                                        <div class="order-item-total">$89.50</div>
                                        <div class="order-item-status">
                                            <span class="status-badge delivered">Delivered</span>
                                        </div>
                                        <div class="order-item-actions">
                                            <button class="view-order-btn">View</button>
                                        </div>
                                    </div>
                                    <div class="order-history-item">
                                        <div class="order-item-id">ORD-2023-003</div>
                                        <div class="order-item-date">July 10, 2023</div>
                                        <div class="order-item-total">$245.00</div>
                                        <div class="order-item-status">
                                            <span class="status-badge shipped">Shipped</span>
                                        </div>
                                        <div class="order-item-actions">
                                            <button class="view-order-btn">View</button>
                                        </div>
                                    </div>
                                    <div class="order-history-item">
                                        <div class="order-item-id">ORD-2023-004</div>
                                        <div class="order-item-date">August 5, 2023</div>
                                        <div class="order-item-total">$32.75</div>
                                        <div class="order-item-status">
                                            <span class="status-badge processing">Processing</span>
                                        </div>
                                        <div class="order-item-actions">
                                            <button class="view-order-btn">View</button>
                                        </div>
                                    </div>
                                    <div class="order-history-item">
                                        <div class="order-item-id">ORD-2023-005</div>
                                        <div class="order-item-date">September 18, 2023</div>
                                        <div class="order-item-total">$178.25</div>
                                        <div class="order-item-status">
                                            <span class="status-badge processing">Processing</span>
                                        </div>
                                        <div class="order-item-actions">
                                            <button class="view-order-btn">View</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-tab-content" id="notes">
                            <div class="form-section">
                                <h3>Customer Notes</h3>
                                <div class="form-group full-width">
                                    <label for="customerNotes">Admin Notes</label>
                                    <textarea id="customerNotes" rows="6">Customer prefers email communication. Has mentioned interest in premium products. Eligible for loyalty discount on next purchase.</textarea>
                                </div>
                            </div>
                        </div>

                        <div class="form-actions">
                            <button type="button" class="cancel-btn">Cancel</button>
                            <button type="submit" class="save-btn">Save Changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
   </body>
</html>