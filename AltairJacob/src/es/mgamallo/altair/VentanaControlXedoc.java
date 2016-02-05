package es.mgamallo.altair;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




import com.jacob.com.Dispatch;




public class VentanaControlXedoc extends JFrame{

	private java.awt.Point coordenadasRaton = new java.awt.Point();

	
	JPanel panel1 = new JPanel();
	JPanel panelMover = new JPanel();
	
	JPanel panel2 = new JPanel(new FlowLayout());
	JButton jBiniciar = new JButton("Iniciar");
	JButton jBxedoc1 = new JButton("Xedoc 1");
	JButton jBxedoc2 = new JButton("Xedoc 2");
	JButton jBianus = new JButton("Ianus");
	JButton jBbandeja1 = new JButton("Bandeja 1");
	JButton jBprometeo = new JButton("Prometeo");
	JButton jBmaquetar = new JButton("Maquetar Todo");
	JButton jBpdf1 = new JButton("Recargar Xedoc 1");
	JButton jBpdf2 = new JButton("Recargar Xedoc 2");
	JButton jBsalir = new JButton("Salir");
	JButton jBpegarTitulo = new JButton("Pegar Titulo");
	JButton jBmaquetarXedoc1 = new JButton("Maquetar 1");
	JButton jBmaquetarXedoc2 = new JButton("Maquetar 2");
	JButton jBrecargarArbol = new JButton("Recargar árbol");
	JButton jBsaltarPdf = new JButton("Saltar pdf");
	
	JButton jBiniciarBandejas = new JButton("Reiniciar Bandejas");
	JButton jBactualizarLista = new JButton("Actualizar lista");
	
	JComboBox comboInicio = new JComboBox();
	JComboBox comboDiasCaptura = new JComboBox();
	
	JLabel etiquetaVacia = new JLabel("      ");


	
	static boolean xedoc1inicializado = false;
	
