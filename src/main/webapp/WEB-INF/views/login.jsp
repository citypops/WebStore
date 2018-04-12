<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href='/resources/css/global.css'/>
    <link rel="stylesheet" type="text/css" href='/resources/css/form.css'/>
</head>
<body>
<nav>
    <a href="/">
        <div class="link">HOME</div>
    </a>
    <sec:authorize access="hasRole('ANONYMOUS')">
        <a href="/register">
            <div class="link" style="float:right">CREATE ACCOUNT</div>
        </a>
    </sec:authorize>
</nav>
<section>
    <c:if test="${param.error != null}">
        <p>Invalid username and password.</p>
    </c:if>

    <c:if test="${param.logout != null}">
        <p>You have been logged out.</p>
    </c:if>
    <form th:action="@{/login}" method="post">
        <div class="ediv">
            <div class="div1">username</div>
            <input type="text" name="username"/>
        </div>
        <div class="ediv">
            <div class="div1">password</div>
            <input type="password" name="password"/>
        </div>
        <div class="ediv">
            <button type="submit">Sign In</button>
        </div>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
</section>
</body>
</html>