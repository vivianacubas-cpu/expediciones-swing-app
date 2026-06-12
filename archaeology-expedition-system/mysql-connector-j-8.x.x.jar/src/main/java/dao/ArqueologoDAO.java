package dao;

import config.Conexion;
import model.Arqueologo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArqueologoDAO {

    public void insertar(Arqueologo a) {
        String sql = "INSERT INTO arqueologo(nombre,especialidad) VALUES (?,?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getNombre());
            ps.setString(2, a.getEspecialidad());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error insertar: " + e.getMessage());
        }
    }

    public List<Arqueologo> listar() {
        List<Arqueologo> lista = new ArrayList<>();
        String sql = "SELECT * FROM arqueologo";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Arqueologo(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especialidad")
                ));
            }

        } catch (Exception e) {
            System.out.println("Error listar: " + e.getMessage());
        }

        return lista;
    }

    public void actualizar(Arqueologo a) {
        String sql = "UPDATE arqueologo SET nombre=?, especialidad=? WHERE id=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getNombre());
            ps.setString(2, a.getEspecialidad());
            ps.setInt(3, a.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error actualizar: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM arqueologo WHERE id=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error eliminar: " + e.getMessage());
        }
    }
}