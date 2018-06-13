/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.ProductoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modeloProducto;
import vista.interfaz;

/**
 *
 * @author pedro
 */
public class controladorProducto implements ActionListener, MouseListener {

    /**
     * instancia a nuestra interfaz de usuario
     */
    interfaz vista;
    /**
     * instancia a nuestro modelo
     */
    modeloProducto modeloProducto = new modeloProducto();
    ProductoDAO ProductoDAO = new ProductoDAO();

    public enum AccionMVC {
        AGREGAR_PRODUCTO,
        MODIFICAR_PRODUCTO,
        ELIMINAR_PRODUCTO

    }

    public controladorProducto(interfaz vista) {
        this.vista = vista;



    }

    public void iniciarP() {
        //declara una acción y añade un escucha al evento producido por el componente         
        this.vista.MODIFICAR_PRODUCTO.setActionCommand("MODIFICAR_PRODUCTO");
        this.vista.MODIFICAR_PRODUCTO.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.AGREGAR_PRODUCTO.setActionCommand("AGREGAR_PRODUCTO");
        this.vista.AGREGAR_PRODUCTO.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.ELIMINAR_PRODUCTO.setActionCommand("ELIMINAR_PRODUCTO");
        this.vista.ELIMINAR_PRODUCTO.addActionListener(this);
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.tabla_producto.addMouseListener(this);
        this.vista.tabla_producto.setModel(new DefaultTableModel());
        
        this.vista.tabla_producto.setModel(this.modeloProducto.getTablaProducto());
 
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
            case MODIFICAR_PRODUCTO:
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vista.tabla_producto.setModel( this.modeloProducto.getTablaProducto() );
                if ( this.ProductoDAO.ModificarFactura(
                        this.vista.id_producto.getText(),
                        this.vista.nombre.getText() ,
                        this.vista.precio.getText(),
                        this.vista.cantidad.getText() ) )
                {
                    this.vista.tabla_producto.setModel( this.modeloProducto.getTablaProducto() );
                    JOptionPane.showMessageDialog(vista,"Exito:registro modificado.");
                    this.vista.id_producto.setText("");
                    this.vista.nombre.setText("") ;
                    this.vista.precio.setText("0");
                    this.vista.cantidad.setText("0") ;
                }
                else //ocurrio un error
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                break;
           
            case AGREGAR_PRODUCTO:
                //añade un nuevo registro
                if ( this.ProductoDAO.NuevoProducto(
                        this.vista.id_producto.getText(),
                        this.vista.nombre.getText() ,
                        this.vista.precio.getText(),
                        this.vista.cantidad.getText() ) )
                {
                    this.vista.tabla_producto.setModel( this.modeloProducto.getTablaProducto() );
                    JOptionPane.showMessageDialog(vista,"Exito: Nuevo registro agregado.");
                    this.vista.id_producto.setText("");
                    this.vista.nombre.setText("") ;
                    this.vista.precio.setText("0");
                    this.vista.cantidad.setText("0") ;
                }
                else //ocurrio un error
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                break;
            case ELIMINAR_PRODUCTO:
                if ( this.ProductoDAO.EliminarProducto( this.vista.id_producto.getText() ) )
                {
                    this.vista.tabla_producto.setModel( this.modeloProducto.getTablaProducto() );
                    JOptionPane.showMessageDialog(vista,"Exito: Registro eliminado.");
                    this.vista.id_producto.setText("");
                    this.vista.nombre.setText("") ;
                    this.vista.precio.setText("0");
                    this.vista.cantidad.setText("0") ;
                }
                break;       
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if( e.getButton()== 1)//boton izquierdo/
        {
             int fila = this.vista.tabla_producto.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.id_producto.setText( String.valueOf( this.vista.tabla_producto.getValueAt(fila, 0) ));
                this.vista.nombre.setText( String.valueOf( this.vista.tabla_producto.getValueAt(fila, 1) ));
                this.vista.precio.setText( String.valueOf( this.vista.tabla_producto.getValueAt(fila, 2) ));
                this.vista.cantidad.setText( String.valueOf( this.vista.tabla_producto.getValueAt(fila, 3) ));
             }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

}
