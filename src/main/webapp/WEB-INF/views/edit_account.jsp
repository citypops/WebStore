<%@ page import="citypops.webstore.domain.User" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link rel="stylesheet" type="text/css" href='/resources/css/global.css'/>
    <link rel="stylesheet" type="text/css" href='/resources/css/form.css'/>
    <script src="/resources/js/global.js"></script>
    <script src="/resources/js/edit_account.js"></script>
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
    <br>
    <div class="sidebarElement" onclick="deleteAccount()">DELETE THIS ACCOUNT</div>
    <p>HINT: Use ',' sign as separator for distinct user roles.</p>
</aside>
<section>
    <div class="ediv">
        <div class="div1">Username</div>
        <b>${user.username}</b>
    </div>
    <div class="ediv">
        <div class="div1">Password</div>
        <input type="password" id="password" value="${user.password}"/>
    </div>
    <div class="ediv">
        <div class="div1">Email</div>
        <input type="text" id="email" value="${user.email}"/>
    </div>
    <div class="ediv">
        <div class="div1">First Name</div>
        <input type="text" id="first_name" value="${user.firstName}"/>
    </div>
    <div class="ediv">
        <div class="div1">Last Name</div>
        <input type="text" id="last_name" value="${user.lastName}"/>
    </div>
    <div class="ediv">
        <div class="div1">City</div>
        <input type="text" id="city" value="${user.city}"/>
    </div>
    <div class="ediv">
        <div class="div1">Postcode</div>
        <input type="text" id="postcode" value="${user.postcode}"/>
    </div>
    <div class="ediv">
        <div class="div1">Street</div>
        <input type="text" id="street" value="${user.street}"/>
    </div>
    <div class="ediv">
        <div class="div1">Home No.</div>
        <input type="text" id="home_no" value="${user.homeNo}"/>
    </div>
    <div class="ediv">
        <div class="div1">Phone No.</div>
        <input type="text" id="phone_no" value="${user.phoneNo}"/>
    </div>
    <div class="ediv">
        <div class="div1">ROLES</div>
        <input type="text" id="roles"/>
    </div>
    <div class="ediv">
        <div class="div1">Enabled</div>
        <input type="checkbox" id="enabled" <c:if test="${user.enabled}">checked</c:if>/>
    </div>
    <div class="ediv">
        <button onclick="updateAccount()">Update Account</button>
    </div>
</section>
<script>
    <%
        User user = (User) request.getAttribute("user");
        JSONArray json = (JSONArray) JSONObject.wrap(user.getRoles());
        out.write(String.format("oldRoles = %s;", json.toString()));
    %>
    username = "${user.username}";
</script>
</body>
</html>