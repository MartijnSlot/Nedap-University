package week6.voteMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PartyList extends Observable {
	
	public List<String> parties;
	
	public PartyList(){
		parties = new ArrayList<String>();	
	}

	public void addParty(String party1) {
		parties.add(party1);
		this.setChanged();
		this.notifyObservers("party");
	}

	public boolean hasParty(String party1) {
		return (parties.contains(party1));
	}

	public List<String> getParties() {
		return parties;
	}

}
