<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Products</title>
    <link rel="stylesheet" type="text/css" href='/resources/css/global.css'/>
    <link rel="stylesheet" type="text/css" href='/resources/css/cart.css'/>
    <script src="/resources/js/global.js"></script>
    <script src="/resources/js/products.js"></script>
</head>
<body onload="init()">
<nav>
    <a href="/">
        <div class="link">HOME</div>
    </a>
    <a href="/logout">
        <div class="link" style="width:120px;float:right">LOGOUT</div>
    </a>
</nav>
<aside class="rightSidebar">
    <div>
        <div style="width:100px; display: inline-block">
            <p>Products: </p>
            <p>Active: </p>
            <p>Inactive: </p>
        </div>
        <div style="display: inline-block">
            <p id="pProducts">0</p>
            <p id="pActive">0</p>
            <p id="pInactive">0</p>
        </div>
    </div>
    <a href="/admin/orders"><div class="sidebarElement">ORDERS</div></a>
    <a href="/admin/products"><div class="sidebarElement">PRODUCTS</div></a>
    <a href="/admin/accounts"><div class="sidebarElement">ACCOUNTS</div></a>
    <br>
    <a href="/admin/products/addproduct"><div class="sidebarElement">ADD NEW PRODUCT</div></a>
    <p>HINT: Click on product to open edit page.</p>
</aside>
<section id="description" class="cart" style="margin-top: 30px">
    <div class="nonClickableCartElement">
        <div class="cartSubElement"
             style="width: 30%">Product Name</div>
        <div class="cartSubElement"
             style="width: 25%">Product ID</div>
        <div class="cartSubElement"
             style="width: 10%; text-align: center;">Price</div>
        <div class="cartSubElement"
             style="width: 10%; text-align: center;">Tax</div>
        <div class="cartSubElement"
             style="width: 15%">Image Name</div>
        <div class="cartSubElement"
             style="width: 10%; text-align: center">Active</div>
    </div>
</section>
<section id="container" class="cart" style="margin-bottom: 30px">
</section>
</body>
</html>