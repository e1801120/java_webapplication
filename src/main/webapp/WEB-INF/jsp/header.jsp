<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<header>
 <nav class="navbar navbar-expand-md navbar-dark"
  style="background-color: tomato">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">ProductManagement</a>
    </div>
    
    <ul class="nav navbar-nav">
      <li class="active"><a href="ProductManagement" class="nav-link">Products</a></li>
      <c:if test="${not empty user }">
      <li><a href="InsertProduct" class="nav-link">Insert</a></li>
      </c:if>
    </ul>
    <ul class="navbar-nav navbar-collapse justify-content-end">
	
  
   <c:if test="${empty user }">
         <li>	<a href="Register" class="nav-link"> Sign Up</a> </li>
      	 <li>	<a href="Login" class="nav-link"> Login</a> </li>
         </c:if>
         <c:if test="${not empty user }">
      	  <li> <a href="UserInfo" class="nav-link"> ${user.username}</a> </li>
      	 
      	  <li> <a href="Logout" class="nav-link"> Logout</a> </li>
      	  
         </c:if>
  </ul>
  </div>
 </nav>
</header>