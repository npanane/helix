<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Details</title>
    <jsp:include page="includes.jsp"></jsp:include>
    <script src="admin/js/details.js"></script>
</head>
<body onload="">
    <div>${product.productId}</div>
    <div>${product.name}</div>
    <div>${product.price}</div>
    <div>${product.info}</div>
</body>
</html>
