<HTML>
    <HEAD> <TITLE>LogIn</TITLE>
        <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    </HEAD>
    <BODY bgcolor="#FFFFFF" text="#000000">
        <H1>Log In</H1>
        <FORM name="form1" method="get" action="Controller">
            <TABLE border="0">
                <TR>
                    <TD>User ID</TD>
                    <TD width="83%"> <INPUT type="text" name="userID" /> </TD>
                </TR>
                <TR>
                    <TD width="17%">Password</TD>
                    <TD width="83%"> <INPUT type="password" name="password" /> </TD>
                </TR>
                <INPUT type="hidden" name="dispatchto" value="LogInUser" />
            </TABLE>
            <P> <INPUT type="submit" name="OK" value="LogIn"> </P>
            <a href="Formular.html">Register</a>
        </FORM>
    </BODY>
</HTML>