package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String args[]) {
        // 1. Usamos try-with-resources para que el ServerSocket se cierre solo
        try (ServerSocket sfd = new ServerSocket(7001)) {
            System.out.println("Servidor de Respaldos iniciado en el puerto 7001...");
            
            while (true) {
                try {
                    // 2. Aceptamos la conexión
                    Socket nsfd = sfd.accept();
                    System.out.println("Conexión recibida desde: " + nsfd.getInetAddress());
                    
                    // 3. Pasamos el socket al hilo. 
                    // El hilo 'Flujo' debe ser el encargado de cerrar 'nsfd' al terminar.
                    Flujo hiloRespaldo = new Flujo(nsfd);
                    hiloRespaldo.start(); 
                    
                } catch (IOException ioe) {
                    System.out.println("Error aceptando conexión: " + ioe.getMessage());
                }
            }
        } catch (IOException ioe) {
            System.out.println("Comunicación rechazada o puerto ocupado: " + ioe.getMessage());
            System.exit(1);
        }
    }
}