<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/04/2022
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Product manager</title>
</head>
<body>
<h1>Product List</h1>
<table border="1px solid">
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Color</th>
        <th>Description</th>
        <th>Category</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${products}" var="p">
        <tr>
            <td>${p.getId()}</td>
            <td>${p.getName()}</td>
            <td>${p.getPrice()}</td>
            <td>${p.getQuantity()}</td>
            <td>${p.getColor()}</td>
            <td>${p.getDescription()}</td>
            <td>${p.getCategory().getName()}</td>
            <td>
                <a href="/ProductServlet?action=edit&id=${d.id}">Edit </a>
                <a href="/ProductServlet?action=delete&id=${d.id}">Delete</a>
            </td>

        </tr>
    </c:forEach>

</table>

</body>
</html>
