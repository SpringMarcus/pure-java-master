<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marcus.chiu
  Date: 10/26/16
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Registration Form</h2>

    <form:form method="POST" modelAttribute="phone">
        <form:input type="hidden" path="id" id="id"/>
        <h1>Phone</h1>
        <table>
            <tr>
                <td><label for="number">Number: </label> </td>
                <!-- below calls Phone.getNumber() -->
                <td><form:input path="number" id="number"/></td>
                <!-- below calls Phone.getNumber() -->
                <td><form:errors path="number" cssClass="error"/></td>
            </tr>

            <tr>
                <td colspan="3">
                    <!-- when test="true" show Update, otherwise show Register -->
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Register"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </form:form>

    <br/>
    <br/>
    Go back to <a href="<c:url value='/phone/list' />">List of All Phones</a>

</body>
</html>
