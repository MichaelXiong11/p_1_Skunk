package skunk.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PredictableDie {

	private int[] theRolls;
	private int nextInt;
	private int lastRoll;
	
	public PredictableDie(){
		
	}

	public PredictableDie(int[] is) {
		this.theRolls = is;
		this.nextInt = 0;
	}

	public void roll() {
		this.lastRoll = this.theRolls[this.nextInt];
		
		
	}

	public int getLastRoll() {
		// TODO Auto-generated method stub
		return this.lastRoll;
	}

}
