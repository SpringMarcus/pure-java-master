<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marcus.chiu
  Date: 10/21/16
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>List of Posts</h2>
    <table>
        <tr>
            <td>ID</td><td>Text</td><td></td>
        </tr>
        <c:forEach items="${posts}" var="post">
            <tr>
                <!-- this calls the Post.getId() method -->
                <td><a href="<c:url value='/post/edit-${post.id}-post' />">${post.id}</a></td>
                <td>${post.text}</td>

                <td><a href="<c:url value='/post/delete-${post.id}-post' />">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
