//7 cuentabancoDaoImpl - Dao

package Dao;

import Factory.ConnectionDb;
import Factory.FactoryConnectionDb;
import Model.cuentabanco;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

//  @author jrsc
 
public class cuentabancoDaoImpl implements cuentabancoDao {
    ConnectionDb conn;
    private MongoClient mongoClient;    //Cliente Java MongoDB
    private MongoDatabase mongodb;      //Objeto base de datos

    public cuentabancoDaoImpl() {  //constructor vacio
    }

    @Override
    public List<cuentabanco> list() {      // listado desde la bd mysql
        this.conn = FactoryConnectionDb.open(FactoryConnectionDb.MYSQL); //inicializa conexion por defecto a MySql

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM cuentabanco");  //construye la cadena de consulta
        
        List<cuentabanco> list = new ArrayList<>(); //list es la lista de cuentas bancarias
        
        try{
            ResultSet rs = this.conn.query(sql.toString());  //ejecuta la consulta
            while (rs.next()){  //mientras haya registros en la tabla
                cuentabanco cuentabanco = new cuentabanco();  //variable cuentabanco de tipo cuentabanco recibe un nuevo objeto cuentabanco
                cuentabanco.setIdBanco(rs.getInt("IdCuentaBanco"));   //recupera datos del resultset y los almacena en los atributos del objeto temporal cuentabanco
                cuentabanco.setTitularCuenta(rs.getString("titularCuenta"));
                cuentabanco.setSaldoInicial(rs.getFloat("saldoInicial"));
                cuentabanco.setSaldoDisponible(rs.getFloat("saldoDisponible"));
                list.add(cuentabanco);  //añade el objeto temporal en la lista
            }
            
        } catch (Exception e) {
            
        } finally {
            this.conn.close();      //cierra la conexion
        }
        return list;    //devuelve la lista generada
    }  

    @Override
    public List<cuentabanco> list2() {  // metodo listar
        this.conn = FactoryConnectionDb.open(FactoryConnectionDb.PGSQL); //inicializa conexion por defecto a MySql

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM cuentabanco");  //construye la cadena de consulta
        
        List<cuentabanco> list = new ArrayList<>(); //list es la lista de cuentas bancarias
        
        try{
            ResultSet rs = this.conn.query(sql.toString()); // ejecuta la consulta
            while (rs.next()){  //mientras haya registros en la tabla
                cuentabanco cuentabanco = new cuentabanco();  //variable cuentabanco de tipo cuentabanco recibe un nuevo objeto cuentabanco
                cuentabanco.setIdBanco(rs.getInt("IdCuentaBanco"));  // recupera datos del resultset y los almacena en los atributos del objeto temporal cuentabanco
                cuentabanco.setTitularCuenta(rs.getString("titularCuenta"));
                cuentabanco.setSaldoInicial(rs.getFloat("saldoInicial"));
                cuentabanco.setSaldoDisponible(rs.getFloat("saldoDisponible"));
                
                list.add(cuentabanco);  //añade el objeto temporal en la lista
            }
            
        } catch (Exception e) {
            
        } finally {
            this.conn.close();     // cierra la conexion
        }
        return list;   // devuelve la lista generada
    }

    @Override
    public List<cuentabanco> list3() {      // listado desde la bd MongoDb
        //       this.mongodb = FactoryConnectionDb.openCollection(FactoryConnectionDb.MONGO); //inicializa conexion por defecto a MongoDb
        List<cuentabanco> list = new ArrayList<>(); //list es la lista de cuentas bancarias
        this.conectarBd();
         //Para devolver todos los documentos en una colección, llamamos al método
        FindIterable<Document> iterable = getMongodb().getCollection("cuentasBancarias").find();

        iterable.forEach(new Block<Document>(){
             @Override
             public void apply(final Document document){
                cuentabanco cuentabanco = new cuentabanco();  //variable cuentabanco de tipo cuentabanco recibe un nuevo objeto cuentabanco
                cuentabanco.setIdBanco(document.getInteger("idCuentaBanco"));  // recupera datos del resultset y los almacena en los atributos del objeto temporal cuentabanco
                cuentabanco.setTitularCuenta(document.getString("titularCuenta"));
                cuentabanco.setSaldoInicial(Float.parseFloat(String.valueOf(document.getDouble("saldoInicial"))));
                cuentabanco.setSaldoDisponible(Float.parseFloat(String.valueOf(document.getDouble("saldoDisponible"))));
                list.add(cuentabanco);  //añade el objeto temporal en la lista
            }
        });
        //se recorre los resultados y aplica un bloque para cada documento
        return list;   // devuelve la lista generada
    }
        
