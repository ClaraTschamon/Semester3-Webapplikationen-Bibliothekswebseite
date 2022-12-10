<html>
    <head>
        <title>LogIn</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" type="text/css" href="Browseransicht.css">
        <link rel="stylesheet" type="text/css" href="Druckansicht.css" media="print"/>
    </head>
    <body>
        <h1>Log In</h1>
        <div class="centerContent">
            <div class="container">
                <form name="form1"
                      method="get"
                      action="Controller">

                    <div class="input-control">
                        <label for="userID" class="bold">User ID</label>
                        <input type="text" id="userID" name="userID">
                        <div class="error"></div>
                        <!--hier nicht als errorelement verwendet sondern wegen css einstellungen-->
                    </div>
                    <div class="input-control">
                        <label for="password" class="bold">Passwort</label>
                        <input type="password" id="password" name="password" />
                        <div class="error"></div>
                    </div>
                    <input type="hidden" name="dispatchto" value="LogInUser" />
                    <!--
                    <table>
                        <tr>
                            <td>User ID</td>
                            <td> <input type="text" name="userID" /> </td>
                        </tr>
                        <tr>
                            <td>Passwort</td>
                            <td> <input type="password" name="password" /> </td>
                        </tr>
                        <input type="hidden" name="dispatchto" value="LogInUser" />
                    </table>-->
                    <button type="submit" name="OK" value="LogIn">LogIn</button>
                    <a href="Formular.html">Registrieren</a>
                </form>
            </div>
        </div>
    </body>
</html>