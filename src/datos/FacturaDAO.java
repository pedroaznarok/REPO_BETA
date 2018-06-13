/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class FacturaDAO extends database {
   
    /**
     * Registra un nuevo producto
     */
    public boolean NuevaFactura(String numerofactura, String nombre, String monto, String tipo, String fecha, String pago) {
        if (valida_datosf(numerofactura, nombre, monto, tipo, fecha, pago)) {
            //se reemplaza "," por "."
            monto = monto.replace(",", ".");
            //Se arma la consulta
            String q = ("INSERT INTO db.factura ( f_nro, f_nombre ,f_monto , f_tipo ,f_fecha, f_pago  ) " + "VALUES ( " + numerofactura + ",'" + nombre + "', " + monto + ",'" + tipo + "','" + fecha + "','"  + pago +  "' ) ");
            //se ejecuta la consulta

            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * Registra un nuevo producto
     */
    public boolean ModificarFactura(String numerofactura, String nombre, String monto, String tipo, String fecha, String pago) {
        if (valida_datosf(numerofactura, nombre, monto, tipo, fecha, pago)) {
            //se reemplaza "," por "."
            monto = monto.replace(",", ".");
            //Se arma la consulta
            String q = "UPDATE `db`.`factura` SET `f_nombre`='" + nombre + "', f_monto =" + monto + ", f_tipo ='" + tipo + "', f_fecha ='" + fecha + "', f_pago ='" + pago + "'  WHERE `f_nro`=" + numerofactura + ";" ;
            //se ejecuta la consulta

            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * Elimina un registro dado su ID
     */
    public boolean EliminarFactura(String numerofactura) {
        boolean res = false;
        //se arma la consulta
        String q = " DELETE FROM db.factura WHERE  f_nro='" + numerofactura + "'";
        //se ejecuta la consulta.
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return res;
    }

    /**
     * Metodo privado para validar datos
     */
    public boolean valida_datosf(String numerofactura, String nombre, String monto, String tipo, String fecha, String pago) {
        if (numerofactura.equals("")) {
            return false;
        } else if (nombre.length() > 0 && monto.length() > 0 && tipo.length() > 0 && pago.length() > 0 && fecha.length() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
}