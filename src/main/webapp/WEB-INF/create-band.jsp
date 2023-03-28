<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Create Band </title>
  <%--    Bootstrap--%>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<div class="container pt-4 mb-4">
  <div class="row">
    <div class="col-12 d-flex align-items-center justify-content-evenly">

      <p class="h1">Band Together</p>

      <nav class="d-flex justify-content-around pb-2">
        <!-- Add Band -->
        <a href="/band/create" class="btn btn-outline-primary">Create Band</a>
        <!-- Dashboard -->
        <a href="/" class="btn btn-outline-primary">Dashboard</a>
        <!-- Logout btn -->
        <a href="/user/logout" class="btn btn-outline-danger">Logout</a>
      </nav>

    </div>
  </div>
</div>
<div class="container">
  <div class="row">
    <div class="col-12">
      <h1> Hello, ${user.username} </h1>
      <h3 class="text-danger"> <c:out value="${error}"/></h3>

      <form:form action="/band/create" method="POST" modelAttribute="band">
        <div class="mb-3">
          <form:label for="bandName" class="form-label" path="bandName">Band Name:</form:label>
          <form:input style="width:250px;" type="text" class="form-control" id="bandName" aria-describedby="bandName" path="bandName"/>
          <form:errors path="bandName" class="text-danger"/>
        </div>
        <div class="mb-3">
          <form:label for="members" class="form-label" path="members">Members:</form:label>
          <form:errors path="members" class="text-danger"/>
          <form:select style="width:250px;" type="text" class="form-select" id="members" aria-describedby="members" path="members">
            <c:forEach var="user" items="${users}">
              <option value="${user.id}"><c:out value="${user.username}"/></option>
            </c:forEach>
          </form:select>
        </div>
        <button type="submit" class="btn btn-outline-primary">Create Band</button>
      </form:form>
    </div>
  </div>
</div>
</body>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>
