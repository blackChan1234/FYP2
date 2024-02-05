package com.example.Savesystem.Restaurant;

import java.time.LocalDate;

public class dayInterval {

	private LocalDate end;
	private LocalDate start;

	public LocalDate getEnd() {
		return this.end;
	}

	/**
	 * 
	 * @param end
	 */
	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public LocalDate getStart() {
		return this.start;
	}

	/**
	 * 
	 * @param start
	 */
	public void setStart(LocalDate start) {
		this.start = start;
	}

}