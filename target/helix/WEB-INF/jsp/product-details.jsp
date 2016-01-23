<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Product Details</title>
    <link href="../../web/css/rest.css" rel="stylesheet" type="text/css">
    <link href="../../web/css/style.css" rel="stylesheet" type="text/css">
    <style>
        .offer-container {
            width: 960px;
            margin: 17px auto;
            border: 1px solid #e5e5e5;
            background: #ffffff;
        }

        .offer-deatils-header{
            height: 345px;
            width: 100%;
            border-bottom: 1px solid #e5e5e5;
            position: relative;
        }

        .offer-deatils-header img{
            float: right;
            margin: 20px;
        }

        .offer-details{
            position: absolute;
            font-family: 'open-sans';
            left: 20px;
            padding: 0px;
            margin: 0px;
        }

        .location{
            font-size: 14px;
            top: 23px;
            color: #666666;
        }

        .name{
            font-size: 20px;
            color: #333333;
            font-weight: bold;
            top: 51px;
        }

        .desc{
            top: 87px;
            font-size: 14px;
            color: #666666;
        }

        .offer-logo{
            position: absolute;
            bottom: 15px;
            left: 15px;
            margin: 0px !important;
        }

        .offer-details-socail-container{
            width: 280px;
            height: 60px;
            position: absolute;
            bottom: 15px;
            left: 140px;
        }

        .offer-details-socail-container p{
            font-family: 'open-sans';
            font-size: 12px;
            float: left;
            margin-right: 12px;
        }

        .redeem-column{
            width: 400px;
            float: right;
            margin-right: 20px;
        }

        .redeem-column h2{
            font-family: 'open-sans';
            font-size: 20px;
            margin-top: 24px;
            margin-bottom: 18px;
        }

        .redeem-container{
            width: 100%;
            background-color: #f8f8f8;
            padding: 1px 0px 1px 0px;
            margin-bottom: 34px;
        }

        .redeem-container p{
            font-family: 'open-sans';
            font-size: 12px;
            margin-left: 23px;
            padding-top: 10px;
        }

        .map{
            width: 360px;
            height: 240px;
            background-image: url(../images/sample-map.jpg);
            margin: 20px;
        }

        .redeem-location{
            font-family: 'open-sans';
            font-size: 20px;
            margin-top: 24px;
            margin-bottom: 18px;
            margin-left: 23px;
            font-weight: bold;
        }

        .direction-button{
            margin-left: 23px;
            background-color: #f1f1f1;
            height: 27px;
            width: 72px;
            border: 1px solid #dddddd;
            border-radius: 2px;
            font-family: 'open-sans';
            font-size: 12px;
            text-align: center;
            line-height: 24px;
            margin-bottom: 16px;
        }

        .link{
            color: #1480FF;
        }

        .review{
            font-family: 'open-sans';
            font-size: 20px;
            margin-top: 24px;
            margin-bottom: 18px;
            margin-left: 23px;
            font-weight: bold;
        }

        .rating-amount{
            font-family: 'open-sans';
            font-size: 20px;
            margin-top: 1px;
            margin-bottom: 18px;
            margin-left: 24px;
            font-weight: bold;
            color: #e5711c;
            float: left;
            margin-right: 10px;
        }

        .rating-container a{
            font-family: 'open-sans';
            font-size: 12px;
            line-height: 26px;
        }

        .rating-container img{
            float: left;
            margin-right: 5px;
        }

        .terms-column  h2{
            margin-left: 10px;
            font-family: 'open-sans';
            font-size: 24px;
            margin-top: 24px;
        }

        .terms-column  p{
            font-size: 13px;
            font-family: 'open-sans';
            line-height: 18px;
            margin-left: 13px;
        }

        .terms-column{
            float: left;
            width: 480px;
        }
    </style>
</head>
<body><div class="header-bar">
    <div class="header-content">

        <div class="header-logo">
            <img alt="Google" src="../../web/images/logo.png">
            <h1> b a b y</h1>
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
            <li><a href="#">Send Money</a></li>
            <li><a href="#">Shop in Stores</a></li>
            <li><a href="#">Buy Online</a></li>
            <li><a href="#">Stay Safe</a></li>
        </ul>
    </div>
</div>
<div class="content-bar">
    <div class="content">
        <div class="offer-container">
            <div class="offer-deatils-header">
                <p class="offer-details location">${product.category.name} > ${product.subCategory.name}</p>
                <h2 class="offer-details name" style="margin-top: 0px;">${product.name} for $${product.price}</h2>
                <p class="offer-details desc">in stock</p>
                <a class="get-hot-offer">GET A QUOTE</a>
                <img height="300" width="400" src="/grip-product-images/${product.productImages.get(0).name}.jpg">
                <img class="offer-logo" height="80" width="80" src="../../web/images/offer-location-logo.jpg">
                <div class="offer-details-socail-container">
                    <p>Use by : <strong>Nov 25, 2013</strong></p><p>How to use : <strong>In-store</strong></p>
                    <div class="offer-social-button-container"></div>
                </div>
            </div>
            <div class="terms-column">
                <h2>Product Description</h2>
                <p>Not valid with any other offers or promotions. Valid only at participating locations. Mention coupon code when ordering over the phone. Limit one per person.</p>
                <h2>Features</h2>
                <p>No cash value unless otherwise indicated in these terms. Google does not make any warranty in relation to the offers, including without limitation their validity and/or value. Google is not a party to any transaction that the advertiser and user may enter into.</p>
                <h2>How to Get It</h2>
                <img width="100" height="85" src="../../web/images/infographic-step3.png">
                <p>Get the Google Offers app on your Android or iPhone to redeem in-store. You can also print your voucher, which will still save the offer to your Google account.</p>
            </div>
            <div class="redeem-column">
                <h2>Where to Redeem</h2>
                <div class="redeem-container">

                    <div class="map"></div>

                    <h3 class="redeem-location">Dominos Pizza</h3>

                    <div class="direction-button">Directions</div>

                    <p><strong>Address: </strong>1621 W Chapman Ave, Orange, CA 92868</p>
                    <p><strong>Phone: </strong>(714) 978-9711</p>
                    <p><strong>Website: </strong><a class="link">www.dominos.com</a></p>
                    <p><strong>Prices: </strong>$$$$</p>
                    <p><strong>Hours: </strong>Sunday hours 10:00 am - 12:00 am - <a class="link">See All</a></p>
                    <p><strong>Menu: </strong><a class="link">www.urbanspoon.com</a></p>
                    <h3 class="review">Review</h3>

                    <div class="rating-container">
                        <h3 class="rating-amount">2.8</h3>
                        <img src="../../web/images/active-rate.jpg">
                        <img src="../../web/images/active-rate.jpg">
                        <img src="../../web/images/active-rate.jpg">
                        <img src="../../web/images/inactive-rate.jpg">
                        <img src="../../web/images/inactive-rate.jpg">
                        <a class="link">6 Reviews</a>
                        <div style="clear: both;"></div>
                    </div>
                </div>
            </div>
            <div style="clear: both;"></div>
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
        <p>Grip - About Grip - <red>New</red> Privacy & Terms</p>
    </div>
</div>
</body>
</html>
