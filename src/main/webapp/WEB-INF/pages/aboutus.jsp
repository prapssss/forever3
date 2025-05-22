<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - Forever3</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/aboutus.css">
</head>
<body>
 
  <!-- Include Header -->
  <jsp:include page="header.jsp" />
    <!-- Hero Section -->
    <section class="hero">
        <img src="${pageContext.request.contextPath}/resources/images/system/aboutsusthirdimage.png" alt="Skincare products flatlay" class="hero-bg">
        <div class="container hero-content">
            <h1>About Us</h1>
            <p>Discover the story behind our passion for beauty and skincare</p>
        </div>
    </section>
 
    <!-- About Section -->
    <section class="about-section">
        <div class="container">
            <div class="about-content">
                <div class="about-image">
                    <div class="about-image-circle">
                        <img src="${pageContext.request.contextPath}/resources/images/system/aboutussecondimage.png" alt="Woman with skincare product">
                    </div>
                </div>
                <div class="about-text">
                    <p class="subtitle">ABOUT US</p>
                    <h2>Our Journey to Forever3</h2>
                    <h3>A Passion for Natural SkinCare</h3>
                    <p>Welcome to Forever3! We are committed to developing high-quality, natural skin care products that accentuate your very own inner beauty. We began in 2015 and have been fixated on creating products that perform as they should, are good for the skin, and are good for the environment.</p>
                    <p>We began when our founder, Sarah, couldn't find skin care products that didn't irritate her skin. After years of research and working with dermatologists, we developed our line of signature products which combine the best of nature and science.</p>
                    <div class="feature-list">
                        <div class="feature-item">
                            <div class="feature-icon">✓</div>
                            <p>Over 8 years of skinCare experience</p>
                        </div>
                        <div class="feature-item">
                            <div class="feature-icon">✓</div>
                            <p>Dermatologist-tested products</p>
                        </div>
                        <div class="feature-item">
                            <div class="feature-icon">✓</div>
                            <p>Cruelty-free and environmentally friendly practices</p>
                        </div>
                        <div class="feature-item">
                            <div class="feature-icon">✓</div>
                            <p>Natural ingredients from all over the world</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
 
    <!-- Values Section -->
    <section class="values-section">
        <div class="container">
            <div class="section-header">
                <p class="subtitle">CORE VALUES</p>
                <h2>The Core Values That Define Everything</h2>
            </div>
            <div class="values-container">
                <div class="value-card">
                    <h3>Purity</h3>
                    <p>We believe in the power of pure, natural ingredients. Our products are free from harmful chemicals, parabens, and synthetic fragrances, ensuring your skin receives only the best nature has to offer.</p>
                </div>
                <div class="value-card">
                    <h3>Safety</h3>
                    <p>Your skin's health and safety are our top priorities. Every product undergoes rigorous testing and is formulated to be gentle even on the most sensitive skin types.</p>
                </div>
                <div class="value-card">
                    <h3>Innovation</h3>
                    <p>We continuously research and develop new formulations that combine traditional wisdom with modern science, creating effective solutions for diverse skincare needs.</p>
                </div>
            </div>
        </div>
    </section>
 
    <!-- Product Showcase -->
    <section class="product-showcase">
        <div class="container">
            <div class="showcase-container">
                <div class="showcase-image">
                    <img src="${pageContext.request.contextPath}/resources/images/system/aboutsusthirdimage.png" alt="Skincare product lineup">
                </div>
                <div class="showcase-content">
                    <h2>Our Signature Collection</h2>
                    <p>We craft our products with caution, combining nature's best with the most current skincare science. Each formula is crafted to tackle particular skin problems with our passion for purity and effectiveness.</p>
                    <p>We believe skincare can be a ritual—a little moment of self-love in your day that feeds your skin, and your sense of well-being. Our amber glass packaging not only preserves the effectiveness of our formulas but also reduces our environmental footprint.</p>
                    <p>Whether from our best-selling Radiance Serum to our calming Purifying Cleanser, each product in our line is designed to work together in harmony to reveal your skin's natural glow.</p>
                </div>
            </div>
        </div>
    </section>
 
    <!-- Testimonial Section -->
    <section class="testimonial-section">
        <div class="container">
            <div class="testimonial-container">
                <div class="testimonial-image">
                    <div class="testimonial-image-circle">
                        <img src="${pageContext.request.contextPath}/resources/images/system/Woman with skincare product.png" alt="Customer testimonial">
                    </div>
                </div>
                <div class="testimonial-content">
                    <h3>"Life-changing products"</h3>
                    <p>I've struggled with sensitive skin my entire life, trying countless products that either didn't work or caused irritation. Discovering Forever3 was a game-changer. Their natural formulations have transformed my skin, and for the first time, I feel confident without makeup. The Radiance Serum is now my holy grail product!</p>
                    <p class="testimonial-author">Emma Thompson</p>
                    <p class="testimonial-role">Loyal Customer Since 2019</p>
                </div>
            </div>
        </div>
    </section>
 
    <!-- Our Vision Section -->
    <section class="vision-section">
        <div class="container">
            <div class="section-header">
                <p class="subtitle">OUR VISION</p>
                <h2>Building a More Beautiful World</h2>
            </div>
            <div class="vision-content">
                <p>We dream of a world where everyone can love their natural beauty with confidence. Our vision is skincare, but it's more than that – we're committed to:</p>
                
                <div class="vision-point">
                    <p class="vision-point-title">Empowering Education:</p>
                    <p class="vision-point-text">We love teaching our people about skincare, ingredients, and sustainability. Learning to make wise choices for your skin and the world.</p>
                </div>
                
                <div class="vision-point">
                    <p class="vision-point-title">Sustainable Beauty:</p>
                    <p class="vision-point-text">We are committed to being better through the use of eco-packaging, earth-friendly manufacturing practices, and sustainable sourcing. Beauty does not have to harm our world.</p>
                </div>
                
                <div class="vision-point">
                    <p class="vision-point-title">Inclusive Beauty Standards:</p>
                    <p class="vision-point-text">We love diversity and firmly believe that beauty is available to all shapes, sizes, colors, tones, ages. We create products for every skin type, tone, and age, celebrating individual beauty in each and every one of you.</p>
                </div>
                
                <div class="vision-point">
                    <p class="vision-point-title">Community Support:</p>
                    <p class="vision-point-text">We are committed to giving back to communities in partnership with organizations that provide support to women's education and environmental conservation projects.</p>
                </div>
                
                <p>Come along on the journey to redefining beauty as a positive force – for your skin, your confidence, and our world together.</p>
            </div>
        </div>
    </section>
  <!-- Include Footer -->
  <jsp:include page="footer.jsp" />
  </body>
</html>