package es.mgamallo.altair;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.rmi.MarshalledObject;
import java.sql.Time;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseListener;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class CapturaRatonYTeclado implements NativeKeyListener,
		NativeMouseInputListener {
	

	protected static final String LS = System.getProperty("line.separator");
	
	public static boolean barraEspaciadoraOn = true;
	
	int teclaActual = 0;
	int teclaAnterior = 0;
	
	public CapturaRatonYTeclado() {
		// TODO Auto-generated constructor stub

		 GlobalScreen.getInstance().addNativeKeyListener(this);
         GlobalScreen.getInstance().addNativeMouseListener(this);
         GlobalScreen.getInstance().addNativeMouseMotionListener(this);
         
         System.out.println("Hola captura");
         
         try {
			GlobalScreen.registerNativeHook();
			
			System.out.println("Capturando");
			
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * try { GlobalScreen.registerNativeHook(); } catch (NativeHookException
		 * e) { // TODO Auto-generated catch block
		 * System.err.println("There was a problem registering the native hook."
		 * ); System.err.println(e.getMessage()); //e.printStackTrace(); }
		 */
	}

	// Métodos de ratón
	// **********************************************************

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == 3){
			try {
				Robot robot  = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousepressed");
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousereleased");
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousedragged");
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousemoved");
	}

	// Métodos de teclado
	// *************************************************************

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		 System.out.println("NativeKeyPressed " + e.getKeyCode());
		 System.out.println("Tecla ... " + ((char) e.getKeyCode()));

		 	if(e.getKeyCode() == 129){   	// ç
		 		if(InicioAltairJacob.xedoc1Activo){

		 			
		 		//	WebElement enviar = InicioAltairJacob.driverBandejaXedoc.findElement(By.id("submitFormFirmar")); 
		 		//	enviar.click();
		 			
		 		}
		 		else{
		 			
		 		//	WebElement enviar = InicioAltairJacob.driverBandejaXedoc.findElement(By.id("submitFormFirmar")); 
		 		//	enviar.click();
		 			

		 		}
		 	}
		 
		 /*
		 	if(e.getKeyCode() == 92){   	// º
		 		if(InicioAltairJacob.xedoc1Activo){
		 			
		 			WebDriverWait wait = new WebDriverWait(InicioAltairJacob.driverBandejaXedoc, 1);
		 			try{
		 			    wait.until(ExpectedConditions.alertIsPresent());
		 			    Alert alert = InicioAltairJacob.driverBandejaXedoc.switchTo().alert();
		 			    alert.accept();
		 			}
		 			catch(Exception ev){
		 				System.out.println("No hay alerta.");
		 				ev.printStackTrace();
		 			}
		 		}
		 		else{
		 			
		 			WebDriverWait wait = new WebDriverWait(InicioAltairJacob.driverXedoc2, 0);
		 			try{
		 			    wait.until(ExpectedConditions.alertIsPresent());
		 			    Alert alert = InicioAltairJacob.driverXedoc2.switchTo().alert();
		 			    alert.accept();
		 			}
		 			catch(Exception ev){
		 				System.out.println("No hay alerta.");
		 				ev.printStackTrace();
		 			}

		 		}
		 		
		 	}
		*/
		 	
		 	
			if(e.getKeyCode() == 106){		// *
				
				if(InicioAltairJacob.xedoc1Activo){
			//		InicioAltairJacob.ventana.jBxedoc1.setBackground(Color.green);
			//		InicioAltairJacob.ventana.gestionXedoc1();
			//		InicioAltairJacob.ventana.jBxedoc2.setBackground(Color.gray);
			//		InicioAltairJacob.ventana.gestionXedoc2();
					
					/*
					Robot robot;
					try {
						robot = new Robot();
						
						Point p = MouseInfo.getPointerInfo().getLocation();
																
						robot.delay(300);
						robot.mouseMove(1600, 200);
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						robot.delay(200);
						robot.mouseMove(p.x, p.y);
						
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					*/
					
			//		habilitarJava();
					
					System.out.println("Xedoc1Activo... ");

					boolean masDocumentos = true;
				
					Dispatch documento = Dispatch.call(InicioAltairJacob.xedoc2, "document").toDispatch();
					Variant masElementos = Dispatch.call(documento, "getElementById","submitFormFirmar");
					System.out.println("Nodo cex " + masElementos.toString());
					if(masElementos.toString().equals("null")){
						System.out.println("No hay mas documentos de Xedoc 2.");
						masDocumentos = false;
					}
					
					
					System.out.println("Empezamos a cargar xedoc 1");
					InicioAltairJacob.xedoc1Activo = false;
					GestionXedoc.carganuevoPdf(InicioAltairJacob.xedoc1, "Xedoc 1");
					
					/*
					if(masDocumentos){
						
						Dispatch submitFormFirmar = Dispatch.call(documento, "getElementById","submitFormFirmar").toDispatch();
						Dispatch.call(submitFormFirmar,"focus");
						System.out.println("Ponemos el foco en Xedoc 2");
						
						Robot robot;
						try {
							robot = new Robot();
							
							Point p = MouseInfo.getPointerInfo().getLocation();
																	
							robot.delay(300);
							robot.mouseMove(1600, 900);
							robot.mousePress(InputEvent.BUTTON1_MASK);
							robot.mouseRelease(InputEvent.BUTTON1_MASK);
							robot.delay(200);
							robot.mouseMove(p.x, p.y);
							
						} catch (AWTException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					*/
					

					
				}
				else{
			//		InicioAltairJacob.ventana.jBxedoc2.setBackground(Color.green);
			//		InicioAltairJacob.ventana.gestionXedoc2();
			//		InicioAltairJacob.ventana.jBxedoc1.setBackground(Color.gray);
			//		InicioAltairJacob.ventana.gestionXedoc1();
					
			/*		
					
					Robot robot;
					try {
						robot = new Robot();
						
						Point p = MouseInfo.getPointerInfo().getLocation();
																
						robot.delay(300);
						robot.mouseMove(1600, 200);
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						robot.delay(200);
						robot.mouseMove(p.x, p.y);
						
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				*/	
					
					System.out.println("Xedoc1Activo false... ");
					
					boolean masDocumentos = true;
					
					Dispatch documento = Dispatch.call(InicioAltairJacob.xedoc1, "document").toDispatch();
					Variant masElementos = Dispatch.call(documento, "getElementById","submitFormFirmar");
					System.out.println("Nodo cex " + masElementos.toString());
					if(masElementos.toString().equals("null")){
						System.out.println("No hay mas documentos de Xedoc 1.");
						masDocumentos = false;
					}
										
					
					System.out.println("Empezamos a cargar xedoc 2");
					InicioAltairJacob.xedoc1Activo = true;
					GestionXedoc.carganuevoPdf(InicioAltairJacob.xedoc2, "Xedoc 2");
					
					/*
					if(masDocumentos){
						System.out.println("Ponemos el foco en Xedoc 1");
						Dispatch submitFormFirmar = Dispatch.call(documento, "getElementById","submitFormFirmar").toDispatch();
						Dispatch.call(submitFormFirmar,"focus");
						
						Robot robot;
						try {
							robot = new Robot();
							
							Point p = MouseInfo.getPointerInfo().getLocation();
																	
							robot.delay(300);
							robot.mouseMove(1600, 900);
							robot.mousePress(InputEvent.BUTTON1_MASK);
							robot.mouseRelease(InputEvent.BUTTON1_MASK);
							robot.delay(200);
							robot.mouseMove(p.x, p.y);
							
						} catch (AWTException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					*/
				}
				
				/*
						if(InicioXedoc.antiguo)
							Dispatch.call(GestionJacobXedoc.bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.zoomPdf()   );
						else{
							if(Inicio.xedoc1onTop){

								Dispatch.put(Inicio.documento1.xedoc,"visible","false");
								if(Inicio.xedoc2activo){
									Inicio.ventanaControlXedoc.jBxedoc1.setBackground(Color.DARK_GRAY);
									try {
										Thread.sleep(200);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									Dispatch.put(Inicio.documento2.xedoc,"visible","true");
									Inicio.ventanaControlXedoc.jBxedoc2.setBackground(Color.green);
									Inicio.xedoc1onTop = false;
									
									Robot robot;
									try {
										robot = new Robot();
										
										Point p = MouseInfo.getPointerInfo().getLocation();
																				
										robot.delay(300);
										robot.mouseMove(40, 15);
										robot.mousePress(InputEvent.BUTTON1_MASK);
										robot.mouseRelease(InputEvent.BUTTON1_MASK);
										robot.delay(200);
										robot.mouseMove(p.x, p.y);
										
									} catch (AWTException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									
									GestionJacobXedoc.cargaNuevoPdf(Inicio.documento1.xedoc);
								}
								else{
									GestionJacobXedoc.finXedoc(Inicio.documento1.xedoc,"Xedoc1");
								}
			
							}
							else if(!Inicio.xedoc1onTop && Inicio.xedoc1activo){

								Dispatch.put(Inicio.documento2.xedoc,"visible","false");
								if(Inicio.xedoc2activo){
									Inicio.ventanaControlXedoc.jBxedoc2.setBackground(Color.DARK_GRAY);
									try {
										Thread.sleep(200);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									Dispatch.put(Inicio.documento1.xedoc,"visible","true");
									Inicio.ventanaControlXedoc.jBxedoc1.setBackground(Color.green);
									Inicio.xedoc1onTop = true;
									
									Robot robot;
									try {
										robot = new Robot();
										robot.delay(300);
										robot.mousePress(InputEvent.BUTTON1_MASK);
										robot.mouseRelease(InputEvent.BUTTON1_MASK);
									} catch (AWTException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									
									GestionJacobXedoc.cargaNuevoPdf(Inicio.documento2.xedoc);
								}
								else{
									GestionJacobXedoc.finXedoc(Inicio.documento2.xedoc,"Xedoc2");
								}
							}
							
						} 
						*/
			}

			//  Flecha derecha
			if(e.getKeyCode() == 39){
			}
			
			//  Flecha abajo
			if(e.getKeyCode() == 40){
			}
			
			//  Flecha izquierda
			if(e.getKeyCode() == 37){
			}
			

		
		

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
	//	 System.out.println("NativeKeyReleased " + arg0.getKeyCode());
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
	//	 System.out.println("NativeKeyTyped " + arg0.getKeyCode());
	}

	

	

	
}
