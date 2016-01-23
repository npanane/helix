<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Shop By</title>

    <link href="../../web/css/rest.css" rel="stylesheet" type="text/css">
    <link href="../../web/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="header-bar">
    <div class="header-content">

        <div class="header-logo">
            <img alt="Google" src="../../web/images/logo.png">
            <h1>baby</h1>
        </div>

        <div class="header-login">
            <a href="#">Sign In</a>
            <a class="install-now" href="#">Install Now</a>
        </div>
    </div>
</div>
<div class="nav-bar">
    <div class="nav-content">
        <ul>
            <li class="active"><a href="#">Overview</a></li>
            <li><a href="shop/category/1">Shop</a></li>
            <li><a href="#">About Us</a></li>
            <li><a href="#">Contact Us</a></li>
            <li><a href="#">Help</a></li>
        </ul>
    </div>
</div>
<div class="content-bar">
    <div class="content">
        <div class="hot-offer">
            <p class="hot-offer-owner">JC Penny</p>
            <h3 class="hot-offer-desc">15% off items in our new home department</h3>
            <p class="hot-offer-loc">Laguna Hills</p>
            <img alt="hot-offer" src="../../web/images/hot-offer.jpg">
            <a class="get-hot-offer">GET OFFER</a>
        </div>
        <div class="offer-header">
            <h3>Offers</h3>
            <a href="#">Why these offers?</a>
        </div>
        <c:if test="${not empty products}">
            <c:forEach var="product" items="${products}">
                <div class="offer">
                    <img style="width: 308px;" src="/grip-product-images/${product.productImages.get(0).name}.jpg">
                    <p class="offer-loc">${product.name}</p>
                    <!--<p class="offer-type">Orange</p>-->
                    <h3 class="offer-desc">Price $${product.price}</h3>
                    <div class="get-offer-container">
                        <a href="#">get a quote</a>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <div class="offer">
            <img src="../../web/images/sample-offer.jpg">
            <p class="offer-loc">Dominos Pizza</p>
            <p class="offer-type">Orange</p>
            <h3 class="offer-desc">Large 2-Topping Pizza for $8.88</h3>
            <div class="get-offer-container">
                <a href="#">get offer</a>
            </div>
        </div>
        <div class="offer">
            <img src="../../web/images/sample-offer.jpg">
            <p class="offer-loc">Dominos Pizza</p>
            <p class="offer-type">Orange</p>
            <h3 class="offer-desc">Large 2-Topping Pizza for $8.88</h3>
            <div class="get-offer-container">
                <a href="#">get offer</a>
            </div>
        </div>
        <div class="offer">
            <img src="../../web/images/sample-offer.jpg">
            <p class="offer-loc">Dominos Pizza</p>
            <p class="offer-type">Orange</p>
            <h3 class="offer-desc">Large 2-Topping Pizza for $8.88</h3>
            <div class="get-offer-container">
                <a href="#">get offer</a>
            </div>
        </div>
        <div class="offer">
            <img src="../../web/images/sample-offer.jpg">
            <p class="offer-loc">Dominos Pizza</p>
            <p class="offer-type">Orange</p>
            <h3 class="offer-desc">Large 2-Topping Pizza for $8.88</h3>
            <div class="get-offer-container">
                <a href="#">get offer</a>
            </div>
        </div>
        <div class="offer">
            <img src="../../web/images/sample-offer.jpg">
            <p class="offer-loc">Dominos Pizza</p>
            <p class="offer-type">Orange</p>
            <h3 class="offer-desc">Large 2-Topping Pizza for $8.88</h3>
            <div class="get-offer-container">
                <a href="#">get offer</a>
            </div>
        </div>
        <div class="offer">
            <img src="../../web/images/sample-offer.jpg">
            <p class="offer-loc">Dominos Pizza</p>
            <p class="offer-type">Orange</p>
            <h3 class="offer-desc">Large 2-Topping Pizza for $8.88</h3>
            <div class="get-offer-container">
                <a href="#">get offer</a>
            </div>
        </div>
        <div class="offer">
            <img src="../../web/images/sample-offer.jpg">
            <p class="offer-loc">Dominos Pizza</p>
            <p class="offer-type">Orange</p>
            <h3 class="offer-desc">Large 2-Topping Pizza for $8.88</h3>
            <div class="get-offer-container">
                <a href="#">get offer</a>
            </div>
        </div>
        <div class="offer">
            <img src="../../web/images/sample-offer.jpg">
            <p class="offer-loc">Dominos Pizza</p>
            <p class="offer-type">Orange</p>
            <h3 class="offer-desc">Large 2-Topping Pizza for $8.88</h3>
            <div class="get-offer-container">
                <a href="#">get offer</a>
            </div>
        </div>
        <div class="offer">
            <img src="../../web/images/sample-offer.jpg">
            <p class="offer-loc">Dominos Pizza</p>
            <p class="offer-type">Orange</p>
            <h3 class="offer-desc">Large 2-Topping Pizza for $8.88</h3>
            <div class="get-offer-container">
                <a href="#">get offer</a>
            </div>
        </div>
        <div class="more-offers">
            <a href="#">More Offers...</a>
        </div>
        <div class="offer-apps-container">
            <div class="offer-app-left">
                <img src="../../web/images/devices.jpg">
                <h3>Get offers every day on your Android or <br> iPhone with our native apps.</h3>
                <li href="#">Download Android app</li>
                <li href="#">Download iPhone app</li>
            </div>
            <div class="offer-app-right">
                <h3>Join us on G+</h3>
                <p>Follow our Google+ page to stay uptodated on the best offers and events</p>
            </div>
            <div class="offer-app-footer">
                <a class="offer-app-links">How it works</a>
                <a class="offer-app-links">For Businesses</a>
                <a class="offer-app-links">Help</a>
                <a class="offer-app-links">Privacy & Terms</a>
            </div>
        </div>
        <div style="clear: both"></div>
    </div>
</div>
<div class="footer-bar">
    <div class="footer-container">
        <div class="footer-container-bar">
            <div class="footer-link-container">
                <a class="offer-app-links">For Developers</a>
                <a class="offer-app-links" style="margin-right:77px;">FAQs</a>
                <a class="offer-app-links">Google Wallet Privacy Policy</a>
                <a class="offer-app-links">For Businesses</a>
                <a class="offer-app-links">Help Center</a>
                <a class="offer-app-links">Terms of Services</a>
            </div>
        </div>
    </div>
    <div class="bottom-bar">
        <p>Google - About Google - <red>New</red> Privacy & Terms</p>
    </div>
</div>
</body>
</html>
