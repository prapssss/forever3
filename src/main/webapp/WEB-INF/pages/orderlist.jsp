<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Orders</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/orderlist.css">
</head>
<body>
<jsp:include page="header.jsp" />
    <div class="container">
        <div class="header">
            <div class="header-content">
                <h2>My Orders</h2>
                <p>View and track your order history</p>
            </div>
            <button class="shop-btn">Continue Shopping</button>
        </div>

        <div class="table-container">
            <table class="orders-table">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Total Amount</th>
                        <th>Payment Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>ORD-2023-001</td>
                        <td>May 15, 2023</td>
                        <td>$156.99</td>
                        <td><span class="status-badge paid">Paid</span></td>
                    
                    </tr>
                    <tr>
                        <td>ORD-2023-002</td>
                        <td>May 16, 2023</td>
                        <td>$89.50</td>
                        <td><span class="status-badge paid">Paid</span></td>
                    </tr>
                    <tr>
                        <td>ORD-2023-003</td>
                        <td>May 20, 2023</td>
                        <td>$245.00</td>
                        <td><span class="status-badge paid">Paid</span></td>
                    </tr>
                    <tr>
                        <td>ORD-2023-004</td>
                        <td>May 25, 2023</td>
                        <td>$32.75</td>
                        <td><span class="status-badge failed">Failed</span></td>
                    </tr>
                    <tr>
                        <td>ORD-2023-005</td>
                        <td>June 2, 2023</td>
                        <td>$178.25</td>
                        <td><span class="status-badge paid">Paid</span></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>