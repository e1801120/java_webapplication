<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Bootstrap 3 -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

<title>Product Management</title>
</head>
<body>


<!-- otetaan mukaan sivulle header-osa -->
<c:import url="header.jsp" charEncoding="UTF-8"></c:import>

<div class="container-fluid">

    <h1>View products</h1>

    <c:if test="${not empty error }">
        <p class="error">${error} </p>
    </c:if>

    <form class="form-inline" action="ProductManagement" method="get">
	
    <div class="form-group">
        <label for="choice">Choose:</label>
        <select name="choice" class="form-control" id="choice">
            <c:forEach var="b" items="${products }">
                <c:if test="${b.productid eq param.choice }">
                    <option value="${b.productid }" selected >${b.productname }</option> 
                </c:if>
                <c:if test="${b.productid ne param.choice }">
                    <option value="${b.productid }" >${b.productname }</option>
                </c:if>			
            </c:forEach>
        </select>
    </div>
    
    <button type="submit" class="btn btn-success">Show</button>
	
    </form>
    
    
	
    <p>
	
    <c:if test="${not empty aproduct }">
	
    <table class="table">
        <tr><td>ID: </td><td>${aproduct.productid }</td></tr>
        <tr><td>Name: </td><td>${aproduct.productname }</td></tr>
        <tr><td>Quality: </td><td>${aproduct.quality }</td></tr>
        <tr><td>Price: </td><td>${aproduct.price } € </td></tr>
        
    
    
    
    </table>
	
    </c:if>
	
    </p>
</div>

<c:if test="${not empty aproduct }">
<c:if test="${not empty user }">
<form action="EditDelete" method="post">
<input type="hidden" name ="action" value="EditDelete">
	
        <div class="form-group">
            
            <input type="hidden" name ="id" value="${aproduct.productid}">
        </div>
        
        <button type="submit" class="btn btn-primary">Edit</button>
		
    </form>
    
    <form action="EditDelete" method="get">
	<input type="hidden" name ="action" value="EditDelete">
	<button type="submit" class="btn btn-danger">Delete</button>
	</form>
    
</c:if>
</c:if>
</body>
</html>