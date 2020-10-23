<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Records</title>
</head>
<body>


<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Avatar</th>
        <th>Details</th>
    </tr>
    </thead>
    <c:forEach items="${records}" var="record">
        <tr>
            <td><c:out value="${record._id}"/></td>
            <td><c:out value="${record.first_name}"/></td>
            <td><c:out value="${record.last_name}"/></td>
            <td><img src="${pageContext.request.contextPath}/getImage?imageId=${record._id}"/></td>            
            <td><form method="post" action="${pageContext.request.contextPath}/recordDetails?recordId=${record._id}"
                      style="margin-bottom: 0;"><input type="submit" value="Details"></form></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>