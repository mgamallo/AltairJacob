package es.mgamallo.altair;

import java.awt.Color;
import java.util.Calendar;
import java.util.Set;

import javax.swing.JOptionPane;

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
		int añoHoy = 1;
		
		int diaHaceUnMes = 1;
		int mesHaceUnMes = 1;
		int añoHaceUnMes = 1;
		
		Calendar calendario = Calendar.getInstance();
		diaHoy = calendario.get(Calendar.DAY_OF_MONTH);
		mesHoy = calendario.get(Calendar.MONTH) + 1;
		añoHoy = calendario.get(Calendar.YEAR);
		
		fechaFin = diaHoy + "/" + mesHoy + "/" + añoHoy;

		calendario.add(Calendar.DAY_OF_MONTH,-  RANGO_DIAS_CONSULTA );
		
		diaHaceUnMes = calendario.get(Calendar.DAY_OF_MONTH);
		mesHaceUnMes = calendario.get(Calendar.MONTH) + 1;
		añoHaceUnMes = calendario.get(Calendar.YEAR);
		
		fechaInicio = diaHaceUnMes + "/" + mesHaceUnMes + "/" + añoHaceUnMes;
		
	//	JavascriptExecutor js = (JavascriptExecutor) driverBandejaXedoc;
		
		/*
		WebElement td = driverBandejaXedoc.findElement(By.id("row"));
		List<WebElement> celdas = td.findElements(By.tagName("td"));
		
		for(int i=0;i<celdas.size()/5;i++){
			System.out.println(celdas.get(i*5 + 3).toString());
		}
		 */
		
		
		Dispatch documento = Dispatch.call(InicioAltairJacob.bandejaXedoc, "document").toDispatch();
		Dispatch tabla = Dispatch.call(documento, "getElementById","row").toDispatch();
		Dispatch celdas = Dispatch.call(tabla, "getElementsByTagName","td").toDispatch();
		int tamaño = Integer.valueOf(Dispatch.get(celdas, "length").toString()) / 5 ;
		
		InicioAltairJacob.listaPdfs = new String[tamaño];
		
		for(int i=0;i<tamaño;i++){
			Dispatch celda = Dispatch.get(celdas, String.valueOf(i*5+2)).toDispatch();
			InicioAltairJacob.listaPdfs[i] = Dispatch.get(celda,"innerHTML").toString();
		}
		
		
		for(int i=0;i<tamaño;i++){
			System.out.println(InicioAltairJacob.listaPdfs[i]);
		}
		
		numPdfsTotales = tamaño;
		InicioAltairJacob.numPdfsTotales = numPdfsTotales;
		
		/*
		String obtenerListaNombres = ""
				+ ""
				+ "var tabla = document.getElementById('row');"
				+ "var celdas = tabla.getElementsByTagName('td');"
				+ "var listaNombres = '';"
				+ "var numCeldas = celdas.length/5;"
				+ "for(var i=0;i<numCeldas;i++){"
					+ "listaNombres = listaNombres + celdas[i*5 + 2].innerHTML + 'ç';"
				+ "}"
				+ "return listaNombres;";
		
	//	Object getArrayNombres = js.executeScript(obtenerListaNombres);
		
	//	listaPdfs = getArrayNombres.toString().split("ç");
				
				
				
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
		
		
		
	//	System.out.println("Número de filas...... " + numeroPdfs);
	*/
		

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

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		HiloMaquetadoInicio maquetado1 = new HiloMaquetadoInicio(InicioAltairJacob.xedoc1, "Xedoc 1", true);
		maquetado1.start();
		
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HiloMaquetadoInicio maquetado2 = new HiloMaquetadoInicio(InicioAltairJacob.xedoc2, "Xedoc 2", true);
		maquetado2.start();
		
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

					+ "cargaContexto();"
				+ "};"
				+ ""
				
				
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
					+ ""
					+ "var numCiclos = 0;"
					+ "var intervalo = setInterval(ciclos,1100);"
					
					+ "function ciclos(){"
						+ "var formResult = document.getElementById('formResult');"
						+ "var tabla = formResult.getElementsByTagName('table');"
						+ "var numeroTablas = tabla.length;"
						+ "if(numeroTablas > 0){"
							+ "clearInterval(intervalo);"
							+ "cambiarContexto(claveEntera);"
							+ "setTimeout(function(){var anclaSeleccionada = celdas[filaSeleccionada*5 +4];"
							+ "anclaSeleccionada = anclaSeleccionada.getElementsByTagName('a');"
							+ "anclaSeleccionada[0].click();},500);"                           ///////// <--------------------------||
							+ ""
						+ "}"
						+ "var error = document.getElementById('advertenciaContexto');"
						+ "if(error != null || error != undefined || numCiclos > 15){"
							+ "clearInterval(intervalo);"
							+ "cambiarContexto(claveEntera);"
						+ "}"
						+ "numCiclos++;"   
					+ "}"
				+ "};"



