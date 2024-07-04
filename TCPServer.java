import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {
    private static List<Integer> sharedList = new ArrayList<>();

    public static void main(String[] args) {
        int port = 12345;
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en el puerto " + port + "...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress());
                
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    try {
                        int clientData = Integer.parseInt(inputLine.trim());

                        // Agrega el dato proporcionado por el cliente a la lista compartida
                        synchronized (sharedList) {
                            sharedList.add(clientData);
                        }

                        // Envía la lista actualizada de vuelta al cliente
                        out.println(sharedList);
                    } catch (NumberFormatException e) {
                        out.println("Error: Por favor, envíe un número válido.");
                    }
                }

                in.close();
                out.close();
                clientSocket.close();
                System.out.println("Conexión cerrada con " + clientSocket.getInetAddress().getHostAddress());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
