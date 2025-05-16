<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Shopping Cart</title>
    <link rel="stylesheet" href="cart.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>Your Shopping Cart</h1>
            <p>Review your items and proceed to checkout</p>
        </div>

        <div class="cart-container">
            <div class="cart-items">
                <div class="cart-header">
                    <div class="header-product">Product</div>
                    <div class="header-price">Price</div>
                    <div class="header-quantity">Quantity</div>
                    <div class="header-total">Total</div>
                    <div class="header-action"></div>
                </div>

                <!-- Cart Item 1 -->
                <div class="cart-item">
                    <div class="item-product">
                        <div class="product-image">
                            <div class="image-placeholder">Image</div>
                        </div>
                        <div class="product-details">
                            <h3>Premium Leather Wallet</h3>
                            <p class="product-id">Item ID: ITEM-001</p>
                            <p class="product-variant">Color: Brown</p>
                        </div>
                    </div>
                    <div class="item-price">$89.99</div>
                    <div class="item-quantity">
                        <div class="quantity-selector">
                            <button class="quantity-btn minus">−</button>
                            <input type="number" value="1" min="1" class="quantity-input">
                            <button class="quantity-btn plus">+</button>
                        </div>
                    </div>
                    <div class="item-total">$89.99</div>
                    <div class="item-action">
                        <button class="remove-btn">✕</button>
                    </div>
                </div>

                <!-- Cart Item 2 -->
                <div class="cart-item">
                    <div class="item-product">
                        <div class="product-image">
                            <div class="image-placeholder">Image</div>
                        </div>
                        <div class="product-details">
                            <h3>Stainless Steel Water Bottle</h3>
                            <p class="product-id">Item ID: ITEM-042</p>
                            <p class="product-variant">Color: Silver</p>
                        </div>
                    </div>
                    <div class="item-price">$15.00</div>
                    <div class="item-quantity">
                        <div class="quantity-selector">
                            <button class="quantity-btn minus">−</button>
                            <input type="number" value="2" min="1" class="quantity-input">
                            <button class="quantity-btn plus">+</button>
                        </div>
                    </div>
                    <div class="item-total">$30.00</div>
                    <div class="item-action">
                        <button class="remove-btn">✕</button>
                    </div>
                </div>

                <!-- Cart Item 3 -->
                <div class="cart-item">
                    <div class="item-product">
                        <div class="product-image">
                            <div class="image-placeholder">Image</div>
                        </div>
                        <div class="product-details">
                            <h3>Organic Cotton T-Shirt</h3>
                            <p class="product-id">Item ID: ITEM-108</p>
                            <p class="product-variant">Size: Medium | Color: Navy</p>
                        </div>
                    </div>
                    <div class="item-price">$24.99</div>
                    <div class="item-quantity">
                        <div class="quantity-selector">
                            <button class="quantity-btn minus">−</button>
                            <input type="number" value="1" min="1" class="quantity-input">
                            <button class="quantity-btn plus">+</button>
                        </div>
                    </div>
                    <div class="item-total">$24.99</div>
                    <div class="item-action">
                        <button class="remove-btn">✕</button>
                    </div>
                </div>
            </div>


            <div class="cart-summary-container">
                    <button class="checkout-btn">Proceed to Checkout</button>
                </div>
            </div>
        </div>

        <div class="empty-cart" style="display: none;">
            <div class="empty-cart-content">
                <div class="empty-cart-icon">🛒</div>
                <h2>Your Cart is Empty</h2>
                <p>Looks like you haven't added any items to your cart yet.</p>
                <a href="#" class="shop-now-btn">Shop Now</a>
            </div>
        </div>
</body>
</html>