<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marcus.chiu
  Date: 10/26/16
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    message : ${success}
    <br/>
    <br/>
    Go back to <a href="<c:url value='/phone/list' />">List of All Phone</a>
</body>
</html>
