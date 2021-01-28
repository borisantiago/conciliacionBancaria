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
                <h4>Ingreso de Cuentas Bancarias</h4><!-- comment -->
                <table class="table table-bordered" style="width: 600px;">
                     <tr>
                        <td><label>Id.Cuenta Bancaria: </label></td>
                        <td><input type="text" id="idCuentaBanco" name="idCuentaBanco" value="<%= cuentaBco.getIdCuentaBanco() %>" disabled="true"> </td> 
                    </tr>
                    <tr>
                        <td><label>Titular: </label></td>
                        <td><input type="text" id="titularCuenta" name="titularCuenta" value="<%= cuentaBco.getTitularCuenta()%>"> </td><!-- comment -->
                    </tr>
                    <tr>
                        <td><label>Saldo Inicial: </label></td>
                        <td><input type="text" id="saldoInicial" name="saldoInicial" value="<%= cuentaBco.getSaldoInicial() %>"></td><!-- comment -->
                    </tr>
                    <tr>
                        <td><label>Saldo Disponible: </label></td>
                        <td><input type="text" id="saldoDisponible" name="saldoDisponible" value="<%= cuentaBco.getSaldoDisponible() %>"></td><!-- comment -->
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="submit" class="btn btn-lg btn-success"> Aceptar </button>
                            <button type="button" class="btn btn-lg btn-warning" onclick="regresar('<%= request.getContextPath() %>/cuentasbcos?op=list')" value="Regresar" /> Regresar </button>
                        </td>
                    </tr>
                </table><!-- comment -->
            </form>
                <script type+"text/javascript">
                    function regresar(url){
                        location.href = url;
                    }
                </script>
        </div>
        <%@include file="../WEB-INF/jspf/bottom.jspf" %>
    </body>
</html>
