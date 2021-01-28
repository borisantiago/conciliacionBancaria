/* 6 cuentabancoDao - Dao
 
 */
package Dao;

import Model.cuentabanco;
import java.util.List;

/**
 * @author jrsc
 */
public interface cuentabancoDao {
    public List<cuentabanco> list();    //prototipo metodo listar desde mysql
    public List<cuentabanco> list2();   //prototipo metodo listar desde pgsql
    public cuentabanco edit(int id);    //prototipo metodo editar desde mysql
    public cuentabanco editMongo(int id);    //prototipo metodo editar desde mysql
    public boolean save (cuentabanco cuentabanco);   //prototipo metodo guardar en mysql
    public boolean saveMongo (cuentabanco cuentabanco);   //prototipo metodo guardar en MongoDb
    public boolean actualizarMongo(cuentabanco cuentabanco); //prototipo metodo ue actualiza datos en mongo
    public boolean delete(int id);      //prototipo metodo borrar desde mysql
    public boolean deleteMongo(int id);      //prototipo metodo borrar desde Mongo
    public List<cuentabanco> view(int id);    //prototipo metodo listar desde mysql cuentas bancarias con id especifico
    
    public List<cuentabanco> list3();   //prototipo metodo listar desde MongoDb
}