    @Override
    public cuentabanco edit(int id) {
        this.conn = FactoryConnectionDb.open(FactoryConnectionDb.MYSQL);
        cuentabanco cuentabanco = new cuentabanco();        //declarar objeto cuentabanco que pertenece a clase cuentabanco
        
        StringBuilder sql = new StringBuilder();    //para almacenar la consulta e efectuar en la bd
        sql.append("SELECT * FROM cuentabanco WHERE IdCuentaBanco = ").append(id);   //cadena de consulta
        
        try {
            ResultSet rs = this.conn.query(sql.toString());  //carga todos los registros que cumplen con la condicion del sql

            while (rs.next()){          //mientras haya registros cargados en el reseltset
                cuentabanco.setIdBanco(rs.getInt("IdCuentaBanco"));  // recupera datos del resultset y los almacena en los atributos del objeto temporal cuentabanco
                cuentabanco.setTitularCuenta(rs.getString("titularCuenta"));
                cuentabanco.setSaldoInicial(rs.getFloat("saldoInicial"));
                cuentabanco.setSaldoDisponible(rs.getFloat("saldoDisponible"));
            }
        } catch (Exception e) {
            
        } finally {
            this.conn.close();          //cierra la conexion
        }
        return cuentabanco;             //devuelve el objeto creado
    }

