package co.edu.cun.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import co.edu.cun.control.Mediador;

public class Menu extends JMenuBar implements ActionListener, ItemListener {

	private static final long serialVersionUID = 607482174667991234L;
	private JButton salir;
	private JButton particula;
	private JButton eliminar;
	private JButton calcular;
	private Mediador mediador;
	private JButton campo;
	private JButton limpiar;
	private JComboBox<String> ejemplos;

	public Menu(Mediador mediador) {
		this.mediador = mediador;
		particula = new JButton("Adicionar Particula");
		particula.setMnemonic(0);
		particula.addActionListener(this);
		this.add(particula);
		eliminar = new JButton("Eliminar Particula");
		eliminar.setMnemonic(0);
		eliminar.addActionListener(this);
		this.add(eliminar);
		limpiar = new JButton("Limpiar");
		limpiar.addActionListener(this);
		this.add(limpiar);
		campo = new JButton("Crear Campo");
		campo.setMnemonic(0);
		campo.addActionListener(this);
		calcular = new JButton("Licencia");
		calcular.setMnemonic(0);
		calcular.addActionListener(this);
		this.add(calcular);
		ejemplos = new JComboBox<String>();
		ejemplos.addItem("");
		ejemplos.addItem("Linea Recta");
		ejemplos.addItem("Triangulo");
		ejemplos.addItemListener(this);
		this.add(ejemplos);
		salir = new JButton("Salir");
		salir.setMnemonic(0);
		salir.addActionListener(this);
		this.add(salir);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == particula) {
			if (mediador != null)
				mediador.setAgregarParticula(true);
		} else if (e.getSource() == limpiar){
			mediador.limpiar();
			ejemplos.setSelectedIndex(0);
		} else if (e.getSource() == salir) {
			System.exit(0);
		} else if (e.getSource() == eliminar) {
			if (mediador != null)
				mediador.eliminarParticula();
		} else if (e.getSource() == calcular) {
			String message = "This program is free software: you can redistribute it and/or modify\n"
					+ " it under the terms of the GNU General Public License as published by\n"
					+ " the Free Software Foundation, either version 3 of the License, or\n"
					+ " (at your option) any later version.\n\n"

					+ " This program is distributed in the hope that it will be useful,\n"
					+ " but WITHOUT ANY WARRANTY; without even the implied warranty of \n"
					+ " MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the \n"
					+ " GNU General Public License for more details.\n\n"

					+ "You should have received a copy of the GNU General Public License\n "
					+ "along with this program.  If not, see <http://www.gnu.org/licenses/>.\n\n"
					
					+ "Repositorio:  https://github.com/johancing/projectscun \n"
					+ "Versión: 1.1\n"
					+ "johanc.ing@gmail.com";
			JOptionPane.showMessageDialog(null, message);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == ejemplos)
			if (mediador != null)
				mediador.crearEjemplos(ejemplos.getSelectedIndex());
	}

}
