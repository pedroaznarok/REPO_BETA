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
public class ProductoDAO extends database{
    
    /** Registra un nuevo producto */
    public boolean NuevoProducto(String id, String nombre , String precio, String cantidad)
    {
        if( valida_datos(id, nombre, precio, cantidad)  )
        {
            //se reemplaza "," por "."
            precio = precio.replace(",", ".");
            //Se arma la consulta
            String q="INSERT INTO db.producto ( p_id , p_nombre , p_precio, p_cantidad  ) "
                    + "VALUES ( " + id + ",'" + nombre + "', " + precio + "," + cantidad + " ) ";
            //se ejecuta la consulta
         
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        }
        else
         return false;
    }
    public boolean ModificarFactura(String id, String nombre , String precio, String cantidad) {
        if (valida_datos(id, nombre, precio,cantidad)) {
            //se reemplaza "," por "."
            precio = precio.replace(",", ".");
            //Se arma la consulta
            String q = "UPDATE `db`.`producto` SET `p_nombre` = '" + nombre +"', p_precio ="+ precio+", p_cantidad ="+ cantidad+" WHERE `p_id` =" + id +";";
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

    /** Elimina un registro dado su ID  */
    public boolean EliminarProducto( String id )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM db.producto WHERE  p_id='" + id + "'" ;
        //se ejecuta la consulta.
         try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }
    /** Actualiza un registro dado su Id **/

    /** Metodo privado para validar datos */
    private boolean valida_datos(String id, String nombre , String precio, String cantidad)
    {
        if( id.equals("     ") )
            return false;
        else if( nombre.length() > 0 && precio.length()>0 && cantidad.length() >0)
        {
            return true;
        }
        else  return false;
    }
    
}