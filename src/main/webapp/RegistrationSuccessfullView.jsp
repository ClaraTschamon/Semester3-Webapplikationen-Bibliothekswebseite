<%--
  Created by IntelliJ IDEA.
  User: clara
  Date: 09.12.2022
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<html>
    <head>
        <title>Registrierung erfolgreich</title>
        <link rel="stylesheet" type="text/css" href="Browseransicht.css">
    </head>
    <body>
    <!--zurückpfeil-->
        <p>Deine UserID: ${sessionScope.currentUser.userID}</p>
        <p>Dein Passwort: ${sessionScope.currentUser.password}</p>
        <div class="topbar">
            <div class="left">
                <a href="home.html">
                    <img src="bilder/icons/weiterpfeil.png" id="back" alt="weiter Pfeil">
                </a>
            </div>
        </div>
    </body>
</html>
