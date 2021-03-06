<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <link rel="stylesheet" type="text/css" href='/resources/css/global.css'/>
    <link rel="stylesheet" type="text/css" href='/resources/css/form.css'/>
    <script src="/resources/js/global.js"></script>
    <script src="/resources/js/edit_product.js"></script>
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
    <a href="/admin/orders"><div class="sidebarElement">ORDERS</div></a>
    <a href="/admin/products"><div class="sidebarElement">PRODUCTS</div></a>
    <a href="/admin/accounts"><div class="sidebarElement">ACCOUNTS</div></a>
</aside>
<section>
    <div class="ediv">
        <div class="div1">Product ID</div>
        <input type="text" id="productId"/>
    </div>
    <div class="ediv">
        <div class="div1">Name</div>
        <input type="text" id="prodName"/>
    </div>
    <div class="ediv">
        <div class="div1">Price</div>
        <input type="number" step=".01" min=0 id="price"/>
    </div>
    <div class="ediv">
        <div class="div1">Tax</div>
        <input type="number" step=".01" min=0 max=1 id="taxAmount"/>
    </div>
    <div class="ediv">
        <div class="div1">Image Name</div>
        <input type="text" id="imageName"/>
    </div>
    <div class="ediv">
        <div class="div1">Active</div>
        <input type="checkbox" id="active" checked/>
    </div>
    <div class="ediv">
        <button onclick="addProduct()">Add Product</button>
    </div>
</section>
</body>
</html>