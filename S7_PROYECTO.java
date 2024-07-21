import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class S7_PROYECTO extends JFrame {

    private static final int PUERTO = 5000;
    private static ServerSocket servidor;
    private static Socket cliente;

    public S7_PROYECTO() {
        super("Juego Básico - Bosque con Niños");

        // Configuración de la ventana (igual que antes)

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarEscena(g);
            }
        };
        panel.setBackground(new Color(135, 206, 250));
        getContentPane().add(panel);

        setVisible(true);

        // Iniciar el servidor en un hilo aparte
        iniciarServidor();
    }

    private void iniciarServidor() {
        new Thread(() -> {
            try {
                servidor = new ServerSocket(PUERTO);
                System.out.println("Servidor iniciado. Esperando clientes...");

                cliente = servidor.accept();
                System.out.println("Cliente conectado desde " + cliente.getInetAddress());

                // Aquí podrías implementar la lógica para comunicarte con el cliente
                // Por ejemplo, enviar y recibir datos usando InputStream y OutputStream

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void dibujarEscena(Graphics g) {
        // Código de dibujo de la escena (igual que antes)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new S7_PROYECTO();
            }
        });
    }
}
