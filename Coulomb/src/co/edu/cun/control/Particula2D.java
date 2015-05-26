package co.edu.cun.control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import co.edu.cun.core.Particula;

public class Particula2D {

	public static final int TAMANO = 20;
	private Particula particula;
	private Rectangle area;
	private boolean activo;
	private String nombre;
	private Color color;
	private int posX;
	private int posY;

	public Particula2D(String nombre, int x, int y, Color color) {
		int tam = TAMANO / 2;
		setPosX(x - tam);
		setNombre(nombre);
		setColor(color);
		setPosY(y - tam);
		area = new Rectangle(getPosX(), getPosY(), TAMANO, TAMANO);
	}

	public boolean seleccionar(int x, int y) {
		area.setBounds(getPosX(), getPosY(), TAMANO, TAMANO);
		return area.contains(x, y);
	}

	public void paint(Graphics g) {
		if (activo) {
			String tittle = nombre + "(" + (getPosX() / 10) + ", "
					+ (getPosY() / 10) + ")";
			g.drawString(tittle, getPosX(), getPosY());
		}
		Color c = g.getColor();
		if (getParticula().getCarga() == 0)
			g.setColor(Color.gray);
		else if (getParticula().getCarga() < 0)
			g.setColor(Color.red);
		else
			g.setColor(Color.blue);	
		g.fillOval(getPosX(), getPosY(), TAMANO, TAMANO);
		g.setColor(c);
	}

	public Particula getParticula() {
		return particula;
	}

	public void setParticula(Particula particula) {
		this.particula = particula;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
