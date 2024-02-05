package com.example.Savesystem.Restaurant;

import java.util.ArrayList;
import java.util.Stack;

public class daysCycle {

	private ArrayList<aDayCycle> cycles;
	private ArrayList<opening> openings;
	public daysCycle(ArrayList<aDayCycle> cycles, ArrayList<opening> openings) {
		this.cycles = cycles;
		this.openings =openings;
	}

	public daysCycle() {
		cycles = new ArrayList<aDayCycle>();
		openings = new ArrayList<opening>();
	}
	public ArrayList<aDayCycle> getCycles() {
		return this.cycles;
	}

	/**
	 * 
	 * @param cycles
	 */
	public void setCycles(ArrayList<aDayCycle> cycles) {
		this.cycles = cycles;
	}



	public ArrayList<opening> getOpenings() {
		return this.openings;
	}

	/**
	 * 
	 * @param openings
	 */
	public void setOpenings(ArrayList<opening> openings) {
		this.openings = openings;
	}

	public int getNumOfDay() {
		return this.openings.size();
	}

	/**
	 * 
	 * @param c
	 */
	public void addCycles(aDayCycle c) {
		this.cycles.add(c);
	}

	/**
	 * 
	 * @param c
	 */
	public void removeCycle(aDayCycle c) {
		this.cycles.remove(c);
	}

	/**
	 * 
	 * @param cycles
	 * @param openings
	 */


}