<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" type="text/css" href='/resources/css/global.css'/>
    <link rel="stylesheet" type="text/css" href='/resources/css/cart.css'/>
    <link rel="stylesheet" type="text/css" href='/resources/css/modal.css'/>
    <script src="/resources/js/global.js"></script>
    <script src="/resources/js/cart.js"></script>
</head>
<body onload="initCart()">
<nav>
    <a href="/">
        <div class="link">HOME</div>
    </a>
    <sec:authorize access="authenticated">
        <a href="/logout">
            <div class="link" style="width:120px;float:right">LOGOUT</div>
        </a>
    </sec:authorize>
</nav>
<aside class="rightSidebar">
    <div>
        <div style="width:100px; display: inline-block">
            <p>Products: </p>
            <p>Netto Sum: </p>
            <p>Brutto Sum: </p>
        </div>
        <div style="display: inline-block">
            <p id="pProducts">0</p>
            <p id="pNettoSum">$0.00</p>
            <p id="pBruttoSum">$0.00</p>
        </div>
    </div>
    <div class="sidebarElement" onclick="cleanCart()">CLEAN CART</div>
    <div class="sidebarElement" onclick="processOrder()">PROCESS ORDER</div>
    <p>HINT: Click on any cart item to edit its quantity or delete it.</p>
</aside>
<div id="modal" class="modal">
    <div class="modalContent">
    </div>
</div>
<section id="description" style="margin-top: 30px" class="cart">
    <div class="nonClickableCartElement">
        <div class="cartSubElement"
             style="width: 30%">Product Name</div>
        <div class="cartSubElement"
             style="width: 20%">Product ID</div>
        <div class="cartSubElement"
             style="width: 10%; text-align: center;">Qty</div>
        <div class="cartSubElement"
             style="width: 10%; text-align: center;">Price</div>
        <div class="cartSubElement"
             style="width: 10%; text-align: center;">Tax</div>
        <div class="cartSubElement"
             style="width: 10%; text-align: right">Netto</div>
        <div class="cartSubElement"
             style="width: 10%; text-align: right">Brutto</div>
    </div>
</section>
<section id="cartContainer" class="cart" style="margin-bottom: 30px">
</section>
</body>
</html>