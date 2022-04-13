<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 13/04/2022
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Delete Product</h1>
<form method="post">
    <table>
        <caption><h2>Are You Sure ?</h2></caption>
        <c:if test="${product != null}">
            <input type="hidden" name="id" value="<c:out value='${product.getId()}' />"/>
        </c:if>
        <tr>
            <th>Name:</th>
            <td>${product.getName()}</td>
        </tr>
        <tr>
            <th>Price:</th>
            <td>${product.getPrice()}</td>
        </tr>
        <tr>
            <th>Quantity:</th>
            <td>
            <td>${product.getQuantity()}</td>
            </td>
        </tr>
        <tr>
            <th>Color:</th>
            <td>${product.getColor()}</td>
        </tr>
        <tr>
            <th>Description:</th>
            <td>${product.getDescription()}</td>
        </tr>
        <tr>
            <th>Category: </th>
            <td>${product.getCategory().getName()}</td>
        </tr>
        <tr>
            <td><button type="submit">Delete</button>
                <button type="button " onclick="location.href='/ProductServlet?action=product'">Back</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>