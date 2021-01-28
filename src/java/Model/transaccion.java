/* 5 Transaccion - Model

*/
package Model;

import java.sql.Date;

/**
 * @author jrsc
 */
public class transaccion {
    private int IdTransaccion;
    private float valorTransaccion;
    private int IdCuentaBanco;
    private int tipoTransaccion;
    private Date fechaTransaccion;

    public transaccion() {  //coinstructor
        this.IdCuentaBanco = 1;
        this.IdTransaccion = 1;
        this.tipoTransaccion = 1;
        this.valorTransaccion = 0;
    }

    public transaccion(int IdTransaccion, float valorTransaccion, int IdCuentaBanco, int tipoTransaccion, Date fechaTransaccion) {
        this.IdTransaccion = IdTransaccion;
        this.valorTransaccion = valorTransaccion;
        this.IdCuentaBanco = IdCuentaBanco;
        this.tipoTransaccion = tipoTransaccion;
        this.fechaTransaccion = fechaTransaccion;
    }

    public void setIdTransaccion(int IdTransaccion) {
        this.IdTransaccion = IdTransaccion;
    }

    public void setValorTransaccion(float valorTransaccion) {
        this.valorTransaccion = valorTransaccion;
    }

    public void setIdCuentaBanco(int IdCuentaBanco) {
        this.IdCuentaBanco = IdCuentaBanco;
    }

    public void setTipoTransaccion(int tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public int getIdTransaccion() {
        return IdTransaccion;
    }

    public float getValorTransaccion() {
        return valorTransaccion;
    }

    public int getIdCuentaBanco() {
        return IdCuentaBanco;
    }

    public int getTipoTransaccion() {
        return tipoTransaccion;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }   
}