/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.losinstanciados.pfinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import db.Mysql;
import java.sql.*;
import java.util.Random;

/**
 *
 * @author Mariana Villegas
 */
public class altaDeClientes extends JFrame implements ActionListener {

    protected final JButton btnAceptar, btnCancelar, btnLimpiar, btngenID;
    protected final JTextField txtNombre, txtEmail, txtTelefono, txtIdtarjeta;
    protected final JFrame v = new JFrame("ALTA DE CLIENTES");

    Connection conn;
    Statement sent;

    public altaDeClientes() {
        super("Alta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setSize(600, 250);  //Establecemos las dimensiones del formulario (ancho x alto)
        v.setLocation(440, 100); //Establecemos la ubicación en pantalla (x,y)

        conn = Mysql.getConnection();
       

        //Paso 1. Vamos a crear etiquetas
        JLabel lblNombre = new JLabel("Nombre: ");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblTelefono = new JLabel("Telefono:");
        JLabel lblIdtarjeta = new JLabel("ID Tarjeta:");

        //Paso 2. Vamos a crear campos de texto
        txtNombre = new JTextField(20);
        txtEmail = new JTextField(20);
        txtTelefono = new JTextField(10);
        txtIdtarjeta = new JTextField(19);

        //Paso 3. Vamos a crear un botón.
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        btnLimpiar = new JButton("Limpiar");
        btngenID = new JButton("Generar ID");

        //Paso 4. Vamos a crear el contenedor.
        JPanel pnlContenido = new JPanel(null); //Gestor nulo, util al usar setBounds

        //Paso 5. Ubicamos los elementos en el contenedor
        lblNombre.setBounds(20, 30, 70, 10); //x,y, ancho y alto
        lblNombre.setFont(new Font("Arial", 1, 14));
        lblNombre.setForeground(new Color(25, 85, 255));
        txtNombre.setBounds(95, 30, 300, 25);
        txtNombre.setFont(new Font("Arial", 1, 14));

        lblEmail.setBounds(20, 65, 70, 10);
        lblEmail.setFont(new Font("Arial", 1, 14));
        txtEmail.setBounds(95, 65, 300, 25);
        txtEmail.setFont(new Font("Arial", 1, 14));

        lblTelefono.setBounds(20, 100, 70, 10);
        lblTelefono.setFont(new Font("Arial", 1, 14));
        txtTelefono.setBounds(95, 100, 300, 25);
        txtTelefono.setFont(new Font("Arial", 1, 14));

        lblIdtarjeta.setBounds(20, 135, 70, 10);
        lblIdtarjeta.setFont(new Font("Arial", 1, 14));
        txtIdtarjeta.setBounds(95, 135, 300, 25);
        txtIdtarjeta.setFont(new Font("Arial", 1, 14));

        btnAceptar.setBounds(90, 175, 80, 25);
        btnCancelar.setBounds(180, 175, 100, 25);
        btnLimpiar.setBounds(290, 175, 120, 25);
        btngenID.setBounds(420, 175, 140, 25);

        //Paso 6. Agremos los componentes al contenedor
        this.setContentPane(pnlContenido);
        this.getContentPane().add(lblNombre);
        this.getContentPane().add(txtNombre);
        this.getContentPane().add(lblEmail);
        this.getContentPane().add(txtEmail);
        this.getContentPane().add(lblTelefono);
        this.getContentPane().add(txtTelefono);
        this.getContentPane().add(lblIdtarjeta);
        this.getContentPane().add(txtIdtarjeta);
        this.getContentPane().add(btnAceptar);
        this.getContentPane().add(btnCancelar);
        this.getContentPane().add(btnLimpiar);
        this.getContentPane().add(btngenID);

        //Paso 7. Asociamos el contenedor a la ventana
        v.setContentPane(pnlContenido);

        //Paso 8. Hacemos visible la ventana
        v.setVisible(true);

        //Paso 9. Escucha de eventos.
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnLimpiar.addActionListener(this);
        btngenID.addActionListener(this);

    }

    public void salir() {
        v.setVisible(false);
       
    }

    public void insertarDatos() {

        if (!txtNombre.getText().matches("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}")) {
            JOptionPane.showMessageDialog(null, "Sólo se adminten letras en el campo de 'Nombre'");
            txtNombre.setText("");
        }
        if (!txtEmail.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            JOptionPane.showMessageDialog(null, "El e-mail debe tener una '@'");
            txtEmail.setText("");
        }
        if (!txtTelefono.getText().matches("\\d+") || txtTelefono.getText().length() != 10) {
            JOptionPane.showMessageDialog(null, "Sólo se adminten números en el campo de 'Teléfono' y se requieren 10 dígitos, asegurese de incluir la lada");
            txtTelefono.setText("");
        }
        if (!txtIdtarjeta.getText().matches("\\d+") || txtIdtarjeta.getText().length() != 9) {
            JOptionPane.showMessageDialog(null, "Ingrese los 11 números de su ID");
            txtIdtarjeta.setText("");
        }
        if ((txtNombre.getText().isEmpty()) || (txtTelefono.getText().isEmpty()) || (txtIdtarjeta.getText().isEmpty()) || (txtEmail.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben de estar llenos");
        } else {
            try {
                // el query que ejecutaremos lo guardaremos en una variable tipo string, el query tiene el nombre de las columnas como estan en la base de datos y en los valores ponemos ? porque aun no estan especificados
                String sql = "insert into clientes(nombre, email, telefono, id_tarjeta)"
                        + "values(?,?,?,?)";
                //Utilizamos PreparedStatement en vez de Statement para una query que ya ha sido compilada y hacemos la conexion, en este caso la variable es sql y quedara guardada en el statement ps
                PreparedStatement ps = conn.prepareCall(sql);
                // el primer parametro que recibira ps.setString sera el numero de columna y el segundo sera la obtencion del valor que guardaron nuestras variables escritas en el codigo
                ps.setString(1, txtNombre.getText());
                ps.setString(2, txtEmail.getText());
                ps.setString(3, txtTelefono.getText());
                ps.setString(4, txtIdtarjeta.getText());
                //ejecutaremos el statment ps y lo guardaremos en un interger llamado n
                int n = ps.executeUpdate();
                // Verificamos que existan resultados y si existen n sera mayor a 0
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Datos Guardados");

                }
                JOptionPane.showMessageDialog(rootPane, txtNombre.getText() + "\n" + txtEmail.getText()
                        + "\n" + txtTelefono.getText() + "\n" + txtIdtarjeta.getText() + "\nSe registró correctamente");

            } catch (Exception el) {
                JOptionPane.showMessageDialog(null, "Error" + el.getMessage());

            }
v.setVisible(false);
        }

    }

//programar eventos
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {

            insertarDatos();

        } else if (e.getSource() == btnCancelar) {
            
           salir();

        } else if (e.getSource() == btnLimpiar) {
            txtNombre.setText("");
            txtEmail.setText("");
            txtTelefono.setText("");
            txtIdtarjeta.setText("");

        } else if (e.getSource() == btngenID) {
            Random r = new Random();
            int ID = r.nextInt((1000000000 - 100000000) + 1) + 1000000;
            txtIdtarjeta.setText(Integer.toString(ID));
        }
    }
}
