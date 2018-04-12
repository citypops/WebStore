<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href='/resources/css/global.css'/>
    <link rel="stylesheet" type="text/css" href='/resources/css/home.css'/>
    <script src="/resources/js/global.js"></script>
    <script src="/resources/js/home.js"></script>
    <script src="/resources/js/cart.js"></script>
</head>
<body onload="init();<sec:authorize access="hasRole('CUSTOMER')">getCart(updateCartIndicator);</sec:authorize>">
<nav>
    <a href="/">
        <div class="link">HOME</div>
    </a>
    <sec:authorize access="hasRole('ANONYMOUS')">
        <script>logged_in = false</script>
        <a href="/login">
            <div class="link" style="width:120px;float:right">SIGN IN</div>
        </a>
        <a href="/register">
            <div class="link" style="float:right">CREATE ACCOUNT</div>
        </a>
    </sec:authorize>
    <sec:authorize access="authenticated">
        <script>logged_in = true</script>
        <a href="/logout">
            <div class="link" style="width:120px;float:right">LOGOUT</div>
        </a>
    </sec:authorize>
    <sec:authorize access="hasRole('ADMIN')">
        <a href="/admin">
            <div class="link">ADMIN PANEL</div>
        </a>
    </sec:authorize>
    <sec:authorize access="hasRole('CUSTOMER')">
        <a href="/mycart">
            <div class="link" style="width:120px;float:right" id="cartIndicator">CART</div>
        </a>
    </sec:authorize>
</nav>
<section id="container"></section>
</body>
</html>
