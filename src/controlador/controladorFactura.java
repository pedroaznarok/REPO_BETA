/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.FacturaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modeloFactura;
import vista.interfaz;

/**
 *
 * @author pedro
 */
public class controladorFactura implements ActionListener, MouseListener {

    /**
     * instancia a nuestra interfaz de usuario
     */
    interfaz vista;
    /**
     * instancia a nuestro modelo
     */
    modeloFactura modeloFactura = new modeloFactura();
    FacturaDAO FacturaDAO = new FacturaDAO();
    
    public enum AccionMVC {
        CREAR_FACTURA,
        MODIFICAR_FACTURA,
        ELIMINAR_FACTURA,
        

    }

    public controladorFactura(interfaz vista) {
        this.vista = vista;
        vista.setVisible(true);
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (controladorFactura.AccionMVC.valueOf(e.getActionCommand())) {
            case MODIFICAR_FACTURA:
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vista.tabla_factura.setModel(this.modeloFactura.getTablaFactura());
                if (this.FacturaDAO.ModificarFactura(
                        this.vista.nro_factura.getText(),
                        this.vista.nombre_comprador.getText(),
                        this.vista.monto_factura.getText(),
                        String.valueOf(this.vista.tipo_factura.getSelectedItem()),
                        this.vista.fecha.getText(),
                        String.valueOf(this.vista.metodo_pago.getSelectedItem())
                )) {
                    this.vista.tabla_factura.setModel(this.modeloFactura.getTablaFactura());
                    JOptionPane.showMessageDialog(vista, "Exito: Factura Modificada.");
                    this.vista.nro_factura.setText("");
                    this.vista.nombre_comprador.setText("");
                    this.vista.monto_factura.setText("");
                    this.vista.tipo_factura.setActionCommand("");
                    this.vista.fecha.setText("");
                    this.vista.metodo_pago.setActionCommand("");

                } else //ocurrio un error
                {
                    JOptionPane.showMessageDialog(vista, "Error: Los datos son incorrectos.");
                }
                break;
            case CREAR_FACTURA:
                //añade un nuevo registro
                if (this.FacturaDAO.NuevaFactura(
                        this.vista.nro_factura.getText(),
                        this.vista.nombre_comprador.getText(),
                        this.vista.monto_factura.getText(),
                        String.valueOf(this.vista.tipo_factura.getSelectedItem()),
                        this.vista.fecha.getText(),
                        String.valueOf(this.vista.metodo_pago.getSelectedItem())
                )) {
                    this.vista.tabla_factura.setModel(this.modeloFactura.getTablaFactura());
                    JOptionPane.showMessageDialog(vista, "Exito: Nuevo factura agregada.");
                    this.vista.nro_factura.setText("");
                    this.vista.nombre_comprador.setText("");
                    this.vista.monto_factura.setText("");
                    this.vista.tipo_factura.setActionCommand("");
                    this.vista.fecha.setText("");
                    this.vista.metodo_pago.setActionCommand("");

                } else //ocurrio un error
                {
                    JOptionPane.showMessageDialog(vista, "Error: Los datos son incorrectos.");
                }
                break;
            case ELIMINAR_FACTURA:
                if (this.FacturaDAO.EliminarFactura(this.vista.nro_factura.getText())) {
                    this.vista.tabla_factura.setModel(this.modeloFactura.getTablaFactura());
                    JOptionPane.showMessageDialog(vista, "Exito: Factura Eliminada.");
                    this.vista.nro_factura.setText("");
                    this.vista.nombre_comprador.setText("");
                    this.vista.monto_factura.setText("");
                    this.vista.tipo_factura.setActionCommand("");
                    this.vista.fecha.setText("");
                    this.vista.metodo_pago.setActionCommand("");
                }
                break;
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

    

    /**
     * Inicia el skin y las diferentes variables que se utilizan
     */
    public void iniciar() {
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.CREAR_FACTURA.setActionCommand("CREAR_FACTURA");
        this.vista.CREAR_FACTURA.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.MODIFICAR_FACTURA.setActionCommand("MODIFICAR_FACTURA");
        this.vista.MODIFICAR_FACTURA.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.ELIMINAR_FACTURA.setActionCommand("ELIMINAR_FACTURA");
        this.vista.ELIMINAR_FACTURA.addActionListener(this);

        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.tabla_factura.addMouseListener(this);
        this.vista.tabla_factura.setModel(new DefaultTableModel());

        this.vista.tabla_factura.setModel(this.modeloFactura.getTablaFactura());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1)//boton izquierdo
        {
            int fila = this.vista.tabla_factura.rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vista.nro_factura.setText(String.valueOf(this.vista.tabla_factura.getValueAt(fila, 0)));
                this.vista.nombre_comprador.setText(String.valueOf(this.vista.tabla_factura.getValueAt(fila, 1)));
                this.vista.monto_factura.setText(String.valueOf(this.vista.tabla_factura.getValueAt(fila, 2)));
                this.vista.tipo_factura.setActionCommand(String.valueOf(this.vista.tabla_factura.getValueAt(fila, 3)));
                this.vista.fecha.setText(String.valueOf(this.vista.tabla_factura.getValueAt(fila, 4)));
                this.vista.monto_factura.setActionCommand(String.valueOf(this.vista.tabla_factura.getValueAt(fila, 5)));

            }
        }
    }
}