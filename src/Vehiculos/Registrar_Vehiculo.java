package Vehiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registrar_Vehiculo {
    private JTextField placa;
    private JTextField marca;
    private JTextField cilindraje;
    private JTextField combustible;
    private JTextField color;
    private JTextField propietario;
    private JButton registrar;
    private JButton limpiar;
    public JPanel mainPanel;
    private JButton buscar;

    String url = "jdbc:mysql://localhost:3306/concecionario";
    String user = "root";
    String password = "123456";
    String sql = "INSERT INTO vehiculos (placa, marca, cilindraje, combustible, color, propietario) VALUES (?, ?, ?, ?, ?, ?)";


    public Registrar_Vehiculo() {
        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Vehiculo vehiculo = new Vehiculo();

                if (placa.getText().isEmpty() || marca.getText().isEmpty() || cilindraje.getText().isEmpty() || combustible.getText().isEmpty() || color.getText().isEmpty() || propietario.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Los campos deben estar llenos");
                    return;
                } else if (placa.getText().length() != 8) {
                    JOptionPane.showMessageDialog(null, "Ingrese un numero de placa valido");
                    return;
                }

                vehiculo.setPlaca(placa.getText());
                vehiculo.setMarca(marca.getText());
                vehiculo.setCilindraje(cilindraje.getText());
                vehiculo.setCombustible(combustible.getText());
                vehiculo.setColor(color.getText());
                vehiculo.setPropietario(propietario.getText());

                try {
                    Connection conexion = DriverManager.getConnection(url, user, password);
                    PreparedStatement declarar = conexion.prepareStatement(sql);
                    declarar.setString (1, vehiculo.getPlaca());
                    declarar.setString (2, vehiculo.getMarca());
                    declarar.setString (3, vehiculo.getCilindraje());
                    declarar.setString (4, vehiculo.getCombustible());
                    declarar.setString (5, vehiculo.getColor());
                    declarar.setString (6, vehiculo.getPropietario());
                    declarar.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Vehiculo registrado correctamente.");
                    conexion.close();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el vehiculo." + ex.getMessage());
                    ex.printStackTrace();
                }

            }
        });
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placa.setText(null);
                marca.setText(null);
                cilindraje.setText(null);
                combustible.setText(null);
                color.setText(null);
                propietario.setText(null);
            }
        });
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Buscar Vehiculos");
                frame.setContentPane(new Buscar_vehiculo().mainPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
