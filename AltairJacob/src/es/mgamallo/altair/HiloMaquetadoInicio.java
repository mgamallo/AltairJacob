package es.mgamallo.altair;

import java.awt.Toolkit;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class HiloMaquetadoInicio extends Thread {
	
	String nombreXedoc;
	boolean inicializarTanda;
	ActiveXComponent navegador;

	public HiloMaquetadoInicio(ActiveXComponent navegador, String nombreXedoc, Boolean inicializarTanda) {
		// TODO Auto-generated constructor stub
		
		this.navegador = navegador;
		this.nombreXedoc = nombreXedoc;
		this.inicializarTanda = inicializarTanda;
	}
	
	public void run(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				 try {
					System.out.println("Empieza el hilo de " + nombreXedoc); 
					
					new MaquetadoXedoc(navegador, nombreXedoc, inicializarTanda, false, false);
	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					System.out.println("Jacob break al maquetar. Restaurando proximamente.");
					
					// GestionJacob.rescateJacob(nombreIanus);
				}
			}
		});
	}
}
