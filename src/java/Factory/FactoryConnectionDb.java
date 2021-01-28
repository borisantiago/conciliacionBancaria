/* 3_5 FactoryConnectionDb - Factory
 */
package Factory;

/**
 *
 * @author jrsc
 */
public class FactoryConnectionDb {
    public static final int MYSQL = 1;
    public static final int PGSQL = 2;
    public static final int MONGO = 3;

    public static String[] configMYSQL = {"conciliacion", "root", ""};  //configrar copnexion: nombre bd, usr, clave
    public static String[] configPGSQL = {"", "", ""};      //configrar copnexion: nombre bd, usr, clave
   public static String[] configMONGO = {"", "", ""};      //configrar copnexion: nombre bd, usr, clave
    
    //metodo que devuelve un objeto tipo coneectionDb (la primera clase)
    public static ConnectionDb open(int tipoBd){   //parametro indica si se conecta con MyQsl o PgSql
        switch(tipoBd){         //pasra determinar tipo de base de datos a laque se debe conectar
            case FactoryConnectionDb.MYSQL:     //abrir bd mysql
                return new MySqlConnectionFactory(configMYSQL);  //no hace falta break porque esta return
            case FactoryConnectionDb.PGSQL:     //abrir bd postgresql
                return new PgSqlConnectionFactory(configPGSQL);
           case FactoryConnectionDb.MONGO:     //abrir bd MongoDb
               return new MongoConnectionFactory(configMONGO);
            default:        //si no se indica a q bd se conecta, devuelve nulo
                return null;
        }
    }

    //metodo que devuelve un objeto tipo coneectionDb (la primera clase)
    public static ConnectionDb openCollection(int tipoBd){   //parametro indica si se conecta con MyQsl o PgSql
        switch(tipoBd){         //pasra determinar tipo de base de datos a laque se debe conectar
            case FactoryConnectionDb.MONGO:     //abrir bd MongoDb
                return new MongoConnectionFactory(configMONGO);
            default:        //si no se indica a q bd se conecta, devuelve nulo
                return null;
        }
    }
}