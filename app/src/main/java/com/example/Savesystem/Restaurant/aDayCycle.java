package com.example.Savesystem.Restaurant;

import java.util.ArrayList;
import java.util.Stack;

public class aDayCycle {

	private ArrayList<opening> openings;
	private ArrayList<timeInterval> interval;
	private int days;

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

	public ArrayList<timeInterval> getInterval() {
		return this.interval;
	}

	/**
	 * 
	 * @param timeInterval
	 */
	public void setInterval(ArrayList<timeInterval> timeInterval) {
		this.interval = timeInterval;
	}

	public int getDays() {
		return this.days;
	}

	/**
	 * 
	 * @param days
	 */
	public void setDays(int days) {
		this.days = days;
	}

}