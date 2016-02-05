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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;


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
		 		if(InicioSeleniumIexplorer.xedoc1Activo){

		 			
		 			WebElement enviar = InicioSeleniumIexplorer.driverBandejaXedoc.findElement(By.id("submitFormFirmar")); 
		 			enviar.click();
		 			
		 		}
		 		else{
		 			
		 			WebElement enviar = InicioSeleniumIexplorer.driverBandejaXedoc.findElement(By.id("submitFormFirmar")); 
		 			enviar.click();
		 			

		 		}
		 	}
		 
		 
		 	if(e.getKeyCode() == 92){   	// º
		 		if(InicioSeleniumIexplorer.xedoc1Activo){
		 			
		 			WebDriverWait wait = new WebDriverWait(InicioSeleniumIexplorer.driverBandejaXedoc, 1);
		 			try{
		 			    wait.until(ExpectedConditions.alertIsPresent());
		 			    Alert alert = InicioSeleniumIexplorer.driverBandejaXedoc.switchTo().alert();
		 			    alert.accept();
		 			}
		 			catch(Exception ev){
		 				System.out.println("No hay alerta.");
		 				ev.printStackTrace();
		 			}
		 		}
		 		else{
		 			
		 			WebDriverWait wait = new WebDriverWait(InicioSeleniumIexplorer.driverXedoc2, 0);
		 			try{
		 			    wait.until(ExpectedConditions.alertIsPresent());
		 			    Alert alert = InicioSeleniumIexplorer.driverXedoc2.switchTo().alert();
		 			    alert.accept();
		 			}
		 			catch(Exception ev){
		 				System.out.println("No hay alerta.");
		 				ev.printStackTrace();
		 			}

		 		}
		 	}

			if(e.getKeyCode() == 106){		// *
				
				if(InicioSeleniumIexplorer.xedoc1Activo){
					InicioSeleniumIexplorer.ventana.jBxedoc1.setBackground(Color.green);
					InicioSeleniumIexplorer.ventana.gestionXedoc1();
					InicioSeleniumIexplorer.ventana.jBxedoc2.setBackground(Color.gray);
					InicioSeleniumIexplorer.ventana.gestionXedoc2();
					
					/*
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
					*/
					
					habilitarJava();
					
					WebElement aceptar = null;
				
					
					boolean masDocumentos = true;
					
					try {
						aceptar = InicioSeleniumIexplorer.driverXedoc2.findElement(By.id("submitFormFirmar"));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						masDocumentos = false;
						System.out.println("No hay mas documentos de Xedoc 2.");
					}
					
					if(masDocumentos){
						JavascriptExecutor js = (JavascriptExecutor) InicioSeleniumIexplorer.driverXedoc2;
						js.executeScript("arguments[0].focus();",aceptar);
					
					}
					
					
					InicioSeleniumIexplorer.xedoc1Activo = false;
					GestionXedocSelenium.carganuevoPdf(InicioSeleniumIexplorer.driverBandejaXedoc, "Xedoc 1");
					
				}
				else{
					InicioSeleniumIexplorer.ventana.jBxedoc2.setBackground(Color.green);
					InicioSeleniumIexplorer.ventana.gestionXedoc2();
					InicioSeleniumIexplorer.ventana.jBxedoc1.setBackground(Color.gray);
					InicioSeleniumIexplorer.ventana.gestionXedoc1();
					
					
					/*
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
					*/
					
					
					habilitarJava();
					
					
					
					WebElement aceptar = null;
				
					
					boolean masDocumentos = true;
					
					try {
						aceptar = InicioSeleniumIexplorer.driverBandejaXedoc.findElement(By.id("submitFormFirmar"));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						masDocumentos = false;
						System.out.println("No hay mas documentos de Xedoc 1.");
					}
					
					if(masDocumentos){
						JavascriptExecutor js = (JavascriptExecutor) InicioSeleniumIexplorer.driverBandejaXedoc;
						js.executeScript("arguments[0].focus();",aceptar);
					
					}
					

					
					InicioSeleniumIexplorer.xedoc1Activo = true;
					GestionXedocSelenium.carganuevoPdf(InicioSeleniumIexplorer.driverXedoc2, "Xedoc 2");
					
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

	

	
	
	static public void habilitarJava(){
		Robot robot;
		try {
			robot = new Robot();
			
			java.awt.Point p = MouseInfo.getPointerInfo().getLocation();
													
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
		
		java.awt.Point barraAviso;
		java.awt.Point recordarAviso; 
		
		barraAviso = InicioSeleniumIexplorer.barraAviso;
		recordarAviso = InicioSeleniumIexplorer.recordarAviso;
		
		Robot robotPlugin;

			try {
				robotPlugin = new Robot();
				Color color = robotPlugin.getPixelColor(barraAviso.x,barraAviso.y);
				
//	robotPlugin.delay(3000);
				
				System.out.println(Integer.toHexString(color.getRGB()));
				String codigoColor = Integer.toHexString(color.getRGB());
				if(codigoColor.equals("ffd4d0c8") || codigoColor.equals("fff0f0f0")){
					robotPlugin.mouseMove(barraAviso.x, barraAviso.y);
					robotPlugin.mousePress(InputEvent.BUTTON1_MASK);
					robotPlugin.mouseRelease(InputEvent.BUTTON1_MASK);
					
					robotPlugin.delay(500);
					
					robotPlugin.mouseMove(recordarAviso.x, recordarAviso.y);
					robotPlugin.mousePress(InputEvent.BUTTON1_MASK);
					robotPlugin.mouseRelease(InputEvent.BUTTON1_MASK);
					
					robotPlugin.delay(400);
					
					robotPlugin.keyPress(KeyEvent.VK_UP);
					robotPlugin.keyRelease(KeyEvent.VK_UP);
					robotPlugin.delay(100);
					robotPlugin.keyPress(KeyEvent.VK_ENTER);
					robotPlugin.keyRelease(KeyEvent.VK_ENTER);
					robotPlugin.delay(100);
					robotPlugin.keyPress(KeyEvent.VK_ENTER);
					robotPlugin.keyRelease(KeyEvent.VK_ENTER);

					robotPlugin.delay(200);
				}
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	

	
	

	
}
