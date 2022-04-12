<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/04/2022
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="" method="post">
<table>
    <tr>
        <th>Enter Name</th>
        <td><input type="text" name="name" placeholder="enter  name"/></td>
    </tr>
    <tr>
        <th>Enter Price</th>
        <td><input type="text" name="price" placeholder="enter price"/></td>
    </tr>
    <tr>
        <th>Enter Quantity</th>
        <td><input name="quantity" placeholder="enter quantity"></td>
    </tr>
    <tr>
        <th>Enter Color</th>
        <td><input type="text" name="color" placeholder="enter color"></td>
    </tr>
    <tr>
        <th>Enter Description</th>
        <td><input type="text" name="description" placeholder="enter description"></td>
    </tr>

    <tr>
        <th>Choose Category</th>
        <td>
            <select name="category" id="category">
                <c:forEach items="${categories}" var="cat">
                    <option value="${cat.id}">${cat.getName()}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td><button type="button" onclick="location.href='/ProductServlet?action=cat'">Back list product</button></td>
        <td><button type="submit" onclick="location.href='/ProductServlet?action='">Add</button></td>
    </tr>
</table>
</form>
</body>
</html>

