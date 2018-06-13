/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class database {
 /* DATOS PARA LA CONEXION */
  /** base de datos por defecto es test*/
  private String db = "db";
  /** usuario */
  private String user = "root";
  /** contraseña de MySql*/
  private String password = "mysql";
  /** Cadena de conexion */
  private String url = "jdbc:mysql://localhost:3306/"+db;
  /** variable para trabajar con la conexion a la base de datos */
  private Connection conn = null;

   /** Constructor de clase */
   public database(){
      //String url1 = this.url;
       
       try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conn = DriverManager.getConnection( this.url, this.user , this.password ); 
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }catch(ClassNotFoundException e){
         System.err.println( e.getMessage() );
      }
   }


   public Connection getConexion()
   {
    return this.conn;
   }

}