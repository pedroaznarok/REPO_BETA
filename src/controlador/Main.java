package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import vista.interfaz;
/**
 *
 * @author pedro
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       interfaz interfaz1 = new interfaz();
       controladorProducto contProd = new controladorProducto(interfaz1);
       controladorFactura contFact = new controladorFactura(interfaz1);
       
       contFact.iniciar();
       contProd.iniciarP();

    }
    
}
