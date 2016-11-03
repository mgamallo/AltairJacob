package es.mgamallo.altair;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class InicioAltairJacob {

	protected static final String LS = System.getProperty("line.separator");
	
	static final String RUTAXEDOC = "http://xedocidx.sergas.local/xedoc_idx/login";
	static final String RUTAIANUS = "http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp";

	
    static ActiveXComponent oShell;  
    static ActiveXComponent oWindows; 
	
	static ActiveXComponent bandejaXedoc;
	static ActiveXComponent xedoc1;
	static ActiveXComponent xedoc2;
	static ActiveXComponent ianus;
	
	public static boolean esWin64 = false;
	
	static String nombreVentanaBandejaXedoc = "";
	static String nombreVentanaXedoc1 = "";

	static boolean xedoc1Activo = true;
	
	static VentanaControlXedoc ventana;

	static int numeroPantallas;

	static boolean inicioTanda = true;
	static boolean primerPdf = true;

	public static int RANGO_DIAS_CONSULTA = 100;

	public static int numPdfsTotales = 0;
	public static String listaPdfs[];

	public static String nombrePdfXedoc1 = "";
	public static String nombrePdfXedoc2 = "";
	
	public static User user;
	public static boolean esChop;
	public static final String CODIGO_CHOP = "360340";
	public static final String CODIGO_SALNES = "360353";
	public static final String VALOR_CHOP = "HC_CHOPO";
	public static final String VALOR_SALNES = "HC_SAL";
	
	public static String codigoCentro = "";
	
	public static String[][] tablaDocumentos;
	
	public static void capturaWebs(){
		
		// ComThread.InitSTA();
		
		// String direccionIanus = "http://assccihspre/ianus_hosp2_pre/inicio.do";
		
		/*
		if(Inicio.usuario.tipoDocumentacion == 2){
			direccionIanus = DIRECCIONIANUS_SALNES;
		}
		else{
			direccionIanus = DIRECCIONIANUS_CHOP;
		}
		*/
		
	    oShell = new ActiveXComponent("Shell.Application"); 
	    oWindows = oShell.invokeGetComponent("Windows");

	    String sistema = System.getProperty("sun.arch.data.model");
	    
	    if(	!sistema.equals("32")){
	    	
	    		System.out.println("Capturando ianus en 64 bits");
		        try {
					Runtime.getRuntime().exec("C:/Program Files (x86)/Internet Explorer/iexplore.exe -nomerge");
					Thread.sleep(1000);
					
					Runtime.getRuntime().exec("C:/Program Files (x86)/Internet Explorer/iexplore.exe -nomerge");
					Thread.sleep(1000);
					
		        } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	    else{
	    	
	    	System.out.println("Capturando ianus en 32 bits");
	        try {
				Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe -nomerge");
				Thread.sleep(1000);
				Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe -nomerge");
				Thread.sleep(1000);

	        } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
        
        int iCount = oWindows.getProperty("Count").getInt();
        System.out.println("iCount: " + iCount);        
		
        for (int i=iCount-1,j= 1; i >iCount-3 ; i--,j++) {
            ActiveXComponent oWindow = oWindows.invokeGetComponent("Item", new Variant(i));     
            String sLocName = oWindow.getProperty("LocationName").getString();
            String sFullName = oWindow.getProperty("FullName").getString();
            String sUrl =  oWindow.getProperty("LocationURL").getString();
            
            boolean isIE = sFullName.toLowerCase().endsWith("iexplore.exe");
            boolean bVisible = oWindow.getProperty("Visible").getBoolean();
            System.out.println("i: " + i + ", loc: " + sLocName + ", name: " + sFullName + ", isIE: " + isIE + ", vis: " + bVisible);
 
            
            /*
            if ((isIE)&&(sLocName.startsWith("about:blank"))) {
                oIE = oWindow;
            }
            */
            if(j==1){
            	bandejaXedoc = oWindow;
            }
            if(j==2){
            	xedoc2= oWindow;
            }
            if(j==3){
            	ianus= oWindow;
            } 
        }

	}
	
	
	public static void colocaWebsXedoc(){
		
		int ancho = 0;
		int alto = 0;
		int izquierda = 0;
		int arriba = 0;
		if(numeroPantallas == 1){
			
			ancho = 1919 / 2;
			alto = 1172;
			arriba = 0;
			izquierda = 0;
			
		    Dispatch.put(bandejaXedoc,"height",alto);
		    Dispatch.put(bandejaXedoc,"width",ancho);
		    Dispatch.put(bandejaXedoc,"top",arriba);  
		    Dispatch.put(bandejaXedoc,"left",izquierda);
			
		    Dispatch.put(xedoc2,"height",alto);
		    Dispatch.put(xedoc2,"width",ancho);
		    Dispatch.put(xedoc2,"top",arriba);  
		    Dispatch.put(xedoc2,"left",izquierda + ancho);
			
		}
		else{
			ancho = 1023;
			alto = 1279;
			arriba = 0;
			izquierda = 1025;
			
		    Dispatch.put(xedoc2,"height",alto);
		    Dispatch.put(xedoc2,"width",ancho);
		    Dispatch.put(xedoc2,"top",arriba);  
		    Dispatch.put(xedoc2,"left",izquierda);
			
		    Dispatch.put(bandejaXedoc,"height",alto);
		    Dispatch.put(bandejaXedoc,"width",ancho);
		    Dispatch.put(bandejaXedoc,"top",arriba);  
		    Dispatch.put(bandejaXedoc,"left",0);
		}
		

	    
		Dispatch.put(bandejaXedoc,"menubar",false);
		Dispatch.put(bandejaXedoc,"toolbar",false);
		Dispatch.put(xedoc2,"menubar",false);
		Dispatch.put(xedoc2,"toolbar",false);	
	 

	}
	
	
	public static void cargaUsurioIanus(ActiveXComponent navegador, String ruta){
		
		Dispatch.call(navegador, "Navigate",ruta);
		
		final String introducirUsuarioPulido = 
				"var framePrincipal = window.frames;" + LS +
				"var frameLogin = framePrincipal['principal'].frames['main'];" + LS +
				"var login = frameLogin.document.getElementById('login');" + LS +
				"var password = frameLogin.document.getElementById('password');" + LS +
				"login.value = '" + user.getUsername() + "';" + LS +
				"password.value = '" + user.getPassword() + "';" + LS +
				"frameLogin.aceptar();"
				;  
			
			// introduceUsuarioJacob(Inicio.ianus1, Inicio.usuario);
			
			Dispatch.call(navegador,"Navigate","javascript:" + introducirUsuarioPulido);
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Dispatch.put(navegador,"Visible",true);
			Dispatch.put(navegador,"menubar",false);
			Dispatch.put(navegador,"toolbar",false);
			
			
								//	Dispatch.put(Inicio.paciente1.ianus,"height",550);  // 1099
			
		    Dispatch.put(navegador,"height",1149);  // 1099
		    Dispatch.put(navegador,"top",130);  // 180
		    Dispatch.put(navegador,"left",1024);
		    
			
//		    introduceUsuarioJacob(Inicio.ianus2, Inicio.usuario);

	}
	
	
	public static void cargaUsuarioXedoc(ActiveXComponent navegador, String ruta){

		 Dispatch.call(navegador, "Navigate", ruta);
		 
		 getReadyState(navegador, 4, 70);
		 
		 Dispatch documento = Dispatch.call(navegador, "document").getDispatch();

		 Dispatch centro = Dispatch.call(documento, "getElementById","j_entorno").getDispatch();
		// Dispatch.put(centro, "value", "HC_CHOPO");
		 
		 Dispatch opciones = Dispatch.call(centro, "getElementsByTagName","option").getDispatch();
		 // System.out.println(Dispatch.call(opciones,"length").toString());´
		 
		 int numero = Integer.valueOf(Dispatch.call(opciones,"length").toString());
		 
		 Dispatch centroSel = null;
		 
		 for(int i=0;i<numero;i++){
			 Dispatch opcion = Dispatch.call(opciones,String.valueOf(i)).getDispatch();
			 String valor = Dispatch.get(opcion,"value").getString();
			 System.out.println(Dispatch.get(opcion,"value"));
			 
			 if(esChop && valor.equals(VALOR_CHOP)){
				 centroSel  = Dispatch.call(opciones,String.valueOf(i)).getDispatch();
				 break;
			 }
			 else if(!esChop && valor.equals(VALOR_SALNES)){
				 centroSel  = Dispatch.call(opciones,String.valueOf(i)).getDispatch();
				 break;
			 }
		 }
		 
/*		 
		 if(esChop){
			 centroSel  = Dispatch.call(opciones,"14").getDispatch();
		 }
		 else{
			 centroSel  = Dispatch.call(opciones,"15").getDispatch();
		 }
*/		 
		// System.out.println(Dispatch.get(centroSel,"value"));
		 Dispatch.put(centroSel,"selected","true");
		 
		 
		// System.out.println(Dispatch.get(centro,"value").toString());
	 
		 Dispatch password = Dispatch.call(documento, "getElementById","j_password").getDispatch();
		 Dispatch.put(password, "value",  user.getPassword() /* "archivo1"*/ );
		 
		 Dispatch login = Dispatch.call(documento, "getElementById","j_username").getDispatch();
		 Dispatch.put(login, "value",  user.getUsername()  /* "mgamgul1"*/ );
		 
		 Dispatch boton = Dispatch.call(documento, "getElementsByTagName","input").getDispatch();
		 
		 int numeroInputs = Integer.valueOf(Dispatch.get(boton,"length").toString());
	//	 System.out.println(Dispatch.get(boton,"length").toString());
		 
		 for(int i=0;i<numeroInputs;i++){
			 Dispatch input = Dispatch.call(boton,String.valueOf(i)).getDispatch();
	//		 System.out.println(i + "     " + Dispatch.get(input,"value"));
			 if(Dispatch.get(input,"value").toString().equals("Entrar")){
				 Dispatch.call(input, "setAttribute","id","entrar");
				 break;
			 }
		 }
		 
		 
		 Dispatch.call(navegador,"navigate","javascript:document.getElementById('entrar').click();");
		 
		 
		 
		 /*
		// Dispatch formulario = Dispatch.call(documento, "getElementById","loginForm").getDispatch();

		// Dispatch.call(formulario, "submit");
		 
		 
		 String codigo = ""
				 
		 		+ "var sel = document.getElementById('j_entorno');"
		 		+ "var opciones = sel.getElementsByTagName('option');"
		 		+ "for(var i =0;i< opciones.length;i++){"
		 			+ "if(opciones[i].value.localeCompare('HC_CHOPO') == 0){"
		 				+ "opciones[i].selected = true;"
		 			+ "}"
		 		+ "}"
		 		+ ""
		 		+ "var pass = document.getElementById('j_password');"
		 		+ "pass.value = 'archivo1';"
		 		+ "var usuario = document.getElementById('j_username');"
		 		+ "usuario.value = 'mgamgul1';"
		 		+ ""
		 		
		 		+ "document.getElementById('loginForm').submit();"
		 		+ "";
		 
		//  Dispatch.call(navegador, "Navigate","javascript:" + codigo);
		 		*/

	}


	/**
	 * @param navegador
	 * @param estado		3 casi listo; 4 listo;
	 */
	
	public static void getReadyState(ActiveXComponent navegador, int estado, int maxCiclos) {
		Variant cadena = Dispatch.call(navegador, "readyState");

		int ciclos = 0;
		while (true && ciclos < maxCiclos) {

			cadena = Dispatch.call(navegador, "readyState");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(cadena.toString());
			if (Integer.valueOf(cadena.toString()) == estado) {
				break;
			}
			
			ciclos++;
		}
		
		System.out.println("CArgado en... " + ciclos + " ciclos.");
	}
	
	
	public static void selectMiBandeja(ActiveXComponent navegador){
		
		String miBandeja = "Mi bandeja";
		
		String codigo = ""
				+ "var anclas = document.getElementsByTagName('a');"
				+ "for(var n = 0;n<anclas.length;n++){"
					+ "if(anclas[n].innerHTML.indexOf('" + miBandeja + "') != -1){"
						+ "anclas[n].click();"
						+ "break;"
					+ "}"
				+ "}"
				+ "";
		
		getReadyState(navegador, 4, 50);
		
		Dispatch.call(navegador,"Navigate","javascript:" + codigo);

	}
	
	
	public static void inicializaBandeja(ActiveXComponent navegador, String nombreBandeja){
		
		getReadyState(navegador, 4, 30);
	
		
		String codigo = "";
		String cadenaAux = "";
		
		/*
		if(nombreBandeja.equals("Bandeja Xedoc 1")){
			cadenaAux = "Bandeja 1";
		}
		else{
			cadenaAux = "Bandeja 2";
		}
		*/
		
		Dispatch documento = Dispatch.call(navegador, "document").getDispatch();
		Dispatch entornoLogin = Dispatch.call(documento, "getElementById","entornoLogin").getDispatch();
		Dispatch.put(entornoLogin,"innerHTML",nombreBandeja);
		Dispatch estiloEntornoLogin = Dispatch.get(entornoLogin,"style").getDispatch();
		Dispatch.put(estiloEntornoLogin,"font","bold 38px arial, sans-serif");
		Dispatch.put(estiloEntornoLogin, "color","yellow");
		// Dispatch.put(estiloEntornoLogin, "marginRight","400px");
		
		Dispatch header = Dispatch.call(documento, "getElementById","header").getDispatch();
		Dispatch estiloHeader = Dispatch.get(header, "style").getDispatch();
		
		Dispatch content = Dispatch.call(documento, "getElementById","content").getDispatch();
		Dispatch estiloContent = Dispatch.get(content, "style").getDispatch();
		
		if(nombreBandeja.equals("Bandeja Xedoc 1")){
			Dispatch.put(estiloHeader, "background","black");	
			Dispatch.put(estiloHeader, "height","60px");
			Dispatch.put(documento,"title","Bandeja 1");
			Dispatch.put(estiloContent, "margin-top","30px");
		}
		else{
			Dispatch.put(estiloHeader, "background","black");
			Dispatch.put(estiloHeader, "height","60px");
			Dispatch.put(estiloEntornoLogin, "color","red");
			Dispatch.put(documento,"title","Bandeja 2");
			Dispatch.put(estiloContent, "margin-top","30px");
		}
		
		/*
		codigo = ""
				+ "var entornoLogin = document.getElementById('entornoLogin');"
				+ "entornoLogin.innerHTML = '" + nombreBandeja + "';"
				+ "entornoLogin.style.font = 'bold 38px arial, sans-serif';"
				+ "entornoLogin.style.color = 'yellow';"
			//	+ "entornoLogin.style.marginRight = '400px';"    Funcionaba en iexplorer
				+ ""
				+ "var header = document.getElementById('header');"
				+ "header.style.background = 'black';"
				+ "document.title = '" + cadenaAux + "';"
						// + "alert('hola');"
				+ "";
		
		
		Dispatch.call(navegador,"Navigate","javascript:" + codigo);
		*/
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();

		numeroPantallas = gs.length;
		
		String usuario = "";
		String pass = "";
		
		if(args.length == 0){
			System.out.println("No hay argumentos....");
			
			VentanaPassword vPassword = new VentanaPassword("USUARIO", "",true);
			vPassword.setVisible(true);
			usuario = vPassword.usuarioSelenium.getUsername();
			pass = vPassword.usuarioSelenium.getPassword();
			esChop = vPassword.esChop;
			if(esChop){
				codigoCentro = CODIGO_CHOP;
			}
			else{
				codigoCentro = CODIGO_SALNES;
			}
			
		//	usuario = "asanagu1";
		//	pass = "coke2511";
		}
		else if(args.length == 3){
			usuario = args[0];
			pass = args[1];
			String aux = args[2];
			if(aux.equals("CHOP")){
				esChop = true;
				codigoCentro = CODIGO_CHOP;
			}
			else{
				esChop = false;
				codigoCentro = CODIGO_SALNES;
			}
		}

		
		user = new User(usuario, pass);
		
		new CapturaRatonYTeclado();
		
		capturaWebs();
		
	//	JOptionPane.showMessageDialog(null, esChop );
		
		cargaUsuarioXedoc(bandejaXedoc, RUTAXEDOC);
		cargaUsuarioXedoc(xedoc2, RUTAXEDOC);
	//	cargaUsurioIanus(ianus, RUTAIANUS);
		

		colocaWebsXedoc();
		
	//	System.out.println("Selecciono bandeja...");
		
		selectMiBandeja(bandejaXedoc);
		getReadyState(xedoc2, 4, 20);
		selectMiBandeja(xedoc2);
		
		inicializaBandeja(bandejaXedoc, "Bandeja Xedoc 1");	
		inicializaBandeja(xedoc2, "Bandeja Xedoc 2");	
		
		getReadyState(bandejaXedoc, 4, 40);
		getReadyState(xedoc2, 4, 40);
		
		ventana = new VentanaControlXedoc();
		ventana.setVisible(true);
		
	}

}
