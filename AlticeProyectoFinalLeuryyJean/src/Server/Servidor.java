package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String args[]) {
        // El try-with-resources asegura que el ServerSocket se cierre automáticamente
        try (ServerSocket sfd = new ServerSocket(7001)) {
            System.out.println("Servidor de Respaldos iniciado en el puerto 7001...");
            
            while (true) {
                try {
                    Socket nsfd = sfd.accept();
                    System.out.println("Conexión recibida desde: " + nsfd.getInetAddress());
                    
                    // Se delega la gestión del socket al hilo 'Flujo'
                    Flujo hiloRespaldo = new Flujo(nsfd);
                    hiloRespaldo.start(); 
                } catch (IOException ioe) {
                    System.out.println("Error aceptando conexión: " + ioe.getMessage());
                }
            }
        } catch (IOException ioe) {
            System.out.println("No se pudo iniciar el servidor en el puerto 7001: " + ioe.getMessage());
            System.exit(1);
        }
    }
}