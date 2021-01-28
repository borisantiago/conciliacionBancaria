<%@page import="Model.cuentabanco"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="cuentaBco" scope="session" class="Model.cuentabanco" />


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../WEB-INF/jspf/jscss.jspf" %>
        <title>Aplicaciones Informáticas II</title>
    </head>
    <body>
        <%@include file="../WEB-INF/jspf/top.jspf" %>
        <div>
            <form id="formCuenta" name="formCuenta" action="<%= request.getContextPath()%>/cuentasbcos" method="post">
                <h4>Visualización de Cuentas Bancarias</h4><!-- comment -->
            </form>
        </div>
        <%@include file="../WEB-INF/jspf/bottom.jspf" %>
    </body>
</html>
