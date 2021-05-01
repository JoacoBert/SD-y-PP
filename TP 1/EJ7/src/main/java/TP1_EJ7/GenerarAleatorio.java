package TP1_EJ7;

import java.util.Random;

public class GenerarAleatorio implements Tarea{
	
	private int n;
	
	public GenerarAleatorio(int n) {
		this.n = n;
	}
	
	public Float ejecutar() {
        Random r = new Random();

        return (float) r.nextInt(this.n);
    }
}