    @Override
    public cuentabanco editMongo(int id) {
        cuentabanco cuentabanco = new cuentabanco();  //variable cuentabanco de tipo cuentabanco recibe un nuevo objeto cuentabanco
 
        try {
            this.conectarBd();
            FindIterable<Document> iterable = getMongodb().getCollection("cuentasBancarias").find(new Document("idCuentaBanco", id));
        
            iterable.forEach(new Block<Document>(){
                    @Override
                    public void apply(final Document document){
                        cuentabanco.setIdBanco(document.getInteger("idCuentaBanco"));  // recupera datos del resultset y los almacena en los atributos del objeto temporal cuentabanco
                        cuentabanco.setTitularCuenta(document.getString("titularCuenta"));
                        cuentabanco.setSaldoInicial(Float.parseFloat(String.valueOf(document.getDouble("saldoInicial"))));
                        cuentabanco.setSaldoDisponible(Float.parseFloat(String.valueOf(document.getDouble("saldoDisponible"))));
                    }
            });
        } catch (Exception ex) {
            Logger.getLogger(cuentabancoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuentabanco;             //devuelve el objeto creado
    }
    
    @Override
    public boolean save(cuentabanco cuentabanco) {
        this.conn = FactoryConnectionDb.open(FactoryConnectionDb.MYSQL);    //abrir la conexion con bd mysql
        boolean save = true;        //bandera para indicar si se almacenaron los cambios
        
        try {
            if (cuentabanco.getIdCuentaBanco() == 0) {  //es cero cuando se esta ingresando un registro nuevo: ver inicializac'on del atributo 
                StringBuilder sql = new StringBuilder();   //para crear la sentencia sql
                sql.append("INSERT INTO cuentabanco (titularCuenta, saldoInicial, saldoDisponible) VALUES ('").append(cuentabanco.getTitularCuenta());
                sql.append("', ").append(cuentabanco.getSaldoInicial());      //crear la cadena de conexion
                sql.append(", ").append(cuentabanco.getSaldoDisponible()).append(")");      //crear la cadena de conexion
                this.conn.execute(sql.toString());      //ejecuta la query
            } else {   //es un registro previamente existente: estamos actualizando
                StringBuilder sql = new StringBuilder();   //para crear la sentencia sql
                sql.append("UPDATE cuentabanco SET idCuentaBanco = ").append(cuentabanco.getIdCuentaBanco());
                sql.append(", titularCuenta = '").append(cuentabanco.getTitularCuenta());
                sql.append("', saldoInicial = ").append(cuentabanco.getSaldoInicial());      //crear la cadena de conexion
                sql.append(",  saldoDisponible = ").append(cuentabanco.getSaldoDisponible()).append(" WHERE idCuentaBanco = ").append(cuentabanco.getIdCuentaBanco());      //crear la cadena de conexion
                this.conn.execute(sql.toString());      //ejecuta la query
            }
            save = true;                                //cambia estado de bandera
        } catch(Exception e){
            
        } finally {
            this.conn.close();      //cerrar la conexion
        }
        return save; 
    }

    @Override
    public boolean saveMongo(cuentabanco cuentabanco) {
        this.conectarBd();
        boolean save = true;        //bandera para indicar si se almacenaron los cambios

        try {            
            //Añadimos un documento a la base de datos directamente
            getMongodb().getCollection("cuentasBancarias").insertOne(
                    new Document()
                        .append("idCuentaBanco", cuentabanco.getIdCuentaBanco())
                        .append("titularCuenta", cuentabanco.getTitularCuenta())
                        .append("saldoInicial", cuentabanco.getSaldoInicial())
                        .append("saldoDisponible", cuentabanco.getSaldoDisponible()));
        } catch (Exception ex) {
            Logger.getLogger(cuentabancoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return save; 
    }

    @Override
    public boolean actualizarMongo(cuentabanco cuentabanco) {
        this.conectarBd();
        boolean save = true;        //bandera para indicar si se almacenaron los cambios
        
        try {            
            Document doc = new Document();
            doc.append("titularCuenta",cuentabanco.getTitularCuenta());
            doc.append("saldoInicial",cuentabanco.getSaldoInicial());
            doc.append("saldoDisponible",cuentabanco.getSaldoDisponible());
            
            MongoCollection<Document> collection = getMongodb().getCollection("cuentasBancarias");
            collection.updateOne(eq("idCuentaBanco", cuentabanco.getIdCuentaBanco()), new Document("$set", doc));
            
        } catch (Exception ex) {
            Logger.getLogger(cuentabancoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return save; 
    }
    
    @Override
    public boolean delete(int id) {
        boolean delete = false;                     //bandera que indica resultado de operacion

        this.conn = FactoryConnectionDb.open(FactoryConnectionDb.MYSQL);    //abrir la conexion con bd mysql
        
        try{
            StringBuilder sql = new StringBuilder();   //para crear la sentencia sql
            sql.append("DELETE FROM cuentabanco WHERE IdCuentaBanco = ").append(id);    //crea la sentencia de borrado
            this.conn.execute(sql.toString());              //ejecuta sentencia sql
            delete = true;
            
        } catch (Exception e) {
            
        } finally {
            this.conn.close();                  //cierra la conexion
        }
        return delete;                              //devuelve el valor de la bandera
    }

    @Override
    public boolean deleteMongo(int id) {
        boolean delete = false;                     //bandera que indica resultado de operacion
        this.conectarBd();

        MongoCollection<Document> collection = getMongodb().getCollection("cuentasBancarias");
        try{
           collection.deleteOne(eq("idCuentaBanco",id));
        } catch (Exception ex) {
            Logger.getLogger(cuentabancoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return delete;                              //devuelve el valor de la bandera
    }
    //////////////////////////
    @Override
    public List<cuentabanco> view(int id) {      // consulta de una cuenta de banco en  la bd mysql
        this.conn = FactoryConnectionDb.open(FactoryConnectionDb.MYSQL); //inicializa conexion por defecto a MySql

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM cuentabanco WHERE IdCuentaBanco = ").append(id);    //crea la sentencia de borrado
        
        List<cuentabanco> list = new ArrayList<>(); //list es la lista de cuentas bancarias
        
        try{
            ResultSet rs = this.conn.query(sql.toString());  //ejecuta la consulta
            while (rs.next()){  //mientras haya registros en la tabla
                cuentabanco cuentabanco = new cuentabanco();  //variable cuentabanco de tipo cuentabanco recibe un nuevo objeto cuentabanco
                cuentabanco.setIdBanco(rs.getInt("IdCuentaBanco"));   //recupera datos del resultset y los almacena en los atributos del objeto temporal cuentabanco
                cuentabanco.setTitularCuenta(rs.getString("titularCuenta"));
                cuentabanco.setSaldoInicial(rs.getFloat("saldoInicial"));
                cuentabanco.setSaldoDisponible(rs.getFloat("saldoDisponible"));
                
                list.add(cuentabanco);  //añade el objeto temporal en la lista
            }
        } catch (Exception e) {
            
        } finally {
            this.conn.close();      //cierra la conexion
        }
        return list;    //devuelve la lista generada
    }  
    
    public MongoClient getMongoClient() {
        return mongoClient;
    }
 
    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }
 
    public MongoDatabase getMongodb() {
        return mongodb;
    }
 
    public void setMongodb(MongoDatabase mongodb) {
        this.mongodb = mongodb;
    } 

    public void conectarBd(){
        setMongoClient(new MongoClient());
        setMongodb(getMongoClient().getDatabase("conciliacion"));
    }
}
