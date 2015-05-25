package co.edu.cun.utils;

import co.edu.cun.core.Particula;

public class Matematicas {

	public static double calcularDistancia(int x1, int x2, int y1, int y2) {
		double x = Math.pow(x2 - x1, 2);
		double y = Math.pow(y2 - y1, 2);
		double d = Math.sqrt(x + y);
		return d;
	}

	public static double anguloFuerza(Particula p) {
		if (p.getFuerzaVectorial() != null && p.getFuerzaVectorial()[0] != 0) {
			double angulo = Math.atan(p.getFuerzaVectorial()[1]
					/ p.getFuerzaVectorial()[0]);
			angulo = Math.toDegrees(angulo);
			angulo = (angulo < 0) ? angulo * -1 : angulo;
			if (p.getFuerzaVectorial()[0] > 0) {
				angulo = (p.getFuerzaVectorial()[1] > 0) ? angulo : 360 - angulo;
			} else {
				angulo = (p.getFuerzaVectorial()[1] > 0) ? 180 - angulo : 180 + angulo; 
			}
			return angulo;
		}
		return 0.0;
	}

	public static double calcularAngulo(int x1, int x2, int y1, int y2) {
		double a = (y2 - y1) * -1;
		double b = x2 - x1;
		double angulo = Math.atan(a / b);
		angulo = Math.toDegrees(angulo);
		return angulo;
	}

}
