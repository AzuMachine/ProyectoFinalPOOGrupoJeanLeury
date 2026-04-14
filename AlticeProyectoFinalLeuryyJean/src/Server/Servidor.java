package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String args[]) {
		ServerSocket sfd = null;
		try {
			sfd = new ServerSocket(7000);
			System.out.println("Servidor de Respaldos iniciado en el puerto 7000...");
		}
		catch (IOException ioe) {
			System.out.println("Comunicación rechazada: " + ioe);
			System.exit(1);
		}
		
		while(true) {
			try {
				Socket nsfd = sfd.accept();
				System.out.println("Conexión recibida desde: " + nsfd.getInetAddress());
				Flujo hiloRespaldo = new Flujo(nsfd);
				hiloRespaldo.start(); 
			}
			catch(IOException ioe){
				System.out.println("Error aceptando conexión: " + ioe);
			}
		}
	}
}