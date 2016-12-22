<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marcus.chiu
  Date: 10/26/16
  Time: 10:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>
</head>
<body>
    <h2>List of Phones</h2>

    <table>
        <tr>
            <td>Phone ID</td><td>number</td><td>Employee ID</td><td></td>
        </tr>

        <c:forEach items="${phones}" var="phone">
            <tr>
                <!-- this calls the Phone.getId() method -->
                <td><a href="<c:url value='/phone/edit-${phone.id}-phone' />">${phone.id}</a></td>
                <td>${phone.number}</td>
                <td><a href="<c:url value='/edit-${phone.employee.ssn}-employee' />">${phone.employee.id}</a></td>
                <td><a href="<c:url value='' />">delete</a></td>
            </tr>
        </c:forEach>
    </table>

    <br/>
    <br/>
    Go back to <a href="<c:url value='/list' />">List of All Employees</a>
</body>
</html>
