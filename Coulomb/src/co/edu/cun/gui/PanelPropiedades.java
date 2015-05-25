package co.edu.cun.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.cun.control.Mediador;
import co.edu.cun.control.Particula2D;

public class PanelPropiedades extends JPanel {

	private static final long serialVersionUID = -953993843363492877L;
	private Mediador mediador;
	private Particula2D particula;
	private JTextField nombre;
	private JTextField carga;
	private JTextField fuerzaV;
	private JTextField fuerzaN;
	private JTextField campoV;
	private JTextField campoN;
	private JTextField angulo;
	private JTextField posX;
	private JTextField posY;
	private JComboBox<String> escala;
	private JComboBox<String> unidades;
	private JButton configurar;

	public PanelPropiedades(Mediador mediador) {
		this.setMediador(mediador);
		getMediador().setPanel(this);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		crearControles();
	}

	private void crearControles() {
		this.setLayout(new GridLayout(4, 5));
		JLabel l1 = new JLabel("Nombre:  ");
		this.add(l1);
		nombre = new JTextField();
		this.add(nombre);
		JLabel l2 = new JLabel("Carga");
		this.add(l2);
		carga = new JTextField("0.0");
		this.add(carga);
		unidades = new JComboBox<String>();
		unidades.addItem("mC");
		unidades.addItem("μC");
		unidades.addItem("nc");
		this.add(unidades);
		this.add(new JLabel("Posición X"));
		posX = new JTextField("0");
		this.add(posX);
		this.add(new JLabel("Posición Y"));
		posY = new JTextField("0");
		this.add(posY);
		escala = new JComboBox<String>();
		escala.addItem("cm");
		escala.addItem("mt");
		this.add(escala);
		JLabel l3 = new JLabel("Fuerza Electrica Vectorial");
		this.add(l3);
		fuerzaV = new JTextField("0.0");
		fuerzaV.setEditable(false);
		this.add(fuerzaV);
		JLabel l4 = new JLabel("Fuerza Electrica Total");
		this.add(l4);
		fuerzaN = new JTextField("0.0");
		fuerzaN.setEditable(false);
		this.add(fuerzaN);
		angulo = new JTextField("Angulo 0.0°");
		angulo.setEditable(false);
		this.add(angulo);
		JLabel l5 = new JLabel("Campo Electrico Vectorial");
		l5.setVisible(false);
		this.add(l5);
		campoV = new JTextField("0.0");
		campoV.setEditable(false);
		campoV.setVisible(false);
		this.add(campoV);
		this.add(new JLabel(""));
		campoN = new JTextField("0.0");
		campoN.setEditable(false);
		campoN.setVisible(false);
		this.add(campoN);
		configurar = new JButton("Configurar");
		configurar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mediador != null) {
					try {
						double d = Double.parseDouble(carga.getText());
						int u = unidades.getSelectedIndex();
						mediador.setEscala((escala.getSelectedIndex() == 0) ? 1000
								: 10);
						mediador.configurar(nombre.getText(), d, u,
								Integer.parseInt(posX.getText()) * 10,
								Integer.parseInt(posY.getText()) * 10);
					} catch (NumberFormatException er) {
						JOptionPane.showMessageDialog(null,
								"El valor de los campos debe ser númerico.");
					}
				}
			}
		});
		this.add(configurar);
	}

	public void reiniciarCampos() {
		nombre.setText("");
		carga.setText("0.0");
		fuerzaN.setText("0.0");
		fuerzaV.setText("0.0");
		campoN.setText("0.0");
		campoV.setText("0.0");
		angulo.setText("Angulo 0.0°");
		posX.setText("0");
		posY.setText("0");
		unidades.setSelectedIndex(0);
	}

	public void verPropiedades() {
		if (particula != null) {
			DecimalFormat format = new DecimalFormat("###.###");
			nombre.setText(particula.getNombre());
			carga.setText(format.format(particula.getParticula().getCarga())
					+ "");
			unidades.setSelectedIndex(particula.getParticula().getUnidad());
			fuerzaN.setText(format.format(particula.getParticula()
					.getFuerzaNeta()) + " N");
			fuerzaV.setText(format.format(particula.getParticula()
					.getFuerzaVectorial()[0])
					+ "i;;  "
					+ format.format(particula.getParticula()
							.getFuerzaVectorial()[1]) + "j");
			angulo.setText("Angulo "
					+ format.format(particula.getParticula().getAngulo()) + "°");
			posX.setText(format.format(particula.getPosX() / 10));
			posY.setText(format.format(particula.getPosY() / 10));
		} else {
			reiniciarCampos();
		}
	}

	public Mediador getMediador() {
		return mediador;
	}

	public void setMediador(Mediador mediador) {
		this.mediador = mediador;
	}

	public Particula2D getParticula() {
		return particula;
	}

	public void setParticula(Particula2D particula) {
		this.particula = particula;
	}

}
