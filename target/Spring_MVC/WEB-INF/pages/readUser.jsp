<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<h2>Users List:</h2>
<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Age</td>
        <td>Role</td>
    </tr>

    <c:forEach items="${userList}" var="user">

    <tr>
        <td>${user.getId()}</td>
        <td>${user.getName()}</td>
        <td>${user.getAge()}</td>
        <td>${user.getRole()}</td>

        <td>
            <a href="/deleteUser?id=${user.getId()}" methods="get">
                <input type="submit" name="id" value="Delete" style="float:left">
            </a>
            <a href="/updateUser?id=${user.getId()}" methods="get">
                <input type="submit" name="id" value="Update" style="float:left">
            </a>
        </td>
    </tr>

    </c:forEach>
</table>

<br/><br/>
<div>
    Input New User:
</div>

<form action="/addUser" method="post">
    <table style="width: 25%">
        <tr>
            <td>Name</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password"/></td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input type="number" name="age"/></td>
        </tr>
        <tr>
            <td>Role</td>
            <td><input type="text" name="role"/></td>
        </tr>
    </table>
    <input type="submit" value="Add"/>
</form>
</body>
</html>
