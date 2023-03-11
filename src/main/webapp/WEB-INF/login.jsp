<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<h1 class="display-1 text-center" >Book Club</h1>
<div class="container">
    <form:form action="/login" method="POST" modelAttribute="userLogin">
    <div class="mb-3">
        <form:label for="email" class="form-label" path="email">Email:</form:label>
        <form:input style="width:250px;" type="email" class="form-control" id="email" aria-describedby="email" path="email"/>
        <form:errors path="email" class="text-danger"/>
    </div>
    <div class="mb-3">
        <form:label for="password" class="form-label" path="password">Password:</form:label>
        <form:input style="width:250px;" type="password" class="form-control" id="password" aria-describedby="password" path="password"/>
        <form:errors path="password" class="text-danger"/>
    </div>
    <button type="submit" class="btn btn-outline-primary">Login</button>
    </form:form>
        <div class="border-top pt-3">
            <small class="text-muted">
                Need An Account? <a class="ml-2" href="/register">Sign Up Now</a>
            </small>
        </div>
    </body>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>
