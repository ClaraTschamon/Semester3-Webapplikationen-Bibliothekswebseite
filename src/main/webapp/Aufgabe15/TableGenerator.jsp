<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: clara
  Date: 14.12.2022
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Generate Table</title>
    </head>
    <body>


<%--        <c:forEach begin="1" end="5" var="current">--%>
<%--            <c:out value="${current}"/>--%>
<%--        </c:forEach>--%>
<%--    <ul>--%>
<%--        <c:forTokens items="a,b,c,d,e,f" delims="," var="current" begin="2" end="4">--%>
<%--        <li><c:out value="${current}"/></li>--%>
<%--        </c:forTokens>--%>
<%--    </ul>--%>

    <table>
        <c:forEach begin="0" end="${sessionScope.numberRows}" varStatus="loop">
            <tr><c:forEach begin="0" end="${sessionScope.numberCols}" varStatus="loop">
                    <td>Test</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    </body>
</html>
