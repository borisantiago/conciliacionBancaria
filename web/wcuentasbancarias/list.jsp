<%-- 
    Document   : index
    Created on : 20-ene-2021, 17:29:23
    Author     : jrsc
--%>

<%-- 
    Document   : index
    Created on : 20-ene-2021, 17:29:23
    Author     : jrsc
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%@page import="Model.cuentabanco" %>
<jsp:useBean id="list" scope="session" class="java.util.List" />

<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>


<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> lista = new ArrayList<Map<Object,Object>>();
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                       
        <%@include file="../WEB-INF/jspf/jscss.jspf" %>
        <title>Aplicaciones Informáticas II</title>
    </head>
    <body>
        <%@include file="../WEB-INF/jspf/top.jspf" %>
        <div style="width: 500px;">
            <a href="<%=request.getContextPath() %>/cuentasbcos?op=create" class="btn btn-lg btn-success" > Nuevo </a><!-- comment -->
            
        </div><br>
        <div class="row">
            <div class="col-md-6">
                <div class="card card-body">
                    <table class="table table-striped table-bordered" >
                <tr align="center">
                    <th>Id</th>
                    <th>Titular</th>
                    <th>Saldo Inicial</th>
                    <th>Saldo Disponible</th>
                    <th></th>
                </tr>
                <% 
                    for (int i = 0; i < list.size(); i++) {
                    cuentabanco cuentabanco = new cuentabanco();
                    cuentabanco = (cuentabanco) list.get(i);
                    map = new HashMap<Object,Object>();
                    map.put("label", cuentabanco.getIdCuentaBanco());
                    map.put("y", cuentabanco.getSaldoDisponible());
                    lista.add(map);
                %>
                <tr>
                    <td><%= cuentabanco.getIdCuentaBanco() %></td>
                    <td><%= cuentabanco.getTitularCuenta() %></td>
                    <td align="right"><%= cuentabanco.getSaldoInicial() %></td>
                    <td align="right"><%= cuentabanco.getSaldoDisponible() %></td>
                    <td>
                        <a href="<%=request.getContextPath() %>/cuentasbcos?op=create" class="btn btn-sm btn-warning" > Editar </a><!-- comment -->
                        <a href="<%=request.getContextPath() %>/cuentasbcos?op=create" class="btn btn-sm btn-danger" > Eliminar </a><!-- comment -->
                    </td>
                </tr>
                <% } %>
                <% String dataPoints = gsonObj.toJson(lista);%>
            </table>
                    
                </div>
            </div>
            <div class="col-md-6">
                <div class="card card-body">
                    
                    <script type="text/javascript">
                        window.onload = function() { 

                        var chart = new CanvasJS.Chart("chartContainer", {
                                theme: "light2",
                                title: {
                                        text: "Saldo incial de las cuentas"
                                },
                                axisX: {
                                        title: "Total de las cuentas"
                                },
                                axisY: {
                                        title: "Saldo inicial",
                                        includeZero: true
                                },
                                data: [{
                                        type: "line",
                                        yValueFormatString: "#,##0 dolares",
                                        dataPoints : <%out.print(dataPoints);%>
                                }]
                        });
                        chart.render();

                        }
                    </script>
                    
                    <div id="chartContainer" style="height: 370px; width: 95%;"></div>
                        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
                  
                    
                </div>
            </div>
            
            
            
           
                        
                        
        <%@include file="../WEB-INF/jspf/bottom.jspf" %>
    </body>
    <footer>
    
    </footer>
</html>
              




