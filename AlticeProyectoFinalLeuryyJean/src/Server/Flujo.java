package Server;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Flujo extends Thread {
    
    private Socket conexionCliente;

    public Flujo(Socket conexionRecibida) {
        this.conexionCliente = conexionRecibida;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream entradaDatos = new ObjectInputStream(conexionCliente.getInputStream());
            
            Object sistemaRespaldado = entradaDatos.readObject();
            
            FileOutputStream archivoSalida = new FileOutputStream("RespaldoEmpresa.dat");
            ObjectOutputStream escritorArchivo = new ObjectOutputStream(archivoSalida);
            
            escritorArchivo.writeObject(sistemaRespaldado);
            
            escritorArchivo.close();
            archivoSalida.close();
            entradaDatos.close();
            conexionCliente.close();
            
            System.out.println("El respaldo se guardó correctamente en el servidor.");
            
        } catch (Exception e) {
            System.out.println("Ocurrió un error al intentar guardar el respaldo.");
            e.printStackTrace(); // o algún mensaje joption o lo que sea
        }
    }
}