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
    <title>Dashboard</title>
  <%--    Bootstrap--%>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
    <h3 class="text-danger"> <c:out value="${error}"/></h3>
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
              <h2> Bands you are a part of!</h2>
              <%--              List of all bands--%>
              <ul>
                <c:forEach var="band" items="${user.bands}">
                  <li>
                    <a href="/band/${band.id}"><c:out value="${band.bandName}"/></a>

                  </li>
                  <ul>
                    <li> Members: </li>
                    <c:forEach var="member" items="${band.members}">
                      <li>
                        <c:out value="${member.username}"/>
                      </li>
                    </c:forEach>
                  </ul>
                </c:forEach>
              </ul>
              <h2> Bands you are not a part of (Yet)</h2>
<%--              List of all bands--%>
              <ul>
                <c:forEach var="band" items="${otherBands}">
                  <li>
                    <a href="/band/${band.id}"><c:out value="${band.bandName}"/></a>
                  </li>
                  <ul>
                    <li> Members: </li>
                    <c:forEach var="member" items="${band.members}">
                      <li>
                        <c:out value="${member.username}"/>
                      </li>
                    </c:forEach>
                  </ul>
                </c:forEach>
              </ul>
            </div>
          </div>
        </div>
</body>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>
