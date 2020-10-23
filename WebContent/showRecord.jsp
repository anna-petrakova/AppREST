<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Showing concrete record</title>
</head>
<body>

<table>
    <tr>
        <th>First name:</th>
        <td><c:out value="${record.first_name}"/></td>
    </tr>
    <tr>
        <th>Last name:</th>
        <td><c:out value="${record.last_name}"/></td>
    </tr>
    <tr>
        <th>Email:</th>
        <td><c:out value="${record.details.email}"/></td>
    </tr>
    <tr>
        <th>Company:</th>
    </tr>
    <tr>
        <th></th>
        <td>Name: <c:out value="${record.details.company.name}"/></td>
    </tr>
    <tr>
        <th></th>
        <td>Country: <c:out value="${record.details.company.country}"/></td>
    </tr>
    <tr>
        <th></th>
        <td>Location: <c:out value="${record.details.company.location_lat}"/> lat, <c:out value="${record.details.company.location_long}"/> long</td>
    </tr>
    <tr>
    	<th>Avatar:</th>
    	<td><img src="${pageContext.request.contextPath}/getImage?imageId=${record._id}"/></td> 
    </tr>    
</table>
<form action="${pageContext.request.contextPath}/records" method="get">
<input type="Submit" value="Back" />
</form>

</body>
</html>