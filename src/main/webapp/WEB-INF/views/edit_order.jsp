<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="citypops.webstore.domain.Order" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Order</title>
    <link rel="stylesheet" type="text/css" href='/resources/css/global.css'/>
    <link rel="stylesheet" type="text/css" href='/resources/css/form.css'/>
    <link rel="stylesheet" type="text/css" href='/resources/css/cart.css'/>
    <link rel="stylesheet" type="text/css" href='/resources/css/order.css'/>
    <link rel="stylesheet" type="text/css" href='/resources/css/modal.css'/>
    <script src="/resources/js/global.js"></script>
    <script src="/resources/js/edit_order.js"></script>
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
            <p>Netto Sum: </p>
            <p>Brutto Sum: </p>
        </div>
        <div style="display: inline-block">
            <p id="pProducts">0</p>
            <p id="pNettoSum">$0.00</p>
            <p id="pBruttoSum">$0.00</p>
        </div>
    </div>
    <a href="/admin/orders"><div class="sidebarElement">ORDERS</div></a>
    <a href="/admin/products"><div class="sidebarElement">PRODUCTS</div></a>
    <a href="/admin/accounts"><div class="sidebarElement">ACCOUNTS</div></a>
    <br>
    <div class="sidebarElement" onclick="deleteOrder()">DELETE THIS ORDER</div>
    <c:choose>
        <c:when test="${!order.state}">
            <div class="sidebarElement" onclick="realiseOrder()">REALISE THIS ORDER</div>
            <p>HINT: Click on order item to edit its quantity or delete it.</p>
        </c:when>
        <c:otherwise>
            <p>HINT: You cannot edit realised orders.</p>
        </c:otherwise>
    </c:choose>
</aside>
<div id="modal" class="modal">
    <div class="modalContent">
    </div>
</div>
<section style="width: 80%; margin-left: 2%">
    <div id="infodiv" class="test">
        <div class="ediv">
            <div class="div1">Order ID</div>
            <b>${order.orderId}</b>
        </div>
        <div class="ediv">
            <div class="div1">Username</div>
            <b>${order.username}</b>
        </div>
        <div class="ediv">
            <div class="div1">Created</div>
            <b>${order.created.toLocalDateTime()}</b>
        </div>
        <div class="ediv">
            <div class="div1">State</div>
            <b><c:choose>
                <c:when test="${order.state}">Realised</c:when>
                <c:otherwise>Waiting</c:otherwise>
            </c:choose></b>
        </div>
        <br>
        <div class="ediv">
            <div class="div1">Name</div>
            <b>${user.firstName} ${user.lastName}</b>
        </div>
        <div class="ediv">
            <div class="div1">Street</div>
            <b>${user.street} ${user.homeNo}</b>
        </div>
        <div class="ediv">
            <div class="div1">Postcode</div>
            <b>${user.postcode}</b>
        </div>
        <div class="ediv">
            <div class="div1">City</div>
            <b>${user.city}</b>
        </div>
        <div class="ediv">
            <div class="div1">Phone No.</div>
            <b>${user.phoneNo}</b>
        </div>
    </div>
    <div id="itemsdiv" class="test">
        <div id="description">
            <div class="nonClickableCartElement">
                <div class="cartSubElement"
                     style="width: 40%">Product ID</div>
                <div class="cartSubElement"
                     style="width: 12%;text-align: center;">Qty</div>
                <div class="cartSubElement"
                     style="width: 12%; text-align: center;">Price</div>
                <div class="cartSubElement"
                     style="width: 12%; text-align: center;">Tax</div>
                <div class="cartSubElement"
                     style="width: 12%; text-align: right">Netto</div>
                <div class="cartSubElement"
                     style="width: 12%; text-align: right">Brutto</div>
            </div>
        </div>
        <div id="container" class="desc">
        </div>
    </div>
</section>
<script>
    <%
        Order order = (Order) request.getAttribute("order");
        JSONArray json = (JSONArray) JSONObject.wrap(order.getOrderItems());
        out.write(String.format("orderItems = %s;\n", json.toString()));
        out.write(String.format("orderId = \"%s\"\n;", order.getOrderId()));
        out.write(String.format("state = %s;", order.getState()));
    %>
</script>
</body>
</html>