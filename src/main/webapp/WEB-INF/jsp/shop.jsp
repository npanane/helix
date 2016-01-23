<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Shop</title>
    <jsp:include page="includes.jsp"></jsp:include>
    <script src="admin/js/shop.js"></script>
</head>
<body onload="init()">
<h3>${message}</h3>
<div id="p1"></div>
<div id="p2"></div>
    <div>
        <ul>
            <li><a href="shop/category/1">Shop by Category</a></li>
            <li><a href="shop/subcategory/1">Shop by Sub Category</a></li>
        </ul>
    </div>
</body>
</html>