/*				+ ""
				+ "setTimeout(function(){cambiarContexto(claveEntera);},4000);"			///////// <--------------------------||
				+ "setTimeout(function(){var anclaSeleccionada = celdas[filaSeleccionada*5 +4];"
					+ "anclaSeleccionada = anclaSeleccionada.getElementsByTagName('a');"
					+ "anclaSeleccionada[0].click();},4500);"                           ///////// <--------------------------||
				+ ""
			//	+ "var anclaSeleccionada = celdaSeleccionada.getElementsByTagName('a');"
			//	+ "anclaSeleccionada[0].click();"
*/				+ ""
			
				

				
				+ ""
				+ "var tabla = document.getElementById('row');"
				+ "var celdas = tabla.getElementsByTagName('td');"
				+ "filas = celdas.length / 5;"
				+ ""
			//	+ "alert(filas);"
				+ ""
				
				;
		
		return cadenaComun;
	}


	public static void carganuevoPdf(ActiveXComponent navegador, String nombreXedoc){
		
		boolean hayMasDocumentos = true;
		
		
		Dispatch documento = Dispatch.call(navegador, "document").toDispatch();
		
		System.out.println("Va a clickar en siguiente");
		try {

			
			Variant siguiente = Dispatch.call(documento, "getElementById","siguiente");
			System.out.println("Siguiente " + siguiente.toString());
			if(siguiente.toString().equals("null")){
				System.out.println("No hay mas documentos de " + nombreXedoc);
				hayMasDocumentos = false;
				Dispatch.call(navegador, "navigate","http://intranetchopo.sergas.local/");
			}
			else{
				Dispatch.call(navegador,"navigate","javascript:document.getElementById('siguiente').click();");
			}
			
		//	Dispatch siguiente = Dispatch.call(documento, "getElementById","siguiente").toDispatch();
		//	Dispatch.call(siguiente, "click");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			
			System.out.println("No hay mas documentos.");
			hayMasDocumentos = false;
			Dispatch.call(navegador, "navigate","http://intranetchopo.sergas.local/");

		}
		if(hayMasDocumentos){
		
			System.out.println("2ª iteración..... ************************************************************");
			
		//	System.out.println("Checkea si hay un alert...");
		//	checkAlert(driver);
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			InicioAltairJacob.getReadyState(navegador, 4, 40);
			
			Variant tablaMeritos = Dispatch.call(documento, "getElementById","tablaMeritos");
			System.out.println("Siguiente " + tablaMeritos.toString());
			if(tablaMeritos.toString().equals("null")){
				System.out.println("No cargó la tabla de méritos en " + nombreXedoc);
				hayMasDocumentos = false;
				//Dispatch.call(navegador, "navigate","http://intranetchopo.sergas.local/");
			}
			else{
				
				System.out.println("Encuentra la tabla de meritos 1");
				System.out.println("Va a clickar en siguiente 2");

				Variant siguiente = Dispatch.call(documento, "getElementById","siguiente");
				System.out.println("Siguiente " + siguiente.toString());
				if(siguiente.toString().equals("null")){
					System.out.println("No hay mas documentos de " + nombreXedoc);
					hayMasDocumentos = false;
					Dispatch.call(navegador, "navigate","http://intranetchopo.sergas.local/");
				}
				else{
					Dispatch.call(navegador,"navigate","javascript:document.getElementById('siguiente').click();");
				}
				
			}
			
		}

		//	Cargamos el contexto.....
		
		if(hayMasDocumentos){
			System.out.println("Busca la tabla de meritos 2... ");
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			InicioAltairJacob.getReadyState(navegador, 4, 40);
			
			Variant tablaMeritos = Dispatch.call(documento, "getElementById","tablaMeritos");
			System.out.println("Siguiente " + tablaMeritos.toString());
			if(tablaMeritos.toString().equals("null")){
				System.out.println("No cargó la tabla de méritos en " + nombreXedoc);
				hayMasDocumentos = false;
				//Dispatch.call(navegador, "navigate","http://intranetchopo.sergas.local/");
			}
			else{
				Dispatch labelAtributo = Dispatch.call(documento, "getElementById","labelAtributo").toDispatch();
				Variant mFvariant = Dispatch.get(labelAtributo,"innerHTML");
				String nombreFichero = mFvariant.toString();
				System.out.println(nombreFichero);
				
				String nhc = extraerNHC(nombreFichero);
				
				Dispatch loadContexto = Dispatch.call(documento, "getElementById","loadContexto").toDispatch();
				mFvariant = Dispatch.get(loadContexto,"innerHTML");
				String nombrePacienteContexto = mFvariant.toString();
				
				System.out.println(nhc);
				System.out.println(nombrePacienteContexto);
				
				if(!nombrePacienteContexto.contains(nhc)){
					
					String fechaInicio = "";
					String fechaFin = "";
					
					String fechas[] = getFechasContexto();
					fechaInicio = fechas[0];
					fechaFin = fechas[1];
					
					String cadena = ""
						+ ""
						+ "function cargaContexto(){"
							+ "document.getElementById('contextoMenuSuperior').click();"
							+ "var nhcElement = document.getElementById('{hc}numeroHC');"
							+ "nhcElement.value= '" + nhc + "';"
							+ "var fechaI = document.getElementById('FechaIni');"
							+ "var fechaF = document.getElementById('FechaFin');"
							+ "var periodo = document.getElementById('Periodo');"
							+ "periodo.options[0].selected = true;"
							+ "fechaI.value = '" + fechaInicio + "';"
							+ "fechaF.value = '" + fechaFin + "';"
							//+ "alert('En medio de la carga de contexto');"
							+ "document.getElementById('submitFormContexto').click();"
							+ "var claveEntera = nhcElement.value + '-360340';"
							+ ""
							+ "var numCiclos = 0;"
							+ "var intervalo = setInterval(ciclos,1100);"
							
							+ "function ciclos(){"
								+ "var formResult = document.getElementById('formResult');"
								+ "var tabla = formResult.getElementsByTagName('table');"
								+ "var numeroTablas = tabla.length;"
								+ "if(numeroTablas > 0){"
									+ "clearInterval(intervalo);"
									+ "cambiarContexto(claveEntera);"
								+ "}"
								+ "var error = document.getElementById('advertenciaContexto');"
								+ "if(error != null || error != undefined || numCiclos > 10){"
									+ "clearInterval(intervalo);"
									+ "cambiarContexto(claveEntera);"
								+ "}"
								+ "numCiclos++;"   
							+ "}"
						+ "};"
						+ ""
						+ "cargaContexto();"
						;
						
					
					
					Dispatch.call(navegador,"navigate","javascript:" + cadena);
					
					
					/*
					System.out.println("Espera a encontrar 'advertenciaContexto'... ");
					driver.findElement(By.id("advertenciaContexto"));
					
					String cambiarContexto = nhc + "-360340";
					String codigoJavascript = "cambiarContexto('" + cambiarContexto + "');";
					
					js.executeScript(codigoJavascript);
	*/
					
		/*
					System.out.println("Espera a encontrar 'arbol'... ");
					driver.findElement(By.id("arbol"));
		*/			
					try {
						Thread.sleep(3500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					new MaquetadoXedoc(navegador, nombreXedoc, false, false);
					
					
				}
					// driver.findElement(By.id("contextoMenuSuperior")).click();

			}
		}
		

		

		
	//	checkMeritos(driver);
		/*
		driver.findElement(By.id("siguiente")).click();
		*/
		
/*		driver.findElement(By.id("siguiente")).click();
		
		checkAlert(driver);
*/		
		/*
		String cadena = ""
				+ ""
				+ "document.getElementById('siguiente').click();"
				+ ""
				+ "";
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(cadena);
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		js.executeScript(cadena);
		
		*/
	}
	
	
	private static String extraerNHC(String nombreFichero){
		
		String nhc = "";
		
		String campos[] = nombreFichero.split(" @");
		if(campos.length == 4){
			nhc = campos[1];
		}
		
		return nhc;
	}
	
	
	private static String[] getFechasContexto(){
		
		String fechas[] = new String[2];
		
		String fechaInicio = "";
		String fechaFin = "";

		int diaHoy = 1;
		int mesHoy = 1;
		int añoHoy = 1;
		
		int diaHaceUnMes = 1;
		int mesHaceUnMes = 1;
		int añoHaceUnMes = 1;
		
		Calendar calendario = Calendar.getInstance();
		diaHoy = calendario.get(Calendar.DAY_OF_MONTH);
		mesHoy = calendario.get(Calendar.MONTH) + 1;
		añoHoy = calendario.get(Calendar.YEAR);
		
		fechaFin = diaHoy + "/" + mesHoy + "/" + añoHoy;

		calendario.add(Calendar.DAY_OF_MONTH,- InicioAltairJacob.RANGO_DIAS_CONSULTA);
		
		diaHaceUnMes = calendario.get(Calendar.DAY_OF_MONTH);
		mesHaceUnMes = calendario.get(Calendar.MONTH) + 1;
		añoHaceUnMes = calendario.get(Calendar.YEAR);
		
		fechaInicio = diaHaceUnMes + "/" + mesHaceUnMes + "/" + añoHaceUnMes;
		
		fechas[0] = fechaInicio;
		fechas[1] = fechaFin;
		
		return fechas;

	}
}
