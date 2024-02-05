package Restaurant;

import java.time.LocalTime;

public class timeInterval {

	private LocalTime start;
	private LocalTime end;

	public LocalTime getStart() {
		return this.start;
	}

	/**
	 * 
	 * @param start
	 */
	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getEnd() {
		return this.end;
	}

	/**
	 * 
	 * @param end
	 */
	public void setEnd(LocalTime end) {
		this.end = end;
	}

}