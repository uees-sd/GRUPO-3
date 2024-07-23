import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snake extends JFrame {

    private static final long serialVersionUID = 1L;
    private final int width = 640;
    private final int height = 480;
    private Point snake;
    private Point comida;
    private ArrayList<Point> listaPosiciones = new ArrayList<>();
    private String direccion = "RIGHT";
    private boolean gameOver = false;
    private ImagenSnake imagenSnake;

    public Snake() {
        setTitle("Snake");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        snake = new Point(320, 240); // Ubicamos la serpiente en el centro de la pantalla
        listaPosiciones.add(new Point(snake)); // Agregamos la posición inicial de la serpiente a la lista
        imagenSnake = new ImagenSnake();
        getContentPane().add(imagenSnake);
        addKeyListener(new Teclas());

        startGame(); // Iniciamos el juego

        setVisible(true);
    }

    public void startGame() {
        comida = new Point(200, 100);
        listaPosiciones.clear();
        listaPosiciones.add(new Point(snake)); // Reiniciamos la lista de posiciones con la posición inicial de la serpiente
        direccion = "RIGHT";
        gameOver = false;

        Momento momento = new Momento();
        Thread trid = new Thread(momento);
        trid.start();
    }

    public void generarComida() {
        Random rnd = new Random();

        comida.x = (rnd.nextInt(width)) + 5;
        if ((comida.x % 5) > 0) {
            comida.x = comida.x - (comida.x % 5);
        }

        if (comida.x < 5) {
            comida.x = comida.x + 10;
        }
        if (comida.x > width) {
            comida.x = comida.x - 10;
        }

        comida.y = (rnd.nextInt(height)) + 5;
        if ((comida.y % 5) > 0) {
            comida.y = comida.y - (comida.y % 5);
        }

        if (comida.y > height) {
            comida.y = comida.y - 10;
        }
        if (comida.y < 0) {
            comida.y = comida.y + 10;
        }
    }

    public void actualizar() {
        if ((snake.x > (comida.x - 10) && snake.x < (comida.x + 10)) && (snake.y > (comida.y - 10) && snake.y < (comida.y + 10))) {
            listaPosiciones.add(0, new Point(snake.x, snake.y));
            System.out.println(listaPosiciones.size());
            generarComida();
        }

        imagenSnake.repaint();
    }

    public static void main(String[] args) {
        new Snake();
    }

    public class Teclas implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (!direccion.equals("LEFT")) {
                    direccion = "RIGHT";
                }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (!direccion.equals("RIGHT")) {
                    direccion = "LEFT";
                }
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (!direccion.equals("DOWN")) {
                    direccion = "UP";
                }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (!direccion.equals("UP")) {
                    direccion = "DOWN";
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }

    public class Momento extends Thread {

        private long last = 0;
        private long frequency = 50;

        public Momento() {

        }

		public void run() {
            while (true) {
                if ((System.currentTimeMillis() - last) > frequency) {
                    if (!gameOver) {

                        if (direccion.equals("RIGHT")) {
                            snake.x = snake.x + 10;
                            if (snake.x > width) {
                                snake.x = 0;
                            }
                        } else if (direccion.equals("LEFT")) {
                            snake.x = snake.x - 10;
                            if (snake.x < 0) {
                                snake.x = width - 10;
                            }
                        } else if (direccion.equals("UP")) {
                            snake.y = snake.y - 10;
                            if (snake.y < 0) {
                                snake.y = height;
                            }
                        } else if (direccion.equals("DOWN")) {
                            snake.y = snake.y + 10;
                            if (snake.y > height) {
                                snake.y = 0;
                            }
                        }
                    }
                    actualizar();

                    last = System.currentTimeMillis();
                }
            }
        }
    }

    public class ImagenSnake extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (gameOver) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(0, 0, width, height);
            g.setColor(Color.BLUE);

            for (Point p : listaPosiciones) {
                g.fillRect(p.x, p.y, 10, 10); // Dibujamos cada segmento de la serpiente
            }

            g.setColor(Color.RED);
            g.fillRect(comida.x, comida.y, 10, 10); // Dibujamos la comida

			if (gameOver) {
				g.setFont(new Font("TimesRoman", Font.BOLD, 40));
				g.drawString("GAME OVER", 200, 200);
				g.drawString("SCORE " + (listaPosiciones.size() - 1), 200, 240);
			
				g.setFont(new Font("TimesRoman", Font.BOLD, 20));
				g.drawString("N to Start New Game", 100, 320);
				g.drawString("ESC to Exit", 100, 340);
			}
			