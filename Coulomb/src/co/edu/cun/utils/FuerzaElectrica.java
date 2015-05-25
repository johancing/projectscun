package co.edu.cun.utils;

import co.edu.cun.control.Particula2D;
import co.edu.cun.core.Particula;

public class FuerzaElectrica {

	// private static final double K = 8987500000.0;
	private static final double K = 9000000000.0;

	private static double getFuerzaElectricaNeta(Particula2D a, Particula2D b,
			int escala) {
		double distancia = Matematicas.calcularDistancia(a.getPosX(),
				b.getPosX(), a.getPosY(), b.getPosY());
		distancia = distancia / escala;
		distancia = Math.pow(distancia, 2);
		double q1 = calcularValorNeto(a);
		double q2 = calcularValorNeto(b);
		if (distancia > 0) {
			distancia = ((K * q1 * q2) / distancia);
			if (distancia < 0)
				distancia = distancia * -1;
			return distancia;
		}
		return 0;
	}

	public static double getFuerzaElectrica(Particula p) {
		if (p != null) {
			double fuerza = Math.sqrt(Math.pow(p.getFuerzaVectorial()[0], 2)
					+ Math.pow(p.getFuerzaVectorial()[1], 2));
			return fuerza;
		}
		return 0;
	}

	public static double[] getFuerzaElectricaVectorial(Particula2D a,
			Particula2D b, int escala) {
		double[] valores = new double[2];
		double fuerzaNeta = getFuerzaElectricaNeta(a, b, escala);
		double angulo = Matematicas.calcularAngulo(a.getPosX(), b.getPosX(),
				a.getPosY(), b.getPosY());
		angulo = calcularAnguloTotal(angulo, a, b);
		valores[0] = fuerzaNeta * Math.cos(Math.toRadians(angulo));
		valores[1] = fuerzaNeta * Math.sin(Math.toRadians(angulo));
		return valores;
	}

	private static double calcularAnguloTotal(double angulo, Particula2D a,
			Particula2D b) {
		if (angulo == 0) {
			if (esFuerzaAtraccion(a.getParticula().getCarga(), b.getParticula()
					.getCarga())) {
				if (a.getPosX() < b.getPosX()) {
					return 0;
				} else {
					return 180;
				}
			} else {
				if (a.getPosX() < b.getPosX()) {
					return 180;
				} else {
					return 0;
				}
				
			}
		}
		if (angulo == 90) {
			if (esFuerzaAtraccion(a.getParticula().getCarga(), b.getParticula()
					.getCarga())) {
				return 90;
			} else {
				return 270;
			}
		}
		if (angulo < 0) {
			if (a.getPosY() < b.getPosY()) {
				if (esFuerzaAtraccion(a.getParticula().getCarga(), b.getParticula()
						.getCarga())) {
					return 360 + angulo;
				} else {
					return 180  + angulo;
				}
			} else {
				if (esFuerzaAtraccion(a.getParticula().getCarga(), b.getParticula()
						.getCarga())) {
					return 180 + angulo;
				} else {
					return 360  + angulo;
				}
			}
		} else {
			if (a.getPosY() < b.getPosY()) {
				if (esFuerzaAtraccion(a.getParticula().getCarga(), b.getParticula()
						.getCarga())) {
					return 180 + angulo;
				}
			} else {
				if (esFuerzaAtraccion(a.getParticula().getCarga(), b.getParticula()
						.getCarga())) {
					return 180  + angulo;
				}
			}
		}
		return 0;
	}

	private static boolean esFuerzaAtraccion(double carga, double carga2) {
		if ((carga < 0 && carga2 > 0) || (carga > 0 && carga2 < 0)) {
			return true;
		}
		return false;
	}

	private static double calcularValorNeto(Particula2D a) {
		double valor = a.getParticula().getUnidad();
		try {
			if (valor == 0)
				valor = a.getParticula().getCarga() / Particula.MILLI_C;
			else if (valor == 1)
				valor = a.getParticula().getCarga() / Particula.MICRO_C;
			else if (valor == 2)
				valor = a.getParticula().getCarga() / Particula.NANO_C;
		} catch (ArithmeticException e) {
			return 0.0;
		}
		return valor;
	}

}
