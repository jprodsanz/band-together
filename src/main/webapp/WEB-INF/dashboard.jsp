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
    <title>Dashboard</title>
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
    <h1 class="text-center"> Dashboard</h1>
        <p>
            <a href="/logout" class="btn btn-outline-primary">Logout</a>
        </p>
    <table class="table table-striped table-bordered text-center">
        <thead class="table-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Username</th>
            <th scope="col">Email </th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${allUsers}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.username}"/></td>
                <td><c:out value="${user.email}"/></td>

                <td>
                    <form action="/user/${user.id}" method="POST">
                        <input type="hidden" name="_method" value="delete"/>
                        <button class="btn btn-danger">Delete</button>
                    </form>
                        <%--                <a href="/trip/${trip.id}" class="btn btn-primary">Update</a>--%>
                </td>
            </tr>

            <%--            <td>--%>
            <%--                <a href="/burger/${burger.id}/delete" class="btn btn-danger">Delete</a>--%>
            <%--            </td>--%>


        </c:forEach>

        </tbody>
    </table>
</div>
</body>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>
