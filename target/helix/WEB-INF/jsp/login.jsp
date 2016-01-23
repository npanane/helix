<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="web/css/login.css">
    <title>Welcome to Helix</title>
</head>
<%--<body onload='document.f.j_username.focus();'>
<c:if test="${not empty error}">
    <div class="errorblock">
        Your login attempt was not successful, try again.<br/> Caused :
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
</c:if>--%>
<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
    <div class="header"><h1>Welcome to Helix System</h1></div>
    <div class="content">
        <div class="header-logo">
            <img alt="Helix" src="web/images/logo.png">
        </div>
        <c:if test="${not empty error}">
            <div class="errorblock">${error}</div>
        </c:if>


        <div><input type='text' name='j_username' value='' class="inputs" placeholder="User Name"></div>
        <div><input type='password' name='j_password' class="inputs" placeholder="Password" /></div>
        <div>
            <input name="submit" type="submit" value="Sign in" class="sign-in" />
        </div>
        <div><a class="need-help" href="#">Need help?</a></div>
    </div>
</form>
</body>
</html>