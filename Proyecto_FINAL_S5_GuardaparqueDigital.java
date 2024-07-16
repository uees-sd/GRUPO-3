import javax.swing.*;
import java.awt.*;

public class Proyecto_FINAL_S5_GuardaparqueDigital extends JFrame {

    public Proyecto_FINAL_S5_GuardaparqueDigital() {
        super("Juego Básico - Bosque con Niños");

        // Configuración de la ventana
        setSize(800, 600);  // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Cierre al salir
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        setResizable(false);  // Evitar que se pueda redimensionar la ventana

        // Panel principal para el juego
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarEscena(g);  // Método para dibujar la escena del juego
            }
        };
        panel.setBackground(new Color(135, 206, 250));  // Color de fondo del cielo (azul claro)
        getContentPane().add(panel);  // Agregar el panel a la ventana

        setVisible(true);  // Hacer visible la ventana
    }

    // Método para dibujar la escena del juego
    private void dibujarEscena(Graphics g) {
        // Dibujar el suelo (pasto)
        g.setColor(new Color(34, 139, 34));  // Color verde para el pasto
        g.fillRect(0, 400, 800, 200);  // Rectángulo para el pasto

        // Dibujar el sol
        g.setColor(Color.yellow);
        g.fillOval(700, 50, 80, 80);  // Círculo amarillo para el sol

        // Dibujar los árboles
        g.setColor(new Color(139, 69, 19));  // Color marrón para los troncos
        g.fillRect(100, 300, 20, 100);  // Tronco del árbol 1
        g.fillRect(600, 320, 20, 80);   // Tronco del árbol 2

        g.setColor(new Color(0, 128, 0));  // Color verde para las hojas
        g.fillOval(70, 250, 80, 80);   // Hojas del árbol 1
        g.fillOval(570, 270, 80, 80);  // Hojas del árbol 2

        // Dibujar los niños
        g.setColor(Color.BLUE);  // Color azul para los niños
        g.fillRect(200, 350, 30, 60);   // Cuerpo del niño 1
        g.fillRect(500, 370, 30, 60);   // Cuerpo del niño 2

        g.setColor(Color.BLACK);  // Color negro para la cabeza y piernas
        g.fillOval(200, 320, 30, 30);  // Cabeza del niño 1
        g.fillRect(210, 410, 10, 40);  // Piernas del niño 1

        g.fillOval(500, 340, 30, 30);  // Cabeza del niño 2
        g.fillRect(510, 430, 10, 40);  // Piernas del niño 2
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Proyecto_FINAL_S5_GuardaparqueDigital();
            }
        });
    }
}
