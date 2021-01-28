/* 8 test

*/
package test;

import Dao.cuentabancoDao;
import Dao.cuentabancoDaoImpl;
import Model.cuentabanco;
import java.util.List;

/**
 * @author jrsc
 */
public class Test {
    public static void main(String[] args){  //programa principal de la prueba
        Test test = new Test();     //crea un objeto para la prueba
        
     //   test.saveMySql(2);       //inserta una nueva cuenta bancaria,  
//        test.saveMongo(2);       //1 = inserta, 2 = Modifica  un Registro en Mongo,  

        // test.editMySql();       //llamada al metodo para editar desde mysql
//        test.editMongo();       //llamada al metodo para editar desde mysql
        test.deleteMySql();       //llamada al metodo para probar delete ebn mysql
       // test.deleteMongo();       //llamada al metodo para probar delete ebn mysql
//        test.listMySql();       //llamada al metodo para prueba listar desde mysql

//        test.viewMySql();       //llamada al metodo para prueba listar cuentas bancarias especificas desde mysql

         // test.listMySql();       //llamada al metodo para prueba listar desde mysql
//        test.listPgSql();       //llamada al metodo para prueba listar desde pgsql
       // test.listMongo();       //llamada al metodo para prueba listar desde Mongo
    }
    
    public void listMySql(){   //metodo para probar conexion a MySql
        cuentabancoDao cuentabancoDao = new cuentabancoDaoImpl(); //atributo cuentabancoDAo es de tipo cuentabancoDao e inicia como nuevo cuentabancodaoimpl
        List<cuentabanco> list = cuentabancoDao.list();     //declaro lis y genero el listado desde mysql

        System.out.println("--- Listado generado desde MySql ---");
        list.forEach(cuentabanco -> {
            System.out.println("Id: "+cuentabanco.getIdCuentaBanco()+" - Titular: "+cuentabanco.getTitularCuenta()+" - Saldo Inicial: "+cuentabanco.getSaldoDisponible()+" - Saldo Dispònible: "+cuentabanco.getSaldoDisponible());
        });
    }
    
    public void listPgSql(){        //metodo para prpbar conexion a pgsql
        cuentabancoDao cuentabancoDao = new cuentabancoDaoImpl(); //atributo cuentabancoDAo es de tipo cuentabancoDao e inicia como nuevo cuentabancodaoimpl
        List<cuentabanco> list = cuentabancoDao.list2();     //declaro lis y genero el listado desde mysql
        
        System.out.println("--- Listado generado desde PgSql ---");
        list.forEach(cuentabanco -> {
            System.out.println("Id: "+cuentabanco.getIdCuentaBanco()+" - Titular: "+cuentabanco.getTitularCuenta()+" - Saldo Inicial: "+cuentabanco.getSaldoDisponible()+" - Saldo Dispònible: "+cuentabanco.getSaldoDisponible());
        });
    }
    
    public void listMongo(){   //metodo para listar los documentos registradops en Mongo

        cuentabancoDao cuentabancoDao = new cuentabancoDaoImpl(); //atributo cuentabancoDAo es de tipo cuentabancoDao e inicia como nuevo cuentabancodaoimpl
        List<cuentabanco> list = cuentabancoDao.list3();     //declaro lis y genero el listado desde mysql
        
        System.out.println("--- Listado generado desde Mongo ---");
        for (cuentabanco cuentabanco : list){
            System.out.println("Id: "+cuentabanco.getIdCuentaBanco()+" - Titular: "+cuentabanco.getTitularCuenta()+" - Saldo Inicial: "+cuentabanco.getSaldoDisponible()+" - Saldo Dispònible: "+cuentabanco.getSaldoDisponible());
        }
    }

    public void editMySql(){            //metodo para probar el edit con mysql
        cuentabancoDao cuentabancoDao = new cuentabancoDaoImpl();   //atributo cuentabancoDAo es de tipo cuentabancoDao e inicia como nuevo cuentabancodaoimpl
        cuentabanco cuentabanco = cuentabancoDao.edit(2);           //se va a editar el registro con IdBanco = 2
        System.out.println("--- Edit con MySql ---");
        System.out.println("Id: "+cuentabanco.getIdCuentaBanco()+" - Titular: "+cuentabanco.getTitularCuenta());
    }

