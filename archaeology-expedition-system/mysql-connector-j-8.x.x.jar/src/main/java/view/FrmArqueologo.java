package view;

import controller.ArqueologoController;
import model.Arqueologo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FrmArqueologo extends JFrame {

    JTextField txtId = new JTextField();
    JTextField txtNombre = new JTextField();
    JTextField txtEspecialidad = new JTextField();

    JButton btnGuardar = new JButton("Guardar");
    JButton btnActualizar = new JButton("Actualizar");
    JButton btnEliminar = new JButton("Eliminar");
    JButton btnListar = new JButton("Listar");

    JTable tabla = new JTable();
    DefaultTableModel modelo = new DefaultTableModel();

    ArqueologoController controller = new ArqueologoController();

    public FrmArqueologo() {

        Color rosaFondo = new Color(255, 230, 240);
        Color rosaBoton = new Color(255, 182, 193);
        Color rosaOscuro = new Color(255, 105, 180);

        setTitle("Sistema de Arqueólogos");
        setSize(650, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(rosaFondo);

        JLabel titulo = new JLabel("GESTIÓN DE ARQUEÓLOGOS");
        titulo.setBounds(180, 10, 400, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(rosaOscuro);
        add(titulo);

        JLabel l1 = new JLabel("ID:");
        JLabel l2 = new JLabel("Nombre:");
        JLabel l3 = new JLabel("Especialidad:");

        l1.setBounds(20, 60, 100, 25);
        l2.setBounds(20, 100, 100, 25);
        l3.setBounds(20, 140, 100, 25);

        txtId.setBounds(120, 60, 150, 25);
        txtNombre.setBounds(120, 100, 150, 25);
        txtEspecialidad.setBounds(120, 140, 150, 25);

        btnGuardar.setBounds(320, 60, 120, 30);
        btnActualizar.setBounds(320, 100, 120, 30);
        btnEliminar.setBounds(320, 140, 120, 30);
        btnListar.setBounds(320, 180, 120, 30);

        JButton[] botones = {btnGuardar, btnActualizar, btnEliminar, btnListar};

        for (JButton b : botones) {
            b.setBackground(rosaBoton);
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Arial", Font.BOLD, 12));
            b.setFocusPainted(false);
        }

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Especialidad");

        tabla.setModel(modelo);
        tabla.setRowHeight(22);

        JScrollPane sp = new JScrollPane(tabla);
        sp.setBounds(20, 240, 590, 200);

        add(l1); add(l2); add(l3);
        add(txtId); add(txtNombre); add(txtEspecialidad);
        add(btnGuardar); add(btnActualizar); add(btnEliminar); add(btnListar);
        add(sp);

        // =========================
        // 🎮 ACCIONES CORREGIDAS
        // =========================

        btnGuardar.addActionListener(e -> {
            controller.guardar(txtNombre.getText(), txtEspecialidad.getText());
            listar();
        });

        btnActualizar.addActionListener(e -> {

            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el ID");
                return;
            }

            controller.actualizar(
                    Integer.parseInt(txtId.getText()),
                    txtNombre.getText(),
                    txtEspecialidad.getText()
            );

            listar();
        });

        btnEliminar.addActionListener(e -> {

            // 🔥 VALIDACIÓN (ESTO TE ARREGLA EL ERROR)
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un ID para eliminar");
                return;
            }

            try {
                int id = Integer.parseInt(txtId.getText());
                controller.eliminar(id);
                listar();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido");
            }
        });

        btnListar.addActionListener(e -> listar());

        setVisible(true);
    }

    // 📋 LISTAR
    private void listar() {
        modelo.setRowCount(0);

        for (Arqueologo a : controller.listar()) {
            modelo.addRow(new Object[]{
                    a.getId(),
                    a.getNombre(),
                    a.getEspecialidad()
            });
        }
    }
}