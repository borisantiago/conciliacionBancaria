/* 4 cuentabanco - Model
 
 */
package Model;

/**
  * @author jrsc
 */
public class cuentabanco {  //modelo para tabla cuentaBanco
    private int IdCuentaBanco;
    private String titularCuenta;
    private float saldoInicial;
    private float saldoDisponible;

    public cuentabanco() {  //constructor iniciliaza valores por defecto en atributos
        this.IdCuentaBanco = 0;
        this.titularCuenta = "";
        this.saldoInicial = 0;
        this.saldoDisponible = 0;
    }

    public cuentabanco(int IdCuentaBanco, String titularCuenta, float saldoInicial, float saldoDisponible) {   //constructor
        this.IdCuentaBanco = IdCuentaBanco;
        this.titularCuenta = titularCuenta;
        this.saldoInicial = saldoInicial;
        this.saldoDisponible = saldoDisponible;
    }

    public void setIdBanco(int IdCuentaBanco) {
        this.IdCuentaBanco = IdCuentaBanco;
    }

    public void setTitularCuenta(String titularCuenta) {
        this.titularCuenta = titularCuenta;
    }

    public void setSaldoInicial(float saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public void setSaldoDisponible(float saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public int getIdCuentaBanco() {
        return IdCuentaBanco;
    }

    public String getTitularCuenta() {
        return titularCuenta;
    }

    public float getSaldoInicial() {
        return saldoInicial;
    }

    public float getSaldoDisponible() {
        return saldoDisponible;
    }   
}