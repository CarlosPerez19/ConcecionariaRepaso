package Vehiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Buscar_vehiculo {
    private JTextField placa;
    private JButton buscar;
    public JPanel mainPanel;

    String url = "jdbc:mysql://localhost:3306/concecionario";
    String user = "root";
    String password = "123456";
    String sql = "SELECT * FROM vehiculos WHERE placa = ?";


    public Buscar_vehiculo() {
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (placa.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos.");
                    return;
                } else if (placa.getText().length() != 8) {
                    JOptionPane.showMessageDialog(null, "Ingrese un numero de placa validos");
                    return;
                }

                try {
                    Connection conexion = DriverManager.getConnection(url, user, password);
                    PreparedStatement statement = conexion.prepareStatement(sql);
                    statement.setString(1, placa.getText());
                    ResultSet rs = statement.executeQuery();

                    if (rs.next()) {
                        String placa = rs.getString("placa");
                        String marca = rs.getString("marca");
                        Double cilindraje = rs.getDouble("cilindraje");
                        String combustible = rs.getString("combustible");
                        String color = rs.getString("color");
                        String propietario = rs.getString("propietario");

                        JOptionPane.showMessageDialog(null, "Vehiculo Encontrado \n" + "Placa: " + placa + "\n" + "Marca: " + marca + "\n" + "Cilindraje: " + cilindraje + "\n" + "Combustible: " +combustible + "\n" + "Color: " + color + "\n" + "Propietario: " + propietario);
                    } else {
                        JOptionPane.showMessageDialog(null, "Vehiculo no registrado");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
