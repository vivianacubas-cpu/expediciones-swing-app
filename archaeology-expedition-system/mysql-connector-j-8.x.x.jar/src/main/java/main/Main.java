package main;

import view.FrmArqueologo;

public class Main {

    public static void main(String[] args) {

        // 🔥 Iniciar interfaz gráfica
        javax.swing.SwingUtilities.invokeLater(() -> {
            new FrmArqueologo();
        });

    }
}