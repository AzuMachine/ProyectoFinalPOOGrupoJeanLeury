package visual;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Altice;

public class DashBoardCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBoardCliente frame = new DashBoardCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DashBoardCliente() {
		
		//Para Guardado
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream altSalidaCliente;
				ObjectOutputStream altWriteCliente;
				
				try {
					altSalidaCliente = new FileOutputStream("Altice.dat");
					altWriteCliente = new ObjectOutputStream(altSalidaCliente);
					altWriteCliente.writeObject(Altice.getInstance());
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				}catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	}

}
