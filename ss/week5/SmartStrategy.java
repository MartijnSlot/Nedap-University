package week5;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SmartStrategy implements Strategy {

	@Override
	public String getName() {
		return "Smart";
	}

	@Override
	public int determineMove(Board b, Mark m) {

		for(int i = 0; i < (int) Math.pow(b.DIM, 2); i++) {
			Board copy = b.deepCopy();
			if(copy.isEmptyField(i)) {
				copy.setField(i, m);
				if(copy.hasWinner()) {
					return(i);
				}
			}
		}

		int middleField = ((int) Math.pow(b.DIM, 2) / 2);
		if(b.isEmptyField(middleField)) {
			return (middleField);
		}

		for(int i = 0; i < (int) Math.pow(b.DIM, 2); i++) {
			Board copy = b.deepCopy();
			Mark a;
			if (m == Mark.XX){
				a = Mark.OO; 
			} else {
				a = Mark.XX;
			}
			if(copy.isEmptyField(i)) {
				copy.setField(i, a);
				if(copy.hasWinner()) {
					return(i);
				}
			}
		}

		Set<Integer> emptyFields = new HashSet<>();
		int randomEmptyField = (int) (Math.random() * emptyFields.size());
		int counter = 0;

		for (int i = 0; i < Math.pow(b.DIM, 2); i++){
			if (b.isEmptyField(i)) {
				emptyFields.add(i);
			}
		}

		Iterator<Integer> iterator = emptyFields.iterator();
		Integer selectField = iterator.next();
		while(counter < randomEmptyField) {
			selectField = iterator.next();
			counter ++;
		}
		return selectField.intValue();
	}
}