	public VentanaControlXedoc() {
		// TODO Auto-generated constructor stub
		
		
		setSize(550,100);
		setResizable(false);
		getContentPane().add(panel1);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		setUndecorated(true);
		
		if(InicioAltairJacob.numeroPantallas == 1){
			setLocation(1327, 1098);
		}
		else{
			setLocation(1490, 1120);
		}
		
		
		

		

		
		panel1.setBackground(Color.blue);
		panel1.setLayout(new BorderLayout());
		panel1.add(panel2,BorderLayout.CENTER);
		panel1.add(panelMover, BorderLayout.WEST);
		
		panel2.setBackground(new Color(255,255,204));

		panel2.add(jBactualizarLista);
		panel2.add(comboInicio);
		panel2.add(jBiniciar);
	//	panel2.add(jBmaquetar);
		panel2.add(jBxedoc1);
		panel2.add(jBxedoc2);
		panel2.add(jBbandeja1);
		panel2.add(jBianus);
		panel2.add(comboDiasCaptura);
		panel2.add(jBprometeo);

		panel2.add(jBpegarTitulo);
		panel2.add(jBmaquetarXedoc1);
		panel2.add(jBmaquetarXedoc2);
		panel2.add(jBrecargarArbol);
		panel2.add(jBsaltarPdf);
		
		panel2.add(jBpdf1);
		panel2.add(jBpdf2);

		panel2.add(jBiniciarBandejas);
		panel2.add(jBsalir);
		
		Dispatch documento = Dispatch.call(InicioAltairJacob.bandejaXedoc,"document").getDispatch();
		Dispatch row = Dispatch.call(documento, "getElementById","row").getDispatch();
		Dispatch filas = Dispatch.call(row, "getElementsByTagName","tr").getDispatch();
		int numeroFilas = Integer.parseInt(Dispatch.get(filas, "length").toString()) - 1;
		
		System.out.println(numeroFilas);
		
		System.out.println("El número de filas es... " + numeroFilas);
		
		for(int i=0;i<numeroFilas;i++){
			comboInicio.addItem(String.valueOf(i+1));
		}
		
		for(int i= 1;i<25;i++){
			int num = i*20;
			comboDiasCaptura.addItem(String.valueOf(num));
		}
		
		jBactualizarLista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				/*
				WebElement tabla = bandejaXedoc1.findElement(By.id("row"));
				List<WebElement> filas = tabla.findElements(By.tagName("tr"));
				int numeroFilas = filas.size() - 1;
				
				System.out.println("El número de filas es... " + numeroFilas);
				
				comboInicio.removeAllItems();
				
				for(int i=0;i<numeroFilas;i++){
					comboInicio.addItem(String.valueOf(i+1));
				}
				InicioAltairJacob.numPdfsTotales = numeroFilas;
				*/
			}
		});
		
		comboInicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		comboDiasCaptura.setSelectedItem(String.valueOf(InicioAltairJacob.RANGO_DIAS_CONSULTA));
		comboDiasCaptura.setToolTipText("Rango de dias del contexto");
		comboDiasCaptura.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InicioAltairJacob.RANGO_DIAS_CONSULTA = Integer.valueOf(comboDiasCaptura.getSelectedItem().toString());
			}
		});
		
		
		jBiniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int pdfSeleccionado = comboInicio.getSelectedIndex();
				
				GestionXedoc.insertarCodigoBandeja("Bandeja Xedoc 1", pdfSeleccionado, pdfSeleccionado + 1);
				
				/*
				
				System.out.println("Inicializa bandejas xedoc.");
				
				int pdfSeleccionado = comboInicio.getSelectedIndex();
				
				// pdfSeleccionado = 1;
				
				System.out.println("Seleccionado el pdf numero ... " + (pdfSeleccionado + 1) );
				
			//	GestionJacobXedoc.capturaBandeja(bandejaXedoc1, xedoc2, "Xedoc 1", "Xedoc 2", pdfSeleccionado, pdfSeleccionado + 1);
			InicioAltairJacob.insertarCodigoBandeja("Bandeja Xedoc 1", pdfSeleccionado, pdfSeleccionado +1 );					
				
			xedoc1inicializado = true;
				
				jBmaquetar.setBackground(Color.green);
				jBiniciar.setBackground(Color.DARK_GRAY);
				jBiniciar.setEnabled(false);
				
				*/
			}
		});

		jBxedoc1.setBackground(Color.GRAY);
		jBxedoc1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				gestionXedoc1();

			}
		});

		jBxedoc2.setBackground(Color.gray);
		jBxedoc2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gestionXedoc2();
			}
		});
		
		
		jBianus.setBackground(Color.DARK_GRAY);
		jBianus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			if(jBianus.getBackground() == Color.DARK_GRAY){
			//	Dispatch.put(GestionJacobXedoc.ianusApoyoXedoc, "visible","true");
				jBianus.setBackground(Color.green);
			}
			else{
			//	Dispatch.put(GestionJacobXedoc.ianusApoyoXedoc, "visible","false");
				jBianus.setBackground(Color.DARK_GRAY);
			}
				

			}
		});
		
		
		jBbandeja1.setBackground(Color.green);
		jBbandeja1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			/*	
			bandejaXedoc1.switchTo().window(InicioAltairJacob.nombreVentanaBandejaXedoc);
	
				
			if(jBbandeja1.getBackground() == Color.GRAY){
			//	Dispatch.put(GestionJacobXedoc.bandejaXedoc1, "visible","true");
				Point punto;

				punto = new Point(0,0);

				
				bandejaXedoc1.manage().window().setPosition(punto);
				bandejaXedoc1.manage().window().maximize();
				
				jBbandeja1.setBackground(Color.green);
			}
			else{
			//	Dispatch.put(GestionJacobXedoc.bandejaXedoc1, "visible","false");
				
				Point punto;
				if(InicioAltairJacob.numeroPantallas == 2){
					punto = new Point(1050,1260);
				}
				else{
					punto = new Point(1050,1260);
				}
				
				bandejaXedoc1.manage().window().setPosition(punto);
				jBbandeja1.setBackground(Color.GRAY);
			}
			
			bandejaXedoc1.switchTo().window(InicioAltairJacob.nombreVentanaXedoc1);				
				*/
			}
		});
		
				
		jBprometeo.setVisible(false);
		jBprometeo.setBackground(Color.darkGray);
		jBprometeo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			if(jBprometeo.getBackground() == Color.DARK_GRAY){
			//	Inicio.panelPrincipal.frame.setVisible(true);
				jBprometeo.setBackground(Color.green);
			}
			else{
			//	Inicio.panelPrincipal.frame.setVisible(false);
				jBprometeo.setBackground(Color.DARK_GRAY);
			}
				

			}
		});
		
		jBpdf1.setVisible(false);
		jBpdf1.setBackground(Color.GRAY);
		jBpdf1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			//	Dispatch.call(Inicio.documento1.xedoc, "refresh");
			
				/*	
			InicioAltairJacob.driverBandejaXedoc.navigate().refresh();
			*/
			}
		});
		
		jBpdf2.setVisible(false);
		jBpdf2.setBackground(Color.GRAY);
		jBpdf2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			//	Dispatch.call(Inicio.documento2.xedoc, "refresh");

				/*
			InicioAltairJacob.driverXedoc2.navigate().refresh();	
				*/
			}
		});
		
		jBmaquetar.setVisible(false);
		jBmaquetar.setBackground(Color.gray);
		jBmaquetar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*
				if(jBmaquetar.getBackground() == Color.GRAY){
					jBmaquetar.setBackground(Color.green);
					
				}
				else{
				//	Inicio.panelPrincipal.frame.setVisible(false);
					jBmaquetar.setBackground(Color.GRAY);
					
					if(InicioAltairJacob.xedoc1Activo){
						new MaquetadoXedocSelenium(InicioAltairJacob.driverBandejaXedoc, "Xedoc 1", false);
					}
					else{
						new MaquetadoXedocSelenium(InicioAltairJacob.driverXedoc2, "Xedoc 2", false);

					}
					

					
				//	new MaquetadoXedoc(Inicio.documento1.xedoc, "Xedoc 1");
					
					try {
						Thread.sleep(200);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				//	new MaquetadoXedoc(Inicio.documento2.xedoc, "Xedoc 2");
				//	Dispatch.put(Inicio.documento2.xedoc,"visible","false");
				}
				
				 */
			}
		});
		
		jBsalir.setBackground(Color.orange);
		jBsalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<3;i++){
					Cerrar.cerrarIexplorer();
				}
				
			//	Inicio.panelPrincipal.frame.setVisible(true);
				System.exit(0);
				dispose();

			}
		});
		
		
		jBpegarTitulo.setBackground(Color.cyan);
		jBpegarTitulo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			/*	ActiveXComponent xedoc;
				
				if(Inicio.xedoc1onTop){
					xedoc = Inicio.documento1.xedoc;
				}
				else{
					xedoc = Inicio.documento2.xedoc;
				}
				
				
				MaquetadoXedoc maquetadoSoloTitulo = new MaquetadoXedoc(xedoc, true);
				maquetadoSoloTitulo.putTitulo();
				
				*/
			}
		});
		
		jBmaquetarXedoc1.setBackground(Color.cyan);
		jBmaquetarXedoc1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*
				new MaquetadoXedocSelenium(InicioAltairJacob.driverBandejaXedoc, "Xedoc 1", false);
				*/
				
				/*
				String cadena = ""
						+ ""
						+ "var submit = document.getElementById('submitFormFirmar');"
						+ "if(submit != null && submit != undefined){"
							+ "submit.focus();"
						+ "}"
						+ "";
				
				JavascriptExecutor js = (JavascriptExecutor) InicioAltairJacob.driverBandejaXedoc;
				js.executeScript(cadena);
				*/
			}
		});
		
		
		jBmaquetarXedoc2.setBackground(Color.cyan);
		jBmaquetarXedoc2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					/*
				new MaquetadoXedocSelenium(InicioAltairJacob.driverXedoc2, "Xedoc 2", false);
					*/
				
				/*
				String cadena = ""
						+ ""
						+ "var submit = document.getElementById('submitFormFirmar');"
						+ "if(submit != null && submit != undefined){"
							+ "submit.focus();"
						+ "}"
						+ "";
				
				JavascriptExecutor js = (JavascriptExecutor) InicioAltairJacob.driverBandejaXedoc;
				js.executeScript(cadena);
				*/
			}
		});
		
		jBrecargarArbol.setBackground(Color.cyan);
		jBrecargarArbol.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				/*
				WebDriver driver;
				
				if(InicioAltairJacob.xedoc1Activo){
					driver = InicioAltairJacob.driverBandejaXedoc;
				}
				else{
					driver = InicioAltairJacob.driverXedoc2;
				}
				
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				
				
				String cadena = ""
						+ ""
						+ "var consultas = document.getElementById('OTROS-noSeleccionable-rama');"
						+ "var listaConsultas = consultas.getElementsByTagName('li');"
						+ ""
						+ "var numListas = listaConsultas.length;"
						+ ""
						+ "for(var i=0;i<numListas;i++){"
							+ "listaConsultas[i].style.display = 'block';"
						+ "}"
						+ "";
					
					js.executeScript(cadena);
				*/
			}
			
		});
		
		
		jBiniciarBandejas.setVisible(true);
		jBiniciarBandejas.setBackground(Color.yellow);
		jBiniciarBandejas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				/*
				jBiniciar.setEnabled(true);
				jBiniciar.setBackground(Color.lightGray);
				GestionXedocSelenium.reInicializaXedocs();
				
				InicioAltairJacob.numPdfsTotales = 0;
				
				jBbandeja1.doClick();
				*/
				
			}
		});
		
		jBsaltarPdf.setVisible(false);
		jBsaltarPdf.setBackground(Color.magenta);
		jBsaltarPdf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				/*
				ActiveXComponent xedoc;
				
				String texto = "Xedoc ";
				if(Inicio.xedoc1onTop){
					xedoc = Inicio.documento1.xedoc;
					texto += "1";
				}
				else{
					xedoc = Inicio.documento2.xedoc;
					texto += "2";
				}
				
				Dispatch documento = Dispatch.get(xedoc, "document").getDispatch();
				Dispatch siguiente = Dispatch.call(documento,"getElementById","siguiente").getDispatch();
			
				String contenidoDeSiguiente;
				try {
					contenidoDeSiguiente = Dispatch.get(siguiente,"innerHTML").toString();
					System.out.println("Contenido de siguiente1... " + contenidoDeSiguiente );
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					contenidoDeSiguiente = null;
				}
				
				if(contenidoDeSiguiente != null){
					Dispatch.call(siguiente, "click");
					Inicio.saltarXedoc = true;
				}
			*/
			}
		});
		
		
		panelMover.setBackground(Color.red);
		panelMover.add(etiquetaVacia);

		panelMover.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getComponent() == panelMover){
					coordenadasRaton = e.getPoint();
					System.out.println("Pinche en el panel");
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		panelMover.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				java.awt.Point punto = MouseInfo.getPointerInfo().getLocation();
				setLocation(punto.x - coordenadasRaton.x, punto.y
						- coordenadasRaton.y);
			}
		});
		
		setVisible(false);
		
	}

	
	public void gestionXedoc1(){
		
		/*
		
		bandejaXedoc1.switchTo().window(InicioAltairJacob.nombreVentanaXedoc1);
		
		if(xedoc1inicializado){
			if(jBxedoc1.getBackground() != Color.GRAY){
		//		Dispatch.put(Inicio.documento1.xedoc, "visible","true");
				Point punto;
				if(InicioAltairJacob.numeroPantallas == 2){
					punto = new Point(1050,1260);
				}
				else{
					punto = new Point(1050,1260);
				}
				
				try {
				
					bandejaXedoc1.manage().window().setPosition(punto);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jBxedoc1.setBackground(Color.gray);

			}
			else{
		//		Dispatch.put(Inicio.documento1.xedoc, "visible","false");
				jBxedoc1.setBackground(Color.green);

				Point punto = new Point(0,0);
				bandejaXedoc1.manage().window().setPosition(punto);
			
			}
				
		}
		
		*/
	}
	
	public void gestionXedoc2(){
		
		/*
		 
		 
		if(xedoc1inicializado){
			if(jBxedoc2.getBackground() != Color.GRAY){
		//		Dispatch.put(Inicio.documento1.xedoc, "visible","true");
				Point punto;
				if(InicioAltairJacob.numeroPantallas == 2){
					punto = new Point(1050,1260);
				}
				else{
					punto = new Point(1050,1260);
				}
				try {
					xedoc2.manage().window().setPosition(punto);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jBxedoc2.setBackground(Color.gray);
			}
			else{
		//		Dispatch.put(Inicio.documento1.xedoc, "visible","false");
				jBxedoc2.setBackground(Color.green);
				
				Point punto = new Point(0,0);
				xedoc2.manage().window().setPosition(punto);
			
			}
		}
		
		*/
	}
	
	
}
