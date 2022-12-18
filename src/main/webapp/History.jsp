<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: clara
  Date: 14.12.2022
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>History</title>
    </head>
    <body>
        <c:forEach var="value" items="${sessionScope.historyHashMap}">
            Name:  <c:out value="${value.key}" />
            Value:  <c:out value="${value.value}" /> <br/>
        </c:forEach>
    </body>
</html>