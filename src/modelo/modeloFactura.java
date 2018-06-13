/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import datos.database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pedro
 */
public class modeloFactura extends database {

    public modeloFactura() {
    }
    /**
     * Obtiene registros de la tabla FACTURA y los devuelve en un
     * DefaultTableModel
     */
    public DefaultTableModel getTablaFactura() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"Numero Factura", "Nombre Comprador", "Monto", "Tipo Factura", "Fecha", "Metodo de Pago"};
        //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
        //para formar la matriz de datos
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as total FROM factura");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        //se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[registros][8];
        try {
            //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM factura");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("f_nro");
                data[i][1] = res.getString("f_nombre");
                data[i][2] = res.getString("f_monto");
                data[i][3] = res.getString("f_tipo");
                data[i][4] = res.getString("f_fecha");
                data[i][5] = res.getString("f_pago");
                i++;
            }
            res.close();
            //se añade la matriz de datos en el DefaultTableModel
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    }    
}
    
