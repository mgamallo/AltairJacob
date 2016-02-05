package es.mgamallo.altair;


import java.awt.Point;
import java.util.List;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class MaquetadoXedoc {

	ActiveXComponent navegador;
	Dispatch documento;
	String nombreXedoc;
	
	String colorFondo = "#10324c";
	String colorFondoInterno = "#9db7cc";
	String colorFondoCajas = "RGB(253,247,133)";
	
	String claseInputs = "custom-combobox-input ui-widget "
			+ "ui-widget-content ui-state-default ui-corner-left ui-autocomplete-input";
	
	private int numeroPantallas;
	
	boolean errorDeContexto = false;
	
	public MaquetadoXedoc(ActiveXComponent navegador, String nombreXedoc, boolean inicializarTanda){
		this.navegador = navegador;
		this.nombreXedoc = nombreXedoc;
		this.documento = Dispatch.call(navegador, "document").getDispatch();
		this.numeroPantallas = InicioAltairJacob.numeroPantallas;
	//	this.inicializaTanda = inicializarTanda;
		
		inicializaMaquetado();
		
		// maquetado01();
		dispatchMaquetado01();
	}
	
	
	public void inicializaMaquetado() {

		// Dimension dimensionSize;

		if (InicioAltairJacob.numeroPantallas == 1) {
			// dimensionSize = new Dimension(1919,1172);

			Dispatch.put(navegador, "height", 1172);
			Dispatch.put(navegador, "width", 1919);
			Dispatch.put(navegador, "top", 0);
			Dispatch.put(navegador, "left", 0);

		} 
		else {
			// dimensionSize = new Dimension(2050,1251);
			Dispatch.put(navegador, "height", 1251);
			Dispatch.put(navegador, "width", 2050);
			Dispatch.put(navegador, "top", 0);
			Dispatch.put(navegador, "left", 0);

		}

	}
	
	
	public void dispatchMaquetado01(){
		
		//  Fondo y encabezado
		Dispatch fondoPagina = Dispatch.call(documento,"getElementById","page").getDispatch();
		Dispatch fondoPaginaEstilo = Dispatch.call(fondoPagina, "getAttribute","style").getDispatch();
		Dispatch.put(fondoPaginaEstilo,"background",colorFondo);

		Dispatch botonSalir = Dispatch.call(documento, "getElementById","botonSalir").toDispatch();
		Dispatch.put(botonSalir, "innerHTML",nombreXedoc);
		Dispatch botonSalirEstilo = Dispatch.get(botonSalir, "style").toDispatch();
		Dispatch.put(botonSalirEstilo, "font","bold 28px arial, sans-serif");
		Dispatch.put(botonSalirEstilo, "color","red");
		
		Dispatch entornoLogin = Dispatch.call(documento, "getElementById","entornoLogin").toDispatch();
		Dispatch entornoLoginStilo = Dispatch.get(entornoLogin, "style").toDispatch();
		Dispatch.put(entornoLoginStilo, "display","none");
		
		Dispatch branding = Dispatch.call(documento, "getElementById","branding").toDispatch();
		Dispatch brandingEstilo = Dispatch.get(branding, "style").toDispatch();
		Dispatch.put(brandingEstilo, "display","none");
		
		Dispatch header = Dispatch.call(documento, "getElementById","header").toDispatch();
		Dispatch headerStilo = Dispatch.get(header, "style").toDispatch();
		Dispatch.put(headerStilo, "height","0px");
		
		// Columna izquierda
		Dispatch columnaI = Dispatch.call(documento, "getElementById","columnaIzquierdaEdicion").toDispatch();
		Dispatch columnaIEstilo = Dispatch.get(columnaI, "style").toDispatch();
		Dispatch.put(columnaIEstilo, "height","1200px");
		Dispatch.put(columnaIEstilo, "width","800px");
		
		Dispatch completePreview = Dispatch.call(documento, "getElementById","completePreview").toDispatch();
		Dispatch completePreviewEstilo = Dispatch.get(completePreview, "style").toDispatch();
		Dispatch.put(completePreviewEstilo, "height","1200px");
		Dispatch.put(completePreviewEstilo, "width","800px");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		//  Columna Derecha
		
		String ancho = "";
		String altoArbol = "1000px";
		String altoTablameritos = "1050px";
		
		if(numeroPantallas == 1){
			ancho = "800px";
			altoArbol = "830px";
			altoTablameritos = "850px";
		}
		else{
			ancho = "960px";
		}
		
		
		Dispatch divOcultarAsociados = Dispatch.call(documento, "getElementById","divOcultarAsociados").toDispatch();
		Dispatch divOcultarAsociadosEstilo = Dispatch.get(divOcultarAsociados, "style").toDispatch();
		Dispatch.put(divOcultarAsociadosEstilo, "display","none");
		
		Dispatch columnaD = Dispatch.call(documento, "getElementById","columnaDerechaEdicion").toDispatch();
		Dispatch columnaDEstilo = Dispatch.get(columnaD, "style").toDispatch();
		Dispatch.put(columnaDEstilo, "width","1000px");
		Dispatch.put(columnaDEstilo, "marginLeft",ancho);
		
		Dispatch tablaAtributos = Dispatch.call(documento, "getElementById","tablaAtributos").toDispatch();
		Dispatch tablaAtributosEstilo = Dispatch.get(tablaAtributos, "style").toDispatch();
		Dispatch.put(tablaAtributosEstilo, "border","none");
		Dispatch.put(tablaAtributosEstilo, "background",colorFondoInterno);
		Dispatch.put(tablaAtributosEstilo, "color","#000000");
		Dispatch.put(tablaAtributosEstilo, "minWidth","520px");
		Dispatch.put(tablaAtributosEstilo, "width","520px");
		
		Dispatch edicionForm = Dispatch.call(documento, "getElementById","edicionForm").toDispatch();
		Dispatch edicionFormEstilo = Dispatch.get(edicionForm, "style").toDispatch();
		Dispatch.put(edicionFormEstilo, "width","1000px");
		
		Dispatch tablaElementosAjax = Dispatch.call(documento, "getElementById","tablaElementosAjax").toDispatch();
		Dispatch tablaElementosAjaxEstilo = Dispatch.get(tablaElementosAjax, "style").toDispatch();
		Dispatch.put(tablaElementosAjaxEstilo, "marginTop","-70px");
		Dispatch.put(tablaElementosAjaxEstilo, "height",altoArbol);
		Dispatch.put(tablaElementosAjaxEstilo, "width","450px");
				
		Dispatch tablaMeritos = Dispatch.call(documento, "getElementById","tablaMeritos").toDispatch();
		Dispatch tablaMeritosEstilo = Dispatch.get(tablaMeritos, "style").toDispatch();
		Dispatch.put(tablaMeritosEstilo, "border","none");
		Dispatch.put(tablaMeritosEstilo, "background",colorFondoInterno);
		Dispatch.put(tablaMeritosEstilo, "height",altoTablameritos);
		Dispatch.put(tablaMeritosEstilo, "minWidth","400px");
		
		Dispatch tablaAtributosAjax = Dispatch.call(documento, "getElementById","tablaAtributosAjax").toDispatch();
		Dispatch tablaAtributosAjaxEstilo = Dispatch.get(tablaAtributosAjax, "style").toDispatch();
		Dispatch.put(tablaAtributosAjaxEstilo, "marginLeft","470px");
		Dispatch.put(tablaAtributosAjaxEstilo, "minWidth","500px");
		
		if(InicioAltairJacob.numeroPantallas == 1){
			Dispatch.put(tablaAtributosAjaxEstilo, "marginTop","-759px");
		}
		else{
			Dispatch.put(tablaAtributosAjaxEstilo, "marginTop","-930px");
		}
		
		Dispatch arbol = Dispatch.call(documento, "getElementById","arbol").toDispatch();
		Dispatch arbolEstilo = Dispatch.get(arbol, "style").toDispatch();
		Dispatch.put(arbolEstilo, "background",colorFondoInterno);
		Dispatch.put(arbolEstilo, "height",altoArbol);
		Dispatch.put(arbolEstilo, "width","440px");
		
		Dispatch tablaDocumento = Dispatch.call(documento, "getElementById","tablaDocumento").toDispatch();
		Dispatch tablaDocumentoEstilo = Dispatch.get(tablaDocumento, "style").toDispatch();
		Dispatch.put(tablaDocumentoEstilo, "border","none");
		Dispatch.put(tablaDocumentoEstilo, "background",colorFondoInterno);
		Dispatch.put(tablaDocumentoEstilo, "width","520px");
		Dispatch.put(tablaDocumentoEstilo, "minWidth","520px");
		
		Dispatch colPropDinamica = Dispatch.call(documento, "getElementById","colPropDinamica").toDispatch();
		Dispatch colPropDinamicaEstilo = Dispatch.get(colPropDinamica, "style").toDispatch();
		Dispatch.put(colPropDinamicaEstilo, "display","none");
		
		Dispatch legends = Dispatch.call(documento, "getElementsByTagName","legend").getDispatch();
		 
		 int numeroInputs = Integer.valueOf(Dispatch.get(legends,"length").toString());
		 System.out.println("Número de legends... " + numeroInputs);
		 
		 for(int i=0;i<numeroInputs;i++){
			 Dispatch leyenda = Dispatch.call(legends,String.valueOf(i)).getDispatch();
			 Dispatch leyendaEstilo = Dispatch.get(leyenda, "style").toDispatch();
			 Dispatch.put(leyendaEstilo, "width","400px");
			 Dispatch.put(leyendaEstilo, "paddingTop","40px");
		 }
		
		/* volvemos columna izquierda */ 
		Dispatch previewer = Dispatch.call(documento, "getElementById","previewer").toDispatch();
		Dispatch previewerEstilo = Dispatch.get(previewer, "style").toDispatch();
		Dispatch.put(previewerEstilo, "height","1200px");
		Dispatch.put(previewerEstilo, "width","800px");		 
		/* fin */
		
		Dispatch loadContexto = Dispatch.call(documento, "getElementById","loadContexto").getDispatch();
		Variant nombrePacient = Dispatch.get(loadContexto,"innerHTML");
		String nombrePaciente = nombrePacient.getString();
		
		System.out.println("Nombre del paciente  " + nombrePaciente);
		
		int index = nombrePaciente.indexOf("(");
		if(index != -1){
			nombrePaciente = nombrePaciente.substring(0,index);
		}
		if(nombrePaciente.contains("Contexto Vacío")){
			nombrePaciente = "Contexto Vacío";
			errorDeContexto = true;
		}
		
		System.out.println("Nombre del paciente  " + nombrePaciente);
		
		// Otros
		Dispatch contextoMenuSuperior = Dispatch.call(documento, "getElementById","contextoMenuSuperior").toDispatch();
		Dispatch contextoMenuSuperiorEstilo = Dispatch.get(contextoMenuSuperior, "style").toDispatch();
		Dispatch.put(contextoMenuSuperiorEstilo, "marginRight","400px");
		
		Dispatch.put(loadContexto, "innerHTML",nombrePaciente);
		Dispatch loadContextoEstilo = Dispatch.get(loadContexto, "style").toDispatch();
		Dispatch.put(loadContextoEstilo, "marginLeft","-800px");
		Dispatch.put(loadContextoEstilo, "color","yellow");
		Dispatch.put(loadContextoEstilo, "fontSize","25px");
		Dispatch.put(loadContextoEstilo, "width","800px");
		
		Dispatch comprimirA = Dispatch.call(documento, "getElementById","selectDisplayButtonsTree").toDispatch();
		Dispatch comprimirAEstilo = Dispatch.get(comprimirA, "style").toDispatch();
		Dispatch.put(comprimirAEstilo, "left","1200px");
		Dispatch.put(comprimirAEstilo, "display","none");	
		
		Dispatch textArea = Dispatch.call(documento, "getElementById","{hc}comentario-{hc}docExt").toDispatch();
		Dispatch textAreaEstilo = Dispatch.get(textArea, "style").toDispatch();
		Dispatch.put(textAreaEstilo, "width","475px");
		Dispatch.put(textAreaEstilo, "marginLeft","20px");	
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// Cajas amarillas
		Dispatch cajasAmarillas = Dispatch.call(documento, "querySelectorAll",".custom-combobox-input").getDispatch();
		 
		 int numeroCajas = Integer.valueOf(Dispatch.get(cajasAmarillas,"length").toString());
		 System.out.println("Número de cajas... " + numeroCajas);
		 
		 for(int i=0, conteo = 0;i<numeroCajas;i++){
			 Dispatch caja = Dispatch.call(cajasAmarillas,String.valueOf(i)).getDispatch();
			 Dispatch cajaEstilo = Dispatch.get(caja, "style").toDispatch();
			 Variant v1 = Dispatch.get(cajaEstilo, "backgroundColor");
			 
			 if(v1.toString().contains("255")){
				 if(i != 0){
					 Dispatch.put(cajaEstilo, "backgroundColor",colorFondoCajas);
				 }
			 }
			 
			
		 }
		
		/*
		String cajasAmarillas = ""
				+ ""
				+ "var cajasAmarillas = document.getElementsByClassName('custom-combobox-input');"
				+ "var tam = cajasAmarillas.length;"
				+ "var conteo = 0;"
				+ "for(var i=0;i<tam-2;i++){"
					+ "var comparacion = cajasAmarillas[i].style.backgroundColor.search('255');"
					// + "alert(cajasAmarillas[i].style.backgroundColor);"
					+ "if(comparacion != -1){"
						+ "if(i != 0){"
							+ "cajasAmarillas[i].style.backgroundColor = '" + colorFondoCajas + "';"
							+ "var ide = 'cajaColoreada' + conteo;"
							+ "conteo = conteo + 1;"
							+ "cajasAmarillas[i].setAttribute('id',ide);"						
						+ "}"

						+ "cajasAmarillas[i].style.paddingLeft = '20px';"
						+ "cajasAmarillas[i].style.marginLeft = '20px';"
						+ "cajasAmarillas[i].style.width = '465px';"
						+ "cajasAmarillas[i].style.color = 'red';"
						+ "cajasAmarillas[i].style.font = 'bold 18px arial, sans-serif';"

						+ "if(conteo == 2){"
						+ 	"break;"
						+ "}"
					+ "}"
				+ "}"
				+ "var fecha = document.getElementById('{hc}dataVersion-{hc}docExt');"
				+ "fecha.style.backgroundColor = '" + colorFondoCajas + "';"
				+ "fecha.style.font = 'bold 20px arial, sans-serif';"
				+ ""
				+ "var labelAtributo = document.getElementById('labelAtributo');"
				+ "labelAtributo.style.width = '500px';"
				+ "labelAtributo.style.color = 'green';"
				+ "labelAtributo.style.paddingLeft = '10px';"
				+ ""
				+ "var estiloCajas = document.getElementsByClassName('custom-combobox');"
				+ "var tam = estiloCajas.length;"
				// + "alert(tam);"
				+ "for(var i=0;i<tam-5;i++){"
		//			+ "document.getElementById('{hc}titulo-{hc}docExt').value = tam;"
					+ "estiloCajas[i].style.width = '520px';"
				+ "}"
				+ "";
		*/
		
	}
	
	public void maquetado01(){
		
		String fondoYEncabezado = ""
				+ ""
				+ "var fondoPagina = document.getElementById('page');"
				+ "fondoPagina.style.background = '" + colorFondo + "';" 
				+ ""
				+ "var botonSalir = document.getElementById('botonSalir');"
				+ "botonSalir.innerHTML = '" + nombreXedoc + "';"
				+ "botonSalir.style.font = 'bold 28px arial, sans-serif';"
				+ "botonSalir.style.color = 'red';"
				+ ""
				+ "var entornoLogin = document.getElementById('entornoLogin');"
				+ "entornoLogin.style.display = 'none';"
				+ ""
				+ "var branding = document.getElementById('branding');"
				+ "branding.style.display = 'none';"
				+ ""
				+ "var header = document.getElementById('header');"
				+ "header.style.height = '0px';"
				+ "";
		
		//  Columna Izquierda
		
		
		
		
		String columnaIzquierda = ""
				+ ""
				+ "var columnaI = document.getElementById('columnaIzquierdaEdicion');"
				+ "columnaI.style.height = '1200px';"
				+ "columnaI.style.width = '800px';"
				+ ""
				+ "var completePreview = document.getElementById('completePreview');"
				+ "completePreview.style.height = '1200px';"
				+ "completePreview.style.width = '800px';"
				+ ""
				+ "var previewer = document.getElementById('previewer');"
				+ "if(previewer != null && previewer != undefined){"
					+ "previewer.style.height = '1200px';"
					+ "previewer.style.width = '800px';"
				+ "}"

				+ "";
		
		
		Dispatch.call(navegador, "navigate","javascript:" + fondoYEncabezado 
				+ columnaIzquierda 
				+ "alert('hola 1');");
		
		
		//  Columna Derecha
		
		String ancho = "";
		String altoArbol = "1000px";
		String altoTablameritos = "1050px";
		
		if(numeroPantallas == 1){
			ancho = "800px";
			altoArbol = "830px";
			altoTablameritos = "850px";
		}
		else{
			ancho = "960px";
		}
		
		String columnaDerecha1 = ""
				+ ""
				+ "var divOcultarAsociados = document.getElementById('divOcultarAsociados');"
				+ "divOcultarAsociados.style.display = 'none';"
				+ ""
				+ "var columnaD = document.getElementById('columnaDerechaEdicion');"
				+ "columnaD.style.width = '1000px';"
				+ "columnaD.style.marginLeft = '" + ancho + "';"
				
				
				+ "var tablaAtributos = document.getElementById('tablaAtributos');"
				+ "tablaAtributos.style.border = 'none';"
				+ "tablaAtributos.style.background = '" + colorFondoInterno + "';"
				+ "tablaAtributos.style.color = '#000000';"
				+ "tablaAtributos.style.minWidth = '520px';"
				+ "tablaAtributos.style.width = '520px';"
				+ ""
				+ "var edicionForm = document.getElementById('edicionForm');"
				+ "edicionForm.style.width = '1000px';"
				
				+ "var tablaElementosAjax = document.getElementById('tablaElementosAjax');"
				+ "tablaElementosAjax.style.marginTop = '-70px';"
				+ "tablaElementosAjax.style.height = '" + altoArbol + "';"
				+ "tablaElementosAjax.style.width = '450px';"
				
				+ "";
		
		String columnaDerecha2 = ""
				+ ""
				
				+ "var tablaMeritos = document.getElementById('tablaMeritos');"
				+ "tablaMeritos.style.background = '" + colorFondoInterno + "';"
				+ "tablaMeritos.style.border = 'none';"
				+ "tablaMeritos.style.height = '" + altoTablameritos + "';"
				+ "tablaMeritos.style.minWidth = '400px';"
				+ ""
				+ "var arbol = document.getElementById('arbol');"
				+ "arbol.style.height = '" + altoArbol + "';"
				+ "arbol.style.width = '440px';"
				+ "arbol.style.background = '" + colorFondoInterno + "';"
				+ ""
				
				+ "var tablaAtributosAjax = document.getElementById('tablaAtributosAjax');"
				+ "tablaAtributosAjax.style.marginLeft = '470px';"
			//	+ "tablaAtributosAjax.style.marginTop = '-930px';"
				+ "tablaAtributosAjax.style.miWidth = '500px';"
				
				+ "var colPropDinamica = document.getElementById('colPropDinamica');"
				+ "colPropDinamica.style.display = 'none';"
				
				+ "var tablaDocumento = document.getElementById('tablaDocumento');"
				+ "tablaDocumento.style.background = '" + colorFondoInterno + "';"
				+ "tablaDocumento.style.border = 'none';"
				+ "tablaDocumento.style.width = '520px';"
				+ "tablaDocumento.style.minWidth = '520px';"
				
				+ "var legend = document.getElementsByTagName('legend');"
		//		+ "alert(legend.length);"
				+ "for(var i=0;i<legend.length;i++){"
					+ "legend[i].style.width = '400px';"
					+ "legend[i].style.paddingTop = '40px';"
				+ "}";
		
		if(InicioAltairJacob.numeroPantallas == 1){
			columnaDerecha2 += "tablaAtributosAjax.style.marginTop = '-759px';";
		}
		else{
			columnaDerecha2 += "tablaAtributosAjax.style.marginTop = '-930px';";
		}
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		Dispatch documento = Dispatch.call(navegador, "document").getDispatch();
		Dispatch loadContexto = Dispatch.call(documento, "getElementById","loadContexto").getDispatch();
		Dispatch.put(loadContexto,"innerHTML","Holaaaaa");
		*/
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Dispatch.call(navegador, "navigate","javascript:" 
				+ columnaDerecha1 
				+ columnaDerecha2  + "alert('hola 2');");
		
		
		Dispatch documento = Dispatch.call(navegador, "document").getDispatch();
		Dispatch loadContexto = Dispatch.call(documento, "getElementById","loadContexto").getDispatch();
		Variant nombrePacient = Dispatch.get(loadContexto,"innerHTML");
		String nombrePaciente = nombrePacient.getString();
		/*
		WebElement paciente = driver.findElement(By.id("loadContexto"));
		String nombrePaciente = paciente.getAttribute("innerHTML").trim();
		*/
		
		System.out.println("Nombre del paciente  " + nombrePaciente);
		
		int index = nombrePaciente.indexOf("(");
		if(index != -1){
			nombrePaciente = nombrePaciente.substring(0,index);
		}
		if(nombrePaciente.contains("Contexto Vacío")){
			nombrePaciente = "Contexto Vacío";
			errorDeContexto = true;
		}
		
		System.out.println("Nombre del paciente  " + nombrePaciente);

/*		
		List<WebElement> nuevaSeccionEdicion = driver.findElements(By.id("nuevaSeccionEdicion"));
		
		System.out.println(nuevaSeccionEdicion.size());
*/		
		
		String otros1 = ""
				+ ""
				
				+ "var contextoMenuSuperior = document.getElementById('contextoMenuSuperior');"
				+ "contextoMenuSuperior.style.marginRight = '400px';"
				+ ""
				+ "var loadContexto = document.getElementById('loadContexto');"
				+ "loadContexto.innerHTML = '" + nombrePaciente + "';"
				+ "loadContexto.style.marginLeft = '-1000px';"
				+ "loadContexto.style.width = '800px';"
				+ "loadContexto.style.color = 'yellow';"
				+ "loadContexto.style.fontSize = '25px';"
				+ ""
				
				+ "var comprimirA = document.getElementById('selectDisplayButtonsTree');"
				+ "comprimirA.style.left = '1200px';"
				+ "comprimirA.style.display = 'none';"
				
				+ "var comprimirB = document.getElementById('selectDisplayButtonsTree');"
				+ "comprimirB.style.left = '1200px';"
				+ "comprimirB.style.display = 'none';"
				
				+ "var textArea = document.getElementById('{hc}comentario-{hc}docExt');"
				+ "textArea.style.width = '475px';"
				+ "textArea.style.marginLeft = '20px';"
				;
		
		
		String codigo = fondoYEncabezado + columnaIzquierda;
				
		
		/*
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(codigo);
		*/
		
		
		 
		 
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				Dispatch.call(navegador, "navigate","javascript:" 
				+ otros1  + "alert('hola 3');");
		
		/*
		js.executeScript(columnaDerecha1);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("tablaMeritos"));
		
		js.executeScript(columnaDerecha2);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		js.executeScript(otros1);
*/
		
		String cajasAmarillas = ""
				+ ""
				+ "var cajasAmarillas = document.getElementsByClassName('custom-combobox-input');"
				+ "var tam = cajasAmarillas.length;"
				+ "var conteo = 0;"
				+ "for(var i=0;i<tam-2;i++){"
					+ "var comparacion = cajasAmarillas[i].style.backgroundColor.search('255');"
					// + "alert(cajasAmarillas[i].style.backgroundColor);"
					+ "if(comparacion != -1){"
						+ "if(i != 0){"
							+ "cajasAmarillas[i].style.backgroundColor = '" + colorFondoCajas + "';"
							+ "var ide = 'cajaColoreada' + conteo;"
							+ "conteo = conteo + 1;"
							+ "cajasAmarillas[i].setAttribute('id',ide);"						
						+ "}"

						+ "cajasAmarillas[i].style.paddingLeft = '20px';"
						+ "cajasAmarillas[i].style.marginLeft = '20px';"
						+ "cajasAmarillas[i].style.width = '465px';"
						+ "cajasAmarillas[i].style.color = 'red';"
						+ "cajasAmarillas[i].style.font = 'bold 18px arial, sans-serif';"

						+ "if(conteo == 2){"
						+ 	"break;"
						+ "}"
					+ "}"
				+ "}"
				+ "var fecha = document.getElementById('{hc}dataVersion-{hc}docExt');"
				+ "fecha.style.backgroundColor = '" + colorFondoCajas + "';"
				+ "fecha.style.font = 'bold 20px arial, sans-serif';"
				+ ""
				+ "var labelAtributo = document.getElementById('labelAtributo');"
				+ "labelAtributo.style.width = '500px';"
				+ "labelAtributo.style.color = 'green';"
				+ "labelAtributo.style.paddingLeft = '10px';"
				+ ""
				+ "var estiloCajas = document.getElementsByClassName('custom-combobox');"
				+ "var tam = estiloCajas.length;"
				// + "alert(tam);"
				+ "for(var i=0;i<tam-5;i++){"
		//			+ "document.getElementById('{hc}titulo-{hc}docExt').value = tam;"
					+ "estiloCajas[i].style.width = '520px';"
				+ "}"
				+ "";
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Dispatch.call(navegador, "navigate","javascript:" 
		+ cajasAmarillas  + "alert('hola 4');");
		
		
		/*
		
		for(int i=2;i<5;i++)
			js.executeScript("arguments[0].style.display = 'none'", nuevaSeccionEdicion.get(i));
		
		js.executeScript(cajasAmarillas);

		List<WebElement> titulos = driver.findElements(By.id("colPropDinamicaAncha"));
		System.out.println(titulos.size());
		
		for(int i=0;i<titulos.size();i++)
			js.executeScript("arguments[0].style.width = '300px';arguments[0].style.textAlign = 'left';", titulos.get(i));

		
		//  Nhc servicio documento
		
		WebElement inicio = driver.findElement(By.xpath("//*[@id=\"primary-nav\"]/li[2]/a"));
		WebElement docPendentes = driver.findElement(By.xpath("//*[@id=\"primary-nav\"]/li[3]/a"));
		WebElement busqueda = driver.findElement(By.xpath("//*[@id=\"primary-nav\"]/li[4]/a"));
		
		String cadenaNav = ""
				+ ""
				+ "for(var i=0;i<3;i++){"
					+ "arguments[i].style.color = 'yellow';"
					+ "arguments[i].style.fontSize = '30px';"
					+ "arguments[i].style.marginBottom = '10px';"
					+ "arguments[i].style.marginRight = '30px';"
					+ "if(i==0){"
						+ "arguments[0].setAttribute('id','nhcNav');"
//						+ "arguments[0].innerHTML = '12345';"
					+ "}"
					+ "else if(i==1){"
						+ "arguments[1].setAttribute('id','servicioNav');"
//						+ "arguments[1].innerHTML = 'CARC';"
					+ "}"
					+ "else{"
						+ "arguments[2].setAttribute('id','tituloNav');"
//						+ "arguments[2].innerHTML = 'Consentimento';"
					+ "}"
				+ "}"

				+ "return document.getElementById('labelAtributo').innerHTML;";
		
		String nombreFichero = js.executeScript(cadenaNav, 
					inicio, docPendentes, busqueda).toString();
		
		System.out.println(nombreFichero);
		
		boolean errorNhc = false;
		
		XedocIndividualSelenium xedoc = new XedocIndividualSelenium(nombreFichero, driver);

		errorNhc = putNHC(driver, xedoc);

		
		if(!errorNhc){
			
			if(!errorDeContexto){
				
				xedoc.buscaNodo();
				try {
					Thread.sleep(100);
					xedoc.seleccionarServicio();
					Thread.sleep(100);
					xedoc.seleccionarDocumento();
					Thread.sleep(100);
					xedoc.inicializaNodosHospUrgQui();
					Thread.sleep(100);
					xedoc.ocultaNodos();
					Thread.sleep(100);
					xedoc.getFocus();
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		*/
	}
}