    public void editMongo(){            //metodo para probar el edit con mysql
        cuentabancoDao cuentabancoDao = new cuentabancoDaoImpl();   //atributo cuentabancoDAo es de tipo cuentabancoDao e inicia como nuevo cuentabancodaoimpl
        cuentabanco cuentabanco = cuentabancoDao.editMongo(100);           //se va a editar el registro con IdBanco = 2
        System.out.println("--- Edit con Mongo ---");
        System.out.println("Id: "+cuentabanco.getIdCuentaBanco()+" - Titular: "+cuentabanco.getTitularCuenta());
    }
    
    public void saveMySql(int ope){            //metodo para porbar el grabar
        cuentabancoDao cuentabancoDao = new cuentabancoDaoImpl();   //atributo cuentabancoDAo es de tipo cuentabancoDao e inicia como nuevo cuentabancodaoimpl
        cuentabanco cuentabanco = new cuentabanco();
        
        if (ope == 1){     //1 es insertar nuevo registro
            //cuentabanco.setTitularCuenta("Juan Perez");  //pone valores para el nuevo registro
            //cuentabanco.setSaldoInicial(7);
            //cuentabanco.setSaldoDisponible(700);
        } else {    //otro valor es para modificar un registro
            cuentabanco.setTitularCuenta("Micaela Ortiz");  //pone valores para el nuevo registro
            cuentabanco.setSaldoInicial(9);
            cuentabanco.setIdBanco(2);       //para modificar la cuenta con id = 2
            cuentabanco.setSaldoDisponible(8000);
        }
        cuentabancoDao.save(cuentabanco);       //graba los datos nuevo registro
    }

    public void saveMongo(int ope){            //metodo para porbar el grabar
        cuentabancoDao cuentabancoDao = new cuentabancoDaoImpl();   //atributo cuentabancoDAo es de tipo cuentabancoDao e inicia como nuevo cuentabancodaoimpl
        cuentabanco cuentabanco = new cuentabanco();
        
        if (ope == 1){     //1 es insertar nuevo registro
            cuentabanco.setIdBanco(5);       //para modificar la cuenta con id = 2
            cuentabanco.setTitularCuenta("EEEE EEEE EEEE");  //pone valores para el nuevo registro
            cuentabanco.setSaldoInicial((float)500.18);
            cuentabanco.setSaldoDisponible((float)500.18);
            cuentabancoDao.saveMongo(cuentabanco);       //graba el nuevo registro
        } else {    //otro valor es para modificar un registro
            cuentabanco.setIdBanco(5);       //para modificar la cuenta con id = 2
            cuentabanco.setTitularCuenta("ZZZZ ZZZZ ZZZZ");  //pone valores para el nuevo registro
            cuentabanco.setSaldoInicial((float)200.20);
            cuentabanco.setSaldoDisponible((float)200.20);
            cuentabancoDao.actualizarMongo(cuentabanco);       //graba el nuevo registro
        }
    }

    public void deleteMySql(){          //metodo para eliminacion registros de mysql
        cuentabancoDao cuentabancoDao = new cuentabancoDaoImpl();   //atributo cuentabancoDAo es de tipo cuentabancoDao e inicia como nuevo cuentabancodaoimpl
        cuentabancoDao.delete(3);                                   //borrar el registro de id 2
    }

    public void deleteMongo(){          //metodo para eliminacion registros de mysql
        cuentabancoDao cuentabancoDao = new cuentabancoDaoImpl();   //atributo cuentabancoDAo es de tipo cuentabancoDao e inicia como nuevo cuentabancodaoimpl
        cuentabancoDao.deleteMongo(5);                                   //borrar el registro de id 2
    }
    
    public void viewMySql(){   //metodo para probar existencia de una cuenta bancaria en bd MySql
        cuentabancoDao cuentabancoDao = new cuentabancoDaoImpl(); //atributo cuentabancoDAo es de tipo cuentabancoDao e inicia como nuevo cuentabancodaoimpl
        List<cuentabanco> list = cuentabancoDao.view(1);     //declaro lis y genero el listado desde mysql

        System.out.println("--- VIEW desde MySql ---");
        list.forEach(cuentabanco -> {
            System.out.println("Id: "+cuentabanco.getIdCuentaBanco()+" - Titular: "+cuentabanco.getTitularCuenta()+" - Saldo Inicial: "+cuentabanco.getSaldoInicial()+" - Saldo Dispònible: "+cuentabanco.getSaldoDisponible());
        });
    }
}
