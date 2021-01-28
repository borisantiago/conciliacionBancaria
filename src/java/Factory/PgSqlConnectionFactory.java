/* 3 PgSqlConnectionFactory - Factory
 *
 */
package Factory;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author jrsc
 */
public final class PgSqlConnectionFactory extends ConnectionDb{
    //agragar constructor
    public PgSqlConnectionFactory(String[] params) {  //recibve un array de string parametros
        this.params = params;    //el atributo params de superclase recibe el parametro del constructor
        this.open();        //llama al metodo que se implementa abajo
    }
    @Override
    public Connection open() {   //implementacion del metodo abstracto que abre bd
        try{
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql:5432//localhost/"+this.params[0], this.params[1], this.params[2]);  //subindice 0: nombre bd. paremtro 1: usr; parametro 2: clave.. llega en constructor
        } catch (Exception e){
            e.printStackTrace();
        }
        return this.connection;     //devuelve la conexion q es atributo declarada en superclase
    }

}