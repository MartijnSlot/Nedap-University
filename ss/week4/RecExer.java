package week4;

import java.util.Arrays;

public class RecExer {

	private static int[] blabla = {1,2,3,4,5};

	public RecExer(){

	}

	public void addToArray(int[] snikkel){
		for (int i = 1; i < snikkel.length; i++) {
			int temp;
			temp = snikkel[i];
			snikkel[i] = snikkel[i-1];
			snikkel[i-1] = temp;
	}
}

public static void main(String[] args){
	RecExer snikkel = new RecExer();
	System.out.print(Arrays.toString(blabla));
	snikkel.addToArray(blabla);
	System.out.print("\n" + Arrays.toString(blabla));

}
}
