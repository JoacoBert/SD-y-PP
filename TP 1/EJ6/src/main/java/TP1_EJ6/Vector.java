package TP1_EJ6;


import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Vector {
	
	private int[] v;
	
	public Vector(int[] v) {
		this.v = v;
	}
	
	public Vector suma(Vector v2){
		int [] arrayV2 = v2.getArray();
		int[] vectorResultadoSuma = new int[5];
		for (int i = 0; i < 5; i++) {
			vectorResultadoSuma[i] = v[i] + arrayV2[i];
		}
		return new Vector(vectorResultadoSuma);
	}

	public Vector resta(Vector v2){
		int [] arrayV2 = v2.getArray();
		int[] vectorResultadoResta = new int[5];
		for (int i = 0; i < 5; i++) {
			vectorResultadoResta[i] = v[i] - arrayV2[i];
		}
		return new Vector(vectorResultadoResta);
	}

	public int[] getArray() {
		return this.v;
	}

	public Vector modificarVector(Vector v1){

		Random random = new Random();
		int [] arrayV2 = v1.getArray();
				
		int num = random.nextInt(100);
		int pos = random.nextInt(5);

		arrayV2[pos] = num;

		return new Vector(arrayV2);
	}

	@Override
	public String toString() {
		return Arrays.toString(v);
	}
}
