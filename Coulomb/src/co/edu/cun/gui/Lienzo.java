package co.edu.cun.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import co.edu.cun.control.Mediador;
import co.edu.cun.control.Particula2D;
import co.edu.cun.core.Particula;
import co.edu.cun.utils.FuerzaElectrica;
import co.edu.cun.utils.Matematicas;

public class Lienzo extends Canvas implements MouseListener {

	private static final long serialVersionUID = 8850327749709809604L;
	private List<Particula2D> particulas;
	private Mediador mediador;
	private int contador;
	private int posX;
	private int posY;

	public Lienzo(Mediador mediador) {
		this.mediador = mediador;
		this.mediador.setLienzo(this);
		this.addMouseListener(this);
		this.setBackground(Color.WHITE);
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (particulas != null) {
			for (Particula2D p : particulas) {
				p.paint(g);
			}
		}
	}

	public List<Particula2D> getParticulas() {
		return particulas;
	}

	public void setParticulas(List<Particula2D> particulas) {
		this.particulas = particulas;
	}

	public Mediador getMediador() {
		return mediador;
	}

	public void setMediador(Mediador mediador) {
		this.mediador = mediador;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		posX = e.getX();
		posY = e.getY();
		if (mediador != null) {
			if (mediador.isAgregarParticula()) {
				crearParticula("Particula_", Color.blue);
			} else {
				seleccionarParticula();
			}
			mediador.setAgregarParticula(false);
		}
	}

	private void seleccionarParticula() {
		mediador.reiniciar();
		if (particulas != null) {
			for (Particula2D p : particulas) {
				if (p.seleccionar(posX, posY)) {
					p.setActivo(true);
					calcularValores(p);
					mediador.setParticula(p);
				} else {
					p.setActivo(false);
				}
			}
			repaint();
		}
	}

	public void calcularValores(Particula2D p) {
		calcularFuerzaVectorial(p);
		calcularFuerzaElectricaNeta(p);
		p.getParticula().setAngulo(Matematicas.anguloFuerza(p.getParticula()));
		repaint();
	}

	private void calcularFuerzaVectorial(Particula2D p) {
		double[] valor = new double[2];
		double[] temp = new double[2];
		for (Particula2D particula : particulas) {
			if (!p.equals(particula)) {
				temp = FuerzaElectrica.getFuerzaElectricaVectorial(p,
						particula, mediador.getEscala());
				valor[0] = valor[0] + temp[0];
				valor[1] = (valor[1] + temp[1]);
			}
		}
		p.getParticula().setFuerzaVectorial(valor);
	}

	private void calcularFuerzaElectricaNeta(Particula2D p) {
		double fuerza = FuerzaElectrica.getFuerzaElectrica(p.getParticula());
		p.getParticula().setFuerzaNeta(fuerza);
	}

	private void crearParticula(String nombre, Color color) {
		Particula particula = new Particula(Particula.CARGA_NEUTRON);
		contador++;
		Particula2D p = new Particula2D(nombre + contador, posX, posY,
				color);
		p.setParticula(particula);
		mediador.setParticula(p);
		if (particulas == null)
			particulas = new LinkedList<Particula2D>();
		particulas.add(p);
		mediador.setAgregarParticula(false);
		repaint();
	}

	public void eliminar() {
		if (particulas != null) {
			Particula2D pa = null;
			for (Particula2D p : particulas) {
				if (p.isActivo())
					pa = p;
			}
			particulas.remove(pa);
			repaint();
		}
		if (mediador != null)
			mediador.reiniciar();
	}

	public void limpiar() {
		particulas = new LinkedList<Particula2D>();
		mediador.reiniciar();
		repaint();
	}

	public void ejemplo1() {
		mediador.setEscala(1000);
		this.particulas = new LinkedList<Particula2D>();
		Particula2D p1 = new Particula2D("Particula_1", 100, 200, Color.BLUE);
		p1.setParticula(new Particula(-3));
		p1.getParticula().setUnidad(1);
		Particula2D p2 = new Particula2D("Particula_2", 400, 200, Color.BLUE);
		p2.setParticula(new Particula(5));
		p2.getParticula().setUnidad(1);
		Particula2D p3 = new Particula2D("Particula_3", 600, 200, Color.BLUE);
		p3.setParticula(new Particula(-4));
		p3.getParticula().setUnidad(1);
		particulas.add(p1);
		particulas.add(p2);
		particulas.add(p3);
		calcularValores(p1);
		calcularValores(p2);
		calcularValores(p3);
	}

	public void ejemplo2() {
		mediador.setEscala(1000);
		this.particulas = new LinkedList<Particula2D>();
		Particula2D p1 = new Particula2D("Particula_1", 200, 150, Color.BLUE);
		p1.setParticula(new Particula(65));
		p1.getParticula().setUnidad(1);
		Particula2D p2 = new Particula2D("Particula_2", 200, 450, Color.BLUE);
		p2.setParticula(new Particula(50));
		p2.getParticula().setUnidad(1);
		Particula2D p3 = new Particula2D("Particula_3", 720, 450, Color.BLUE);
		p3.setParticula(new Particula(-86));
		p3.getParticula().setUnidad(1);
		particulas.add(p1);
		particulas.add(p2);
		particulas.add(p3);
		calcularValores(p1);
		calcularValores(p2);
		calcularValores(p3);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
