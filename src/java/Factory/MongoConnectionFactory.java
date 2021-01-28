/* clase para abrir una bd  en mongo
 */
package Factory;

import java.sql.Connection;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jrsc
 */
public final class MongoConnectionFactory extends ConnectionDb {
    private MongoClient mongoClient;    //Cliente Java MongoDB
    private MongoDatabase mongodb;      //Objeto base de datos

    public MongoConnectionFactory(String[] params) {  //recibve un array de string parametros
        this.params = params;    //el atributo params de superclase recibe el parametro del constructor
        this.open();        //llama al metodo que se implementa abajo
    }
    @Override
    public Connection open() {   //implementacion del metodo abstracto que abre bd
        try{
          setMongoClient(new MongoClient());
          setMongodb(getMongoClient().getDatabase("conciliacionbancaria"));
        } catch (Exception e){
            e.printStackTrace();
        }
        return this.connection;     //devuelve la conexion q es atributo declarada en superclase
    }

    @Override
    public MongoDatabase openCollection() {   //implementacion del metodo abstracto que abre bd
        try{
          setMongoClient(new MongoClient());
          setMongodb(getMongoClient().getDatabase("conciliacionbancaria"));
        } catch (Exception e){
            e.printStackTrace();
        }
        return this.mongodb;     //devuelve la conexion q es atributo declarada en superclase
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
}