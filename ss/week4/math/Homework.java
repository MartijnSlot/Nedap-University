package week4.math;

import week4.Function;

public class Homework {
	
	static Constant c1 = new Constant(5.0);
	static LinearProduct f1 = new LinearProduct(new Constant(4.0), new Exponent (4));
	static Function f2 = f1.integrand();
	static Function f3 = f1.derivative();
	
	public static void main(String[] args){
		System.out.println("f(x) = " + f1 + ", f(8) =  " + f1.apply(8));
		System.out.println("f(x) = " + f2 + ", f(8) =  " + f2.apply(8));
		System.out.println("f(x) = " + f3 + ", f(8) =  " + f3.apply(8));
		
	}



}
