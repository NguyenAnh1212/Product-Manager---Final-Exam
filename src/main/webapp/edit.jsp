<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 13/04/2022
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<center>
    <h1>Product Manager</h1>

</center>
<div >
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Product
                </h2>
            </caption>
            <c:if test="${product != null}">
                <input type="hidden" name="id" value="<c:out value='${product.getId()}' />"/>
            </c:if>
            <tr>
                <th>Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${product.getName()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Price:</th>
                <td>
                    <input type="text" name="price" size="45"
                           value="<c:out value='${product.getPrice()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Quantity:</th>
                <td>
                    <input type="text" name="quantity" size="45"
                           value="<c:out value='${product.getQuantity()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Color:</th>
                <td>
                    <input type="text" name="color" size="15"
                           value="<c:out value='${product.getColor()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Description:</th>
                <td>
                    <textarea name="description" cols="45" rows="10">${product.getDescription()}</textarea>
                </td>
            </tr>
            <tr>
                <th>Category:</th>
                <td><input type="text" name="category" value="${product.getCategory().getName()}" readonly/>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="submit">Update</button>
                    <button type="button" onclick="location.href='/ProductServlet?action=Product'">Back</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
