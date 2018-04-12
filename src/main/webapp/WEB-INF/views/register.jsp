<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Account</title>
    <link rel="stylesheet" type="text/css" href='/resources/css/global.css'/>
    <link rel="stylesheet" type="text/css" href='/resources/css/form.css'/>
    <script src="/resources/js/global.js"></script>
    <script src="/resources/js/register.js"></script>
</head>
<body onload="init()">
<nav>
    <a href="/">
        <div class="link">HOME</div>
    </a>
    <sec:authorize access="hasRole('ANONYMOUS')">
        <script>logged_in = false</script>
        <a href="/login">
            <div class="link" style="width:120px;float:right">SIGN IN</div>
        </a>
    </sec:authorize>
</nav>
<section>
    <div class="ediv">
        <div class="div1">Username</div>
        <input type="text" id="username"/>
    </div>
    <div class="ediv">
        <div class="div1">Password</div>
        <input type="password" id="password"/>
    </div>
    <div class="ediv">
        <div class="div1">Email</div>
        <input type="text" id="email"/>
    </div>
    <div class="ediv">
        <div class="div1">First Name</div>
        <input type="text" id="first_name"/>
    </div>
    <div class="ediv">
        <div class="div1">Last Name</div>
        <input type="text" id="last_name"/>
    </div>
    <div class="ediv">
        <div class="div1">City</div>
        <input type="text" id="city"/>
    </div>
    <div class="ediv">
        <div class="div1">Postcode</div>
        <input type="text" id="postcode"/>
    </div>
    <div class="ediv">
        <div class="div1">Street</div>
        <input type="text" id="street"/>
    </div>
    <div class="ediv">
        <div class="div1">Home No.</div>
        <input type="text" id="home_no"/>
    </div>
    <div class="ediv">
        <div class="div1">Phone No.</div>
        <input type="text" id="phone_no"/>
    </div>
    <div class="ediv">
        <button onclick="registerUser()">Create Account</button>
    </div>
</section>
</body>
</html>
