package co.edu.cun.gui;

import javax.swing.JFrame;
import co.edu.cun.control.Mediador;

public class Aplicacion extends JFrame {

	private static final long serialVersionUID = -2273965975917286426L;
	private Lienzo lienzo;
	private Menu menu;
	private Mediador mediador;

	public Aplicacion() {
		configurarentorno();
	}

	private void configurarentorno() {
		this.setTitle("Simulador ley de coulomb");
		this.setSize(1024, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.mediador = new Mediador();
		menu = new Menu(mediador);
		this.setJMenuBar(menu);
		PanelPropiedades panel = new PanelPropiedades(mediador);
		panel.setBounds(0, 0, 1020, 100);
		this.add(panel);
		lienzo = new Lienzo(mediador);
		lienzo.setBounds(0, 100, 1024, 640);
		this.add(lienzo);
	}

	public static void main(String[] args) {
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.setVisible(true);
	}

}
