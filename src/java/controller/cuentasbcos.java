/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.cuentabancoDao;
import Dao.cuentabancoDaoImpl;
import Model.cuentabanco;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jrsc
 */
public class cuentasbcos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet cuentasbcos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cuentasbcos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pagina = "/wcuentasbancarias/list.jsp";
        cuentabancoDao cuenta = new cuentabancoDaoImpl();   //crea una instancia del objeto dao
        HttpSession sesion = request.getSession(true);  //crea una instancia para el manejo de sesiones
        sesion.setAttribute("list", cuenta.list());     //la consulta del listado lo asigna a la sesion
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);    //obtiene el resultado del servlet
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = (request.getParameter("op") != null ? request.getParameter("op") : "list");  //captura el parametro que recibe: si no es nulo, lo pone en op. Si el nulo, pone list
        String pagina;   //declara la pagina a la que se dirigira el control
        
        if (op.equals("create")){   //si el parametro recibido es para crear
            pagina = "/wcuentasbancarias/create.jsp";  //almacena el nombre de la pagina a la que dirigira los contenidos
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);   //prepara los valores para enviar a la pagina de creacion de la cuenta bancaria
            dispatcher.forward(request, response);  //transfiere el control y datos a pantalla de creacion de cuenta
        } else {  //si no recibo el parametro de creacion de nueva cuenta
            this.list(request, response);  //llama al listado de cuentas banacrias
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        cuentabanco cuentabanco = new cuentabanco();  //declarar un objeto que implementa el modelo del registro de las cuentas bancarias
        cuentabancoDao cuentabancoDao = new cuentabancoDaoImpl();    //declara un objeto para acceder a los metodos que actualizan la bd
        
        
  //      cuentabanco.setIdBanco(Integer.parseInt(request.getParameter("idCuentaBanco")));
        cuentabanco.setTitularCuenta(request.getParameter("titularCuenta"));
        cuentabanco.setSaldoInicial(Float.parseFloat(request.getParameter("saldoInicial")));
        cuentabanco.setSaldoDisponible(Float.parseFloat(request.getParameter("saldoDisponible")));
        
        if (cuentabanco.getIdCuentaBanco()== 0){  //no hay numero de id 0> esta ingresando
            cuentabancoDao.save(cuentabanco);   //llama al metodo para guardar el objeto cuentabanco que acaba de configurarse
        }

        this.list(request, response);  //vuelve a llamar al metodo para listar las cuentas
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
