/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wServicios;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import Dao.cuentabancoDao;
import Dao.cuentabancoDaoImpl;
import Model.cuentabanco;
import java.util.List;
/**
 *
 * @author jrsc
 */
@WebService(serviceName = "existeCuenta")
public class existeCuenta {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "existe")
    public Boolean existe(@WebParam(name = "idCuentaBanco") int idCuentaBanco) {
        cuentabancoDao cuentabancoDao = new cuentabancoDaoImpl();
        
        List<cuentabanco> list = cuentabancoDao.view(idCuentaBanco);
        
        for (cuentabanco cuentabanco : list){
            return true;        //existe por lo menos una cuenta con este idcuentaBanco
        }
        return false;
    }
}
