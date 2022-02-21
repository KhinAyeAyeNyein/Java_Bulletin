<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav
  class="main-header navbar navbar-expand navbar-white navbar-light nav-design">
  <ul class="navbar-nav">
    <li class="nav-item"><a class="nav-link" data-widget="pushmenu"
      href="#"><i class="fas fa-bars"></i></a></li>
  </ul>
  <ul class="navbar-nav ml-auto">
    <c:if test="${LOGIN_USER!= null }">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#" class="nav-link">${LOGIN_USER.email}</a></li>
      </ul>
    </c:if>
    <li><a href="${pageContext.request.contextPath}/logout"
      class="btn btn-info btn-block">Logout</a></li>
  </ul>
</nav>
