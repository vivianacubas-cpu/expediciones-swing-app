package controller;

import dao.ArqueologoDAO;
import model.Arqueologo;

import java.util.List;

public class ArqueologoController {

    ArqueologoDAO dao = new ArqueologoDAO();

    public void guardar(String nombre, String especialidad) {
        dao.insertar(new Arqueologo(0, nombre, especialidad));
    }

    public void actualizar(int id, String nombre, String especialidad) {
        dao.actualizar(new Arqueologo(id, nombre, especialidad));
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }

    public List<Arqueologo> listar() {
        return dao.listar();
    }
}