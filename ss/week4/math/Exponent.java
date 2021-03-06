package week4.math;

import week4.Function;

public class Exponent implements Function, Integrandable {
	
	private int i;
	private Function arg1;
	private Function arg2;

	public Exponent(int i) {
		this.i = i;
	}

	@Override
	public double apply(double arg1) {
		return Math.pow(arg1, i);
	}

	@Override
	public Function derivative() {
		arg1 = new Constant(i);
		arg2 = new Exponent(i - 1);
		return new LinearProduct(arg1, arg2);
	}

	@Override
	public Function integrand() {
		arg1 = new Constant(1.0/(i+1));
		arg2 = new Exponent(i+1);
		return new LinearProduct(arg1, arg2);
		
	}
	
	@Override
	public String toString(){
		return "(x^" + i + ")";
	}

}
