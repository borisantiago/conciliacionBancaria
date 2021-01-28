/* 2 MySqlConnectionFactory - Factory

 */
package Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * @author jrsc
 */
public final class MySqlConnectionFactory extends ConnectionDb {  //sublcase para establecer conexion con bd mysql
    //agragar constructor
    public MySqlConnectionFactory(String[] params) {  //recibve un array de string parametros
        this.params = params;    //el atributo params de superclase recibe el parametro del constructor
        this.open();        //llama al metodo que se implementa abajo
    }
    @Override
    public Connection open() {   //implementacion del metodo abstracto que abre bd
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/"+this.params[0]+"?serverTimezone=UTC", this.params[1], this.params[2]);  //subindice 0: nombre bd. paremtro 1: usr; parametro 2: clave.. llega en constructor
        } catch (Exception e){
            e.printStackTrace();
        }
        return this.connection;     //devuelve la conexion q es atributo declarada en superclase
    }
}