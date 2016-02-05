package es.mgamallo.altair;

import java.awt.Color;
import java.util.Calendar;
import java.util.Set;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class GestionXedoc {

	public static int RANGO_DIAS_CONSULTA = 100;
	
	public static int numPdfsTotales = 0;
	public static String listaPdfs[];
	
	
	public static void capturaNavegadorXedoc1(){
        
		int iCount = InicioAltairJacob.oWindows.getProperty("Count").getInt();
        System.out.println("iCount: " + iCount);        
		
        for (int i=iCount-1,j= 1; i >iCount-4 ; i--,j++) {
            ActiveXComponent oWindow = InicioAltairJacob.oWindows.invokeGetComponent("Item", new Variant(i));     
            String sLocName = oWindow.getProperty("LocationName").getString();
            String sFullName = oWindow.getProperty("FullName").getString();
            String sUrl =  oWindow.getProperty("LocationURL").getString();
            
            boolean isIE = sFullName.toLowerCase().endsWith("iexplore.exe");
            boolean bVisible = oWindow.getProperty("Visible").getBoolean();
            System.out.println("i: " + i + ", loc: " + sLocName + ", name: " + sFullName + ", isIE: " + isIE + ", vis: " + bVisible);
            
            if(j==1){
            	InicioAltairJacob.xedoc1 = oWindow;
                Dispatch doc1 = Dispatch.call(InicioAltairJacob.xedoc1, "document").getDispatch();
                Dispatch.put(doc1,"title","Xedoc 1");
	       	//	Dispatch.put(InicioAltairJacob.xedoc1,"menubar",false);
	       		Dispatch.put(InicioAltairJacob.xedoc1,"toolbar",false);	
            }

        }
	}
	
	
	public static void insertarCodigoBandeja(String nombreXedoc, int filaInicial1, int filaInicial2){
		
		String fechaInicio = "";
		String fechaFin = "";

		int diaHoy = 1;
		int mesHoy = 1;
		int a�oHoy = 1;
		
		int diaHaceUnMes = 1;
		int mesHaceUnMes = 1;
		int a�oHaceUnMes = 1;
		
		Calendar calendario = Calendar.getInstance();
		diaHoy = calendario.get(Calendar.DAY_OF_MONTH);
		mesHoy = calendario.get(Calendar.MONTH) + 1;
		a�oHoy = calendario.get(Calendar.YEAR);
		
		fechaFin = diaHoy + "/" + mesHoy + "/" + a�oHoy;

		calendario.add(Calendar.DAY_OF_MONTH,-  RANGO_DIAS_CONSULTA );
		
		diaHaceUnMes = calendario.get(Calendar.DAY_OF_MONTH);
		mesHaceUnMes = calendario.get(Calendar.MONTH) + 1;
		a�oHaceUnMes = calendario.get(Calendar.YEAR);
		
		fechaInicio = diaHaceUnMes + "/" + mesHaceUnMes + "/" + a�oHaceUnMes;
		
	//	JavascriptExecutor js = (JavascriptExecutor) driverBandejaXedoc;
		
		/*
		WebElement td = driverBandejaXedoc.findElement(By.id("row"));
		List<WebElement> celdas = td.findElements(By.tagName("td"));
		
		for(int i=0;i<celdas.size()/5;i++){
			System.out.println(celdas.get(i*5 + 3).toString());
		}
		 */
		
		
		String obtenerListaNombres = ""
				+ ""
				+ "var tabla = document.getElementById('row');"
				+ "var celdas = tabla.getElementsByTagName('td');"
				+ "var listaNombres = '';"
				+ "var numCeldas = celdas.length/5;"
				+ "for(var i=0;i<numCeldas;i++){"
					+ "listaNombres = listaNombres + celdas[i*5 + 2].innerHTML + '�';"
				+ "}"
				+ "return listaNombres;";
		
	//	Object getArrayNombres = js.executeScript(obtenerListaNombres);
		
	//	listaPdfs = getArrayNombres.toString().split("�");
				
		String codigoNumeroFilas = ""
				+ ""
				+ "var tabla = document.getElementById('row');"
				+ "var celdas = tabla.getElementsByTagName('td');"
				+ "var filas = celdas.length / 5;"
				+ ""
				+ "return filas"
				+ "";
		
	//	Object getFiles = js.executeScript(codigoNumeroFilas);
	//	int numeroPdfs = Integer.parseInt(getFiles.toString());
		
	//	numPdfsTotales = numeroPdfs;
		
	//	System.out.println("N�mero de filas...... " + numeroPdfs);
	
		

		String cadenaComun1 = getCadenaComun(filaInicial1, fechaInicio, fechaFin);
		String cadenaComun2 = getCadenaComun(filaInicial2, fechaInicio, fechaFin);


		

		
		String cadena_blank = ""		
				+ "var numFila = 1;"
				+ "var sal = 'holaaa';"
				+ ""
				
				
				+ ""
				+ "for(var i=0;i<filas;i++){"
					+ "celdas[i*5 + 2].setAttribute('id',i);"
					+ "celdas[i*5 + 2].setAttribute('onclick','getNHC(this);');"  
					+ "var anclas = celdas[i*5 + 4].getElementsByTagName('a');"
					+ "anclas[0].target = '_blank';"
					+ "numFila++;"
				+ "}"
				+ "celdas[filaInicial*5 + 2].click();"
			//	+ "adios();"
			//	+ "alert(filas);"
				+ "";
		
		String cadena_sin_blank = ""
				+ "var numFila = 1;"
				+ "for(var i=0;i<filas;i++){"
					+ "celdas[i*5 + 2].setAttribute('id',i);"
					+ "celdas[i*5 + 2].setAttribute('onclick','getNHC(this);');"
					+ "var anclas = celdas[i*5 + 4].getElementsByTagName('a');"
					+ "numFila++;"
				+ "}"
				+ "celdas[filaInicial*5 + 2].click();"
			//	+ "alert(filas);"
				+ "";
				
		
		String cadena1 = cadenaComun1 + cadena_blank;
		String cadena2 = cadenaComun2 + cadena_sin_blank;
		
	
		System.out.println("Cadena 1 " + cadena1);

		Dispatch.call(InicioAltairJacob.bandejaXedoc, "navigate","javascript:" + cadena1);
		

		
	//	if(numeroPdfs - filaInicial1 > 1){
			Dispatch.call(InicioAltairJacob.xedoc2, "navigate","javascript:" + cadena2);
	//	}
		
		InicioAltairJacob.getReadyState(InicioAltairJacob.xedoc2, 4, 320);	

		capturaNavegadorXedoc1();
		
		Dispatch documento2 = Dispatch.call(InicioAltairJacob.xedoc2, "document").getDispatch();	
		Dispatch.put(documento2,"title","Xedoc 2");	
/*		
		try {
			Thread.sleep(16000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nombreVentanaBandejaXedoc = driverBandejaXedoc.getWindowHandle();
		
		Set<String> handles = driverBandejaXedoc.getWindowHandles();
		int i = 0;
		for (String windowHandle : handles) {
			System.out.println(i + " " + windowHandle);
			i++;
			if (!windowHandle.equals(nombreVentanaBandejaXedoc)) {
				nombreVentanaXedoc1 = windowHandle;
			}
		}

		driverBandejaXedoc.switchTo().window(nombreVentanaXedoc1);
		
		js = (JavascriptExecutor) driverBandejaXedoc;
		js.executeScript("document.title = 'Xedoc 1';");

		if(numeroPdfs - filaInicial1 > 1){
			js = (JavascriptExecutor) driverXedoc2;
			js.executeScript("document.title = 'Xedoc 2';");
		}
		
*/
	
		new MaquetadoXedoc(InicioAltairJacob.xedoc1, "Xedoc 1", true);
		
	//	new MaquetadoXedoc(InicioAltairJacob.xedoc2, "Xedoc 2", true);
		
/*
		new MaquetadoXedocSelenium(driverBandejaXedoc, "Xedoc 1", true);
		
		System.out.println("numero de pdfs " + numeroPdfs);
		System.out.println("fila inicial" + filaInicial1);
		
		if(numeroPdfs - filaInicial1 > 1){
			new MaquetadoXedocSelenium(driverXedoc2, "Xedoc 2", false);
		}
		
		ventana.xedoc1inicializado = true;
		
		
		ventana.panelMover.setBackground(Color.orange);
		ventana.jBxedoc1.setBackground(Color.green);
		if(numeroPdfs - filaInicial1 > 1){
			ventana.jBxedoc2.setBackground(Color.green);
		}
		ventana.gestionXedoc2();
		ventana.jBbandeja1.doClick();
		
		
		barraAviso = new java.awt.Point(2000, 100);
		recordarAviso = new java.awt.Point(287, 189);
		
		if(numeroPantallas == 1){
			barraAviso = new java.awt.Point(1868, 98);
			recordarAviso = new java.awt.Point(261, 185);
		}
		
	*/	

	}

	
	private static String getCadenaComun(int filaInicial, String fechaInicio, String fechaFin){
		String cadenaComun = ""
				+ ""
				+ "var nhc;"
				+ "var filaSeleccionada;"
				+ "var filaInicial = " + filaInicial + ";"
				+ ""
				+ ""
				+ ""
				+ "function getNHC(nodo){"
					+ "var cadena = nodo.innerHTML;"
					+ "var campos = cadena.split(' @');"
					+ "campos[3] = campos[3].replace('r_f.pdf','');"
					+ "nhc = campos[1];"
					+ "filaSeleccionada = nodo.id;"
					//+ "alert(filaSeleccionada);"
					+ "cargaContexto();"
				+ "};"
				
				
				+ "function cargaContexto(){"
				+ "document.getElementById('contextoMenuSuperior').click();"
				+ "var nhcElement = document.getElementById('{hc}numeroHC');"
				+ "nhcElement.value= nhc;"
				+ "var fechaI = document.getElementById('FechaIni');"
				+ "var fechaF = document.getElementById('FechaFin');"
				+ "var periodo = document.getElementById('Periodo');"
				+ "periodo.options[0].selected = true;"
				+ "fechaI.value = '" + fechaInicio + "';"
				+ "fechaF.value = '" + fechaFin + "';"
				//+ "alert('En medio de la carga de contexto');"
				+ "document.getElementById('submitFormContexto').click();"
				+ "var claveEntera = nhc + '-360340';"
				+ "setTimeout(function(){cambiarContexto(claveEntera);},4000);"
				+ "setTimeout(function(){var anclaSeleccionada = celdas[filaSeleccionada*5 +4];"
					+ "anclaSeleccionada = anclaSeleccionada.getElementsByTagName('a');"
					+ "anclaSeleccionada[0].click();},4500);"
				+ ""
			//	+ "var anclaSeleccionada = celdaSeleccionada.getElementsByTagName('a');"
			//	+ "anclaSeleccionada[0].click();"
				+ ""
			+ "};"
				

				
				+ ""
				+ "var tabla = document.getElementById('row');"
				+ "var celdas = tabla.getElementsByTagName('td');"
				+ "filas = celdas.length / 5;"
				+ ""
			//	+ "alert(filas);"
				+ "";
		
		return cadenaComun;
	}
}