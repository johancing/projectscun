package co.edu.cun.control;

import co.edu.cun.gui.Lienzo;
import co.edu.cun.gui.PanelPropiedades;

public class Mediador {
	
	private Particula2D particula;
	private boolean agregarParticula;
	private Lienzo lienzo;
	private PanelPropiedades panel;
	private int escala;
	
	public void eliminarParticula() {
		if (lienzo != null) {
			lienzo.eliminar();
		}
	}

	public Particula2D getParticula() {
		return particula;
	}

	public void setParticula(Particula2D particula) {
		this.particula = particula;
		if (panel != null) {
			panel.setParticula(particula);
			panel.verPropiedades();
		}
	}
	
	public void crearEjemplos(int ejemplo) {
		if (lienzo != null) {
			switch(ejemplo) {
			case 1:
				lienzo.ejemplo1();
				break;
			case 2:
				lienzo.ejemplo2();
				break;
			default:
				lienzo.limpiar();
				break;
			}
		}
	}
	
	public void reiniciar() {
		if (panel != null) {
			panel.setParticula(null);
			panel.reiniciarCampos();
		}
	}
	
	public void configurar(String nombre, double carga, int unidades, int posX, int posY) {
		if (this.getParticula() != null && this.getParticula().isActivo()) {
			getParticula().setNombre(nombre);
			getParticula().getParticula().setCarga(carga);
			getParticula().getParticula().setUnidad(unidades);
			getParticula().setPosX(posX);
			getParticula().setPosY(posY);
			if (lienzo != null)
				lienzo.calcularValores(getParticula());
		}
	}
	
	public boolean isAgregarParticula() {
		return agregarParticula;
	}

	public void setAgregarParticula(boolean agregarPArticula) {
		this.agregarParticula = agregarPArticula;
	}

	public Lienzo getLienzo() {
		return lienzo;
	}

	public void setLienzo(Lienzo lienzo) {
		this.lienzo = lienzo;
	}

	public PanelPropiedades getPanel() {
		return panel;
	}
	
	public void limpiar(){
		lienzo.limpiar();
	}

	public void setPanel(PanelPropiedades panel) {
		this.panel = panel;
	}

	public int getEscala() {
		return escala;
	}

	public void setEscala(int escala) {
		this.escala = escala;
	}

}
