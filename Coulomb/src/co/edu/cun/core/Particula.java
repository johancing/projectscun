package co.edu.cun.core;

public class Particula {

	public static final double CARGA_ELECTRON = 0.00000000000000000016;
	public static final double CARGA_PROTON = -0.00000000000000000016;
	public static final double CARGA_NEUTRON = 0.0;
	public static final int MILLI_C = 1000;
	public static final int MICRO_C = 1000000;
	public static final int NANO_C = 1000000000;
	private double carga;
	private double fuerzaNeta;
	private double[] fuerzaVectorial;
	private double campoNeto;
	private double[] campoVectorial;
	private int unidad;
	private double angulo;

	public Particula(double carga) {
		setCarga(carga);
		setUnidad(0);
		fuerzaVectorial = new double[2];
		campoVectorial = new double[2];
	}

	public double getCarga() {
		return carga;
	}

	public void setCarga(double carga) {
		this.carga = carga;
	}

	public int getUnidad() {
		return unidad;
	}

	public void setUnidad(int unidad) {
		this.unidad = unidad;
	}

	public double getFuerzaNeta() {
		return fuerzaNeta;
	}

	public void setFuerzaNeta(double fuerzaNeta) {
		this.fuerzaNeta = fuerzaNeta;
	}

	public double[] getFuerzaVectorial() {
		return fuerzaVectorial;
	}

	public void setFuerzaVectorial(double[] fuerzaVectorial) {
		this.fuerzaVectorial = fuerzaVectorial;
	}

	public double getCampoNeto() {
		return campoNeto;
	}

	public void setCampoNeto(double campoNeto) {
		this.campoNeto = campoNeto;
	}

	public double[] getCampoVectorial() {
		return campoVectorial;
	}

	public void setCampoVectorial(double[] campoVectorial) {
		this.campoVectorial = campoVectorial;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

}
