package Server;

import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import logico.Altice;

public class Flujo extends Thread {
    
    private Socket conexionCliente;

    public Flujo(Socket conexionRecibida) {
        this.conexionCliente = conexionRecibida;
    }

    @Override
 // Dentro de tu clase Flujo (que extiende de Thread)
    public void run() {
        try (ObjectInputStream entrada = new ObjectInputStream(conexionCliente.getInputStream())) {
            
            // Intentar recibir el objeto Altice
            Altice respaldo = (Altice) entrada.readObject();
            
            // Aquí guardas el respaldo en el disco del servidor
            FileOutputStream fileOut = new FileOutputStream("Respaldo_Servidor.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(respaldo);
            out.close();
            
            System.out.println("Respaldo procesado y guardado con éxito.");
            
        } catch (EOFException e) {
            // Esto es normal cuando el cliente cierra el socket rápido
            System.out.println("Transmisión finalizada: El cliente cerró la conexión.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en el flujo de datos: " + e.getMessage());
        } finally {
            try {
                if (conexionCliente != null) conexionCliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}