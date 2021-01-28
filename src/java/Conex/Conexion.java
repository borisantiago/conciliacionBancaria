/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author rene_
 */
public class Conexion {
    Connection con;
    public Conexion(){
    try {
     Class.forName("com.mysql.jdbc.Driver");
     con=DriverManager.getConnection("jdbc:mysql://localhost:3306/conciliacionbancaria","root","");
    } catch (Exception e){
        System.err.println("Error: "+e);
    }
   } 
  public static void main(String[] args){
  Conexion cn=new Conexion();
  Statement st;
  ResultSet rs;
  try {
      st=cn.con.createStatement();
      rs=st.executeQuery("select * from banco");
      while (rs.next()){
          System.out.println(rs.getInt("IdBanco")+" " + rs.getString("nombreBanco")+" " + rs.getString("direccionBanco"));
          }  
      cn.con.close();
       } catch (Exception e){
       
       }
    }    
}
