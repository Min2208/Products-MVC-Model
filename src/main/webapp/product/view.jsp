<%--
  Created by IntelliJ IDEA.
  User: nhat
  Date: 4/20/18
  Time: 8:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View product</title>
</head>
<body>
<h1>Product details</h1>
<p>
    <a href="/customers">Back to product list</a>
</p>
<table>
    <tr>
        <td>Name: </td>
        <td>${requestScope["products"].getName()}</td>
    </tr>
    <tr>
        <td>Model: </td>
        <td>${requestScope["products"].getModel()}</td>
    </tr>
    <tr>
        <td>Price: </td>
        <td>${requestScope["products"].getPrice()}</td>
    </tr>
</table>
</body>
</html